package com.parch.combine.web.controller;

import com.parch.combine.core.component.vo.FlowResult;
import com.parch.combine.web.service.CombineJavaApiService;
import com.parch.combine.web.service.CombineJavaUIApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DefaultCombineController {

    @Autowired
    private CombineJavaApiService defaultCombineWebService;

    @Autowired
    private CombineJavaUIApiService defaultCombineJavaUIService;

    @PostMapping("flow/{domain}/{function}")
    public FlowResult call(@RequestBody Map<String, Object> params, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        return defaultCombineWebService.call(params, domain, function, request, response);
    }

    @PostMapping("file-flow/{domain}/{function}")
    public FlowResult uploadAndCall(@RequestParam("params") String paramJson, @RequestParam("file") MultipartFile file, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return defaultCombineWebService.uploadAndCall(paramJson, file, domain, function, request, response);
    }

    @GetMapping("page/{pageKey}")
    public String page(@PathVariable(name = "pageKey") String pageKey) {
        return defaultCombineJavaUIService.getPage(pageKey);
    }
}
