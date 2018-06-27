package com.wen.createTable;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * 通过流程引擎配置对象创建表
 *
 * Created by wen on 2018/6/27
 *
 */
public class CreateTable01 {

    @Test
    public void createTable(){
        /**
         * 引擎配置
         */
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        pec.setJdbcDriver("com.mysql.jdbc.Driver");
        pec.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti");
        pec.setJdbcUsername("root");
        pec.setJdbcPassword("123456");

        /**
         * false 不能自动创建表
         * create-drop 先删除表再创建表
         * true 自动创建和更新表
         */
        pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        /**
         * 获取流程引擎对象
         */
        ProcessEngine processEngine = pec.buildProcessEngine();
    }

}
