package com.wen.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

import java.util.List;

/**
 * Created by wen on 2018/6/27
 */
public class FindProcessByKey {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    /**
     * 查询流程定义，返回流程定义集合 -- 对应 act_re_procdef 表
     */
    @Test
    public void getProcessDefinitionListByKey(){
        List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService() // 获取Service
                .createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionKey("myFirstProcess") // 设置作为查询依据的Key
                .list(); // 返回一个集合
        for (ProcessDefinition processDefinition : processDefinitionList){
            System.out.println("流程定义ID：" + processDefinition.getId());
            System.out.println("流程定义名称：" + processDefinition.getName());
            System.out.println("流程定义Key：" + processDefinition.getKey());
            System.out.println("流程定义版本：" + processDefinition.getVersion());
            System.out.println("===================================");
        }
    }

    /**
     * 通过id查询某个流程定义
     */
    @Test
    public void getProcessById(){
        ProcessDefinition processDefinition = processEngine.getRepositoryService() // 获取Service
                .createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionId("myFirstProcess:1:4") // 设置作为查询依据的ID
                .singleResult(); // 查询返回单个结果
        System.out.println("流程定义ID：" + processDefinition.getId());
        System.out.println("流程定义名称：" + processDefinition.getName());
        System.out.println("流程定义Key：" + processDefinition.getKey());
        System.out.println("流程定义版本：" + processDefinition.getVersion());
    }
}
