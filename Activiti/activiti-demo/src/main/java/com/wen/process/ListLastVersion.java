package com.wen.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2018/6/27
 */
public class ListLastVersion {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 查询最新版本的流程定义
     */
    @Test
    public void listLastVersion(){
        // 获取流程定义集合，根据Key升序排列
        List<ProcessDefinition> listAll = processEngine.getRepositoryService() // 获取service
                .createProcessDefinitionQuery() // 创建流程定义查询
                .orderByProcessDefinitionKey().asc() // 根据流程定义版本升序排列
                .list();
        // 定义有序Map，相同的Key添加Value，后添加的会覆盖前面添加的
        Map<String, ProcessDefinition> map = new LinkedHashMap<>();
        for (ProcessDefinition pd : listAll){
            map.put(pd.getKey(), pd);
        }
        List<ProcessDefinition> pdList = new LinkedList<>(map.values());
        for (ProcessDefinition pd : pdList){
            System.out.println("流程定义ID：" + pd.getId());
            System.out.println("流程定义名称：" + pd.getName());
            System.out.println("流程定义Key：" + pd.getKey());
            System.out.println("流程定义版本：" + pd.getVersion());
            System.out.println("===================================");
        }
    }
}
