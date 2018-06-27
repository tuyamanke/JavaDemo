package com.wen.deployment;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * Activiti之HelloWorld实现
 *
 * Created by wen on 2018/6/27
 */
public class HelloWorld {

    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义
     */
    @Test
    public void deploy(){
        //获取部署对象
        Deployment deployment = processEngine.getRepositoryService() // 部署 Service
                .createDeployment() // 创建部署
                .addClasspathResource("diagrams/helloWorld.bpmn") // 加载资源文件
                .addClasspathResource("diagrams/helloWorld.png")
                .name("HelloWorld流程") // 流程名称
                .deploy(); //部署
        System.out.println("流程部署ID：" + deployment.getId());
        System.out.println("流程部署Name：" + deployment.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void start(){
        //启动并获取流程实例
        ProcessInstance processInstance = processEngine.getRuntimeService() //运行时流程实例Service
                .startProcessInstanceByKey("myFirstProcess"); //流程定义表的Key字段值
        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
    }

    /**
     * 查询任务
     */
    @Test
    public void findTask(){
        List<Task> taskList = processEngine.getTaskService() // 任务相关Service
                .createTaskQuery() //创建任务查询
                .taskAssignee("tuyamanke") // 指定某个人
                .list();
        for (Task task : taskList){
            System.out.println("任务ID：" + task.getId());
            System.out.println("任务名称：" + task.getName());
            System.out.println("任务创建时间：" + task.getCreateTime());
            System.out.println("任务委派人：" + task.getAssignee());
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
        }
    }

    /**
     * 结束任务
     */
    @Test
    public void completeTask(){
        processEngine.getTaskService().complete("27504");
    }

}
