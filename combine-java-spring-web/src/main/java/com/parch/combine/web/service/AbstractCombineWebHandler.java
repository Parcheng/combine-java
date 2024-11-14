package com.parch.combine.web.service;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.vo.FlowResult;
import com.parch.combine.core.BaseCombineJavaFunction;
import com.parch.combine.web.util.ResourceFileUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CombineJava - Web请求Service基类
 */
public abstract class AbstractCombineWebHandler extends BaseCombineJavaFunction {

    public AbstractCombineWebHandler(String configPath) {
        super(configPath);
    }

    /**
     * 调用流程
     *
     * @param params   参数
     * @param domain   域
     * @param function 函数
     * @param request  请求request对象
     * @param response 响应response对象
     * @return 结果
     */
    public FlowResult call(Map<String, Object> params, String domain, String function, HttpServletRequest request, HttpServletResponse response) {
        return super.call(params, this.getHeaders(request), domain, function, f -> this.responseDownload(f, response));
    }

    /**
     * 调用流程
     *
     * @param paramJson 参数JSON
     * @param file     文件对象
     * @param domain   域
     * @param function 函数
     * @param request  请求request对象
     * @param response 响应response对象
     * @return 结果
     */
    public FlowResult uploadAndCall(String paramJson, MultipartFile file, String domain, String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 构建文件入参
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setType(FileNameUtil.getPostfix(file.getOriginalFilename()));
        fileInfo.setData(file.getBytes());

        return super.uploadAndCall(paramJson, this.getHeaders(request), fileInfo, domain, function, f -> this.responseDownload(f, response));
    }

    /**
     * 注册配置
     *
     * @param path 文件路径
     * @return 结果
     * @throws IOException 异常
     */
    public List<RegisterResult> registerByPath(String path) throws IOException {
        if (CheckEmptyUtil.isEmpty(path)) {
            return new ArrayList<>();
        }

        String jsonDataStr = ResourceFileUtil.readFile(path);
        return super.register(jsonDataStr);
    }

    /**
     * 获取请求头信息
     *
     * @param request 请求对象
     * @return 结果
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        if (request == null) {
            return Collections.emptyMap();
        }

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
     * @param fileInfo 文件信息
     * @param response 相应对象
     */
    private boolean responseDownload(FileInfo fileInfo, HttpServletResponse response) {
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileInfo.getName(), String.valueOf(StandardCharsets.UTF_8)));
            response.addHeader("Content-Length", "" + fileInfo.getData().length);
            response.setContentType("application/octet-stream");

            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(fileInfo.getData());
            outputStream.flush();

            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    @PreDestroy
    public void resourceClose() {
        super.resourceClose();
    }
}
