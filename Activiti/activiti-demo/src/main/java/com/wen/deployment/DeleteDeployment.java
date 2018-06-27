package com.wen.deployment;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

import java.util.List;

/**
 * Created by wen on 2018/6/27
 */
public class DeleteDeployment {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 删除流程部署
     */
    @Test
    public void delete(){
        processEngine.getRepositoryService()
                // 根据ID删除部署 true为级联删除，默认为false 假如这个部署的流程实例在运行活动中，未完结，有主外键关联，需要级联删除
                .deleteDeployment("45001", true);
        System.out.println("部署删除成功~！");
    }

    @Test
    public void deleteByKey(){
        List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService() // 获取service
                .createProcessDefinitionQuery() // 创建流程定义查询
                .processDefinitionKey("mySecondProcess") // 根据Key查询
                .list();
        for (ProcessDefinition pd : processDefinitionList){ // 遍历集合 获取流程定义的每个部署id，根据这个id来删除所有流程定义
            processEngine.getRepositoryService()
                    .deleteDeployment(pd.getDeploymentId(), true); // true为级联删除，默认为false
        }
    }
}
