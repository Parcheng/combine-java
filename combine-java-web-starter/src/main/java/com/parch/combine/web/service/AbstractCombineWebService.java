package com.parch.combine.web.service;

import com.parch.combine.CombineJavaStarter;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FileNameUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.core.base.FileInfo;
import com.parch.combine.core.service.ICombineWebService;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.web.dto.JsonConfigInitDTO;
import com.parch.combine.web.util.ResourceFileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class AbstractCombineWebService {

    private ICombineWebService service;

    public AbstractCombineWebService(String configPath) {
        CombineJavaStarter.init(configPath);
        service = CombineJavaStarter.getService();
    }

    public DataResult call(Map<String, Object> params, String domain, String function, HttpServletRequest request, HttpServletResponse response) {
        return execute(params, null, domain, function, request, response);
    }

    @SuppressWarnings("unchecked")
    public DataResult uploadAndCall(String paramJson, MultipartFile file, String domain, String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 入参
        Map<String, Object> params;
        if (CheckEmptyUtil.isEmpty(paramJson)) {
            params = new HashMap<>();
        } else {
            params = JsonUtil.deserialize(paramJson, HashMap.class);
        }

        // 构建文件入参
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setType(FileNameUtil.getPostfix(file.getOriginalFilename()));
        fileInfo.setData(file.getBytes());

        // 执行业务逻辑
        return execute(params, fileInfo, domain, function, request, response);
    }

    public List<JsonConfigInitDTO> register(String path) {
        List<JsonConfigInitDTO> result = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(path)) {
            return result;
        }

        try {
            // 读取配置文件
            String jsonDataStr = ResourceFileUtil.readFile(path);
            service.registerFlow(jsonDataStr, vo -> {
                // 保存每个接口的初始化结果
                JsonConfigInitDTO initDTO = new JsonConfigInitDTO();
                initDTO.setKey(vo.getFlowKey());
                initDTO.setSuccess(vo.isSuccess());
                initDTO.setErrorList(vo.getErrorList());
                initDTO.setComponentIds(vo.getComponentIds());
                initDTO.setStaticComponentIds(vo.getStaticComponentIds());
                result.add(initDTO);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private DataResult execute(Map<String, Object> params, FileInfo fileInfo, String domain, String function, HttpServletRequest request, HttpServletResponse response) {
        List<String> componentIds = service.getComponentIds(domain, function);
        if (componentIds == null) {
            return DataResult.fail("接口未注册", "接口不存在");
        }

        DataResult result = service.execute(domain, function, params, getHeaders(request), fileInfo);
        responseHandler(result, response);
        return result;
    }

    /**
     * 获取请求头信息
     *
     * @param request 请求对象
     * @return 结果
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>(16);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }

        return headers;
    }

    /**
     * 下载
     *
     * @param result 结果对象
     * @param response 相应对象
     */
    private void responseHandler(DataResult result, HttpServletResponse response) {
        try {
            if (result.isDownload() && result.getData() instanceof FileInfo) {
                FileInfo fileInfo = (FileInfo) result.getData();
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileInfo.getName(), StandardCharsets.UTF_8));
                response.addHeader("Content-Length", "" + fileInfo.getData().length);
                response.setContentType("application/octet-stream");

                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                outputStream.write(fileInfo.getData());
                outputStream.flush();
            }
        } catch (IOException ex) {
            result.setSuccess(false);
            result.setErrMsg("文件下载异常");
            result.setShowMsg("文件下载异常");
            ex.printStackTrace();
        }
    }
}
