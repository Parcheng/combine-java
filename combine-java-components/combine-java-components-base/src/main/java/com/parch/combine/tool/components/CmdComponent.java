package com.parch.combine.tool.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.tool.base.cmd.CmdErrorEnum;
import com.parch.combine.tool.base.cmd.CmdInitConfig;
import com.parch.combine.tool.base.cmd.CmdLogicConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(key = "cmd", name = "批量执行命令行组件", logicConfigClass = CmdLogicConfig.class, initConfigClass = CmdInitConfig.class)
@ComponentResult(name = "命令执行结果")
public class CmdComponent extends AbstractComponent<CmdInitConfig, CmdLogicConfig> {

    public CmdComponent() {
        super(CmdInitConfig.class, CmdLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        List<Map<String, Object>> results = new ArrayList<>();

        String[] commands = getLogicConfig().commands();
        for (String command : commands) {
            int exitCode = 0;
            List<String> lines = new ArrayList<>();
            try {
                if (CheckEmptyUtil.isEmpty(command)) {
                    continue;
                }

                ProcessBuilder processBuilder = new ProcessBuilder(command);
                Process process = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                exitCode = process.waitFor();
            } catch (Exception e) {
                PrintErrorHelper.print(CmdErrorEnum.FAIL, e);
                exitCode = -1;
            } finally {
                Map<String, Object> itemResult = new HashMap<>();
                itemResult.put("code", exitCode);
                itemResult.put("lines", lines);
                results.add(itemResult);
            }
        }

        return ComponentDataResult.success(results);
    }
}
