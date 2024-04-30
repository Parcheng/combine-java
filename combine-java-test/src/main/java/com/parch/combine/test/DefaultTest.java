package com.parch.combine.test;

import com.parch.combine.CombineWebStarter;
import com.parch.combine.test.handler.FlowHandler;
import com.parch.combine.test.handler.PrintHandler;
import com.parch.combine.test.handler.TestHandler;

/**
 * 测试入口类
 */
public class DefaultTest {

    public static void main(String[] args) {
        // 初始化组件
        CombineWebStarter.init("combine_web.json");

        // 流程初始化
        PrintHandler.start();
        FlowHandler.init("default/init_flow.json");
        FlowHandler.init("default/interceptor_flow.json");
        FlowHandler.init("default/db_mysql_flow.json");
        FlowHandler.init("default/data_create_flow.json");
        FlowHandler.init("default/data_edit_flow.json");
        FlowHandler.init("default/data_calc_flow.json");
        FlowHandler.init("default/data_filter_flow.json");
        FlowHandler.init("default/data_format_flow.json");
        FlowHandler.init("default/data_mapping_flow.json");
        FlowHandler.init("default/data_reset_flow.json");
        FlowHandler.init("default/data_verify_flow.json");
        FlowHandler.init("default/file_input_flow.json");
        FlowHandler.init("default/file_read_flow.json");
        FlowHandler.init("default/file_build_flow.json");
        FlowHandler.init("default/file_output_flow.json");
        //FlowHandler.init("default/call_api_flow.json");
        FlowHandler.init("default/call_flow_flow.json");
        FlowHandler.init("default/logic_judgment_flow.json");
        FlowHandler.init("default/logic_loop_flow.json");
        FlowHandler.init("default/logic_error_flow.json");
        // FlowHandler.init("default/mail_flow.json");


        // 执行测试
        PrintHandler.blank();
        PrintHandler.hr();
        TestHandler.test("default/init_test.json");
        TestHandler.test("default/interceptor_test.json");
        TestHandler.test("default/db_mysql_test.json");
        TestHandler.test("default/data_create_test.json");
        TestHandler.test("default/data_edit_test.json");
        TestHandler.test("default/data_calc_test.json");
        TestHandler.test("default/data_filter_test.json");
        TestHandler.test("default/data_format_test.json");
        TestHandler.test("default/data_mapping_test.json");
        TestHandler.test("default/data_reset_test.json");
        TestHandler.test("default/data_verify_test.json");
        TestHandler.test("default/file_input_test.json");
        TestHandler.test("default/file_read_test.json");
        TestHandler.test("default/file_build_test.json");
        TestHandler.test("default/file_output_test.json");
        //TestHandler.test("default/call_api_test.json");
        TestHandler.test("default/call_flow_test.json");
        TestHandler.test("default/logic_judgment_test.json");
        TestHandler.test("default/logic_loop_test.json");
        TestHandler.test("default/logic_error_test.json");
        // TestHandler.test("default/mail_test.json");
    }
}
