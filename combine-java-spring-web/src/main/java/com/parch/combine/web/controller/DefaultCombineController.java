package com.parch.combine.web.controller;

import com.parch.combine.core.BaseCombineJavaFunction;
import com.parch.combine.core.component.vo.CombineConfigVO;
import com.parch.combine.core.component.vo.FlowResult;
import com.parch.combine.web.service.CombineJavaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DefaultCombineController {

    @Autowired
    private CombineJavaApiService defaultCombineWebService;

    @PostMapping("flow/{domain}/{function}")
    public FlowResult call(@RequestBody Map<String, Object> params, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        return defaultCombineWebService.call(params, domain, function, request, response);
    }

    @PostMapping("file-flow/{domain}/{function}")
    public FlowResult uploadAndCall(@RequestParam("params") String paramJson, @RequestParam("file") MultipartFile file, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return defaultCombineWebService.uploadAndCall(paramJson, file, domain, function, request, response);
    }

    @GetMapping("page-flow/{function}")
    public String page(@PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        FlowResult pageResult = defaultCombineWebService.call(new HashMap<>(), "page", function, request, response);
        return pageResult.getData() == null ? null : pageResult.getData().toString();
    }

    @PostMapping("register")
    public List<BaseCombineJavaFunction.RegisterResult> register(@RequestBody CombineConfigVO config) {
        return defaultCombineWebService.register(config);
    }
}
