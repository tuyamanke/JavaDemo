package com.wen.createTable;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 通过xml配置文件创建表
 *
 * Created by wen on 2018/6/27
 */
public class CreateTable02 {
    @Test
    public void createTableWithXml(){
        //流程引擎配置
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //构建流程引擎对象
        ProcessEngine processEngine = pec.buildProcessEngine();
    }
}
