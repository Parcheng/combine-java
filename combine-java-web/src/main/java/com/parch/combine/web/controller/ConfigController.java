package com.parch.combine.web.controller;

import com.parch.combine.dto.JsonConfigInitDTO;
import com.parch.combine.service.FlowConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private FlowConfigService flowConfigService;

    @GetMapping("/refresh")
    public List<JsonConfigInitDTO> init(String path) {
        return flowConfigService.init(path);
    }
}
