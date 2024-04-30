package com.parch.combine.web.controller;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FileNameUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.core.base.FileInfo;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.service.FlowExecuteService;
import com.parch.combine.web.service.FlowExecuteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FlowExecuteService flowExecuteService;

    @PostMapping("/{domain}/{function}")
    public DataResult call(@RequestBody Map<String, Object> params, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        DataResult result = flowExecuteService.execute(params, getHeaders(request), null, domain, function);
        responseHandler(result, response);
        return result;
    }

    @PostMapping("/file/{domain}/{function}")
    public DataResult upload(@RequestParam("params") String paramJson, @RequestParam("file") MultipartFile file, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        DataResult result = flowExecuteService.execute(params, getHeaders(request), fileInfo, domain, function);
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
            if (result.isDownload() && result.getData() instanceof FileInfo fileInfo) {
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
