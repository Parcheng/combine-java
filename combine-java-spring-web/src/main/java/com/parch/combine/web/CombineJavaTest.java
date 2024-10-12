package com.parch.combine.web;

import com.parch.combine.core.component.tools.thread.ThreadPoolTool;
import com.parch.combine.web.service.CombineJavaTestService;

import java.util.HashMap;
public class CombineJavaTest {

    public static void main(String[] args) {
        CombineJavaTestService test = new CombineJavaTestService();

        test.call(new HashMap<>(), new HashMap<>(), "common", "interceptor");

        test.call(new HashMap<>(), new HashMap<>(), "base", "call");
        test.call(new HashMap<>(), new HashMap<>(), "base", "data");
        test.call(new HashMap<>(), new HashMap<>(), "base", "file");
        test.call(new HashMap<>(), new HashMap<>(), "base", "logic");
        test.call(new HashMap<>(), new HashMap<>(), "base", "tool");
        //test.call(new HashMap<>(), new HashMap<>(), "base", "mail");

        //test.call(new HashMap<>(), new HashMap<>(), "mysql", "execute");

        ThreadPoolTool.closeAll();
    }
}
