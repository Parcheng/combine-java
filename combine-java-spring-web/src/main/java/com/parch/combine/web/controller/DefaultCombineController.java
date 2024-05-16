package com.parch.combine.web.controller;

import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.web.service.DefaultCombineJavaService;
import com.parch.combine.web.service.DefaultCombineJavaUIService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DefaultCombineController {

    @Autowired
    private DefaultCombineJavaService defaultCombineWebService;

    @Autowired
    private DefaultCombineJavaUIService defaultCombineJavaUIService;

    @PostMapping("/{domain}/{function}")
    public DataResult call(@RequestBody Map<String, Object> params, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        return defaultCombineWebService.call(params, domain, function, request, response);
    }

    @PostMapping("/file/{domain}/{function}")
    public DataResult uploadAndCall(@RequestParam("params") String paramJson, @RequestParam("file") MultipartFile file, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return defaultCombineWebService.uploadAndCall(paramJson, file, domain, function, request, response);
    }

    @GetMapping("/page/{pageKey}")
    public String page(@PathVariable(name = "pageKey") String pageKey) {
        return defaultCombineJavaUIService.getPage(pageKey);
    }
}
