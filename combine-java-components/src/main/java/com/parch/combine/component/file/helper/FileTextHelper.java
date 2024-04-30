package com.parch.combine.component.file.helper;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.read.FileDataReadLogicConfig;
import com.parch.combine.core.error.ComponentErrorHandler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件文本处理
 */
public class FileTextHelper {

    /**
     * 构建文本文件
     *
     * @param textData 文件数据
     * @return 结果
     */
    public static byte[] build(List<String> textData) {
        String data = "";
        for (String item : textData) {
            data += (item + "\n");
        }
        return data.getBytes();
    }

    /**
     * 读取
     *
     * @param data 文件数据
     * @return 文本
     */
    public static List<String> read(byte[] data, FileDataReadLogicConfig config) {
        List<String> result = new ArrayList<>();

        int startRowIndex = config.getStartRow() - 1;
        int startIndex = config.getStartIndex()  - 1;
        int startSkipCount = config.getStartSkipCount() + startIndex;
        int endDiscardCount = config.getEndDiscardCount();

        int currRow = 0;
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = in.readLine()) != null) {
                // 跳过前startRowIndex行
                if (currRow < startRowIndex) {
                    currRow++;
                    continue;
                }

                // 跳过前 startSkipCount个字符，忽略掉最后endDiscardCount个字符
                if (line.length() > startSkipCount + endDiscardCount) {
                    line = line.substring(startSkipCount, line.length() - endDiscardCount);
                } else {
                    line = CheckEmptyUtil.EMPTY;
                }

                result.add(line);
                currRow++;
            };
        } catch (IOException e) {
            ComponentErrorHandler.print("读取文件异常", e);
        }

        return result;
    }
}
