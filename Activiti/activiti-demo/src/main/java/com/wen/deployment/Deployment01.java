package com.wen.deployment;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by wen on 2018/6/27
 */
public class Deployment01 {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义 用ClassPath方式
     */
    @Test
    public void deployWithClassPath(){
        // 获取部署对象
        Deployment deployment = processEngine.getRepositoryService() // 获取部署Service
                .createDeployment() // 创建部署对象
                .addClasspathResource("diagrams/helloWorld.bpmn") // 加载资源文件
                .addClasspathResource("diagrams/helloWorld.png") // 加载资源文件
                .name("HelloWorld流程") // 设置流程名称
                .deploy(); // 部署
        System.out.println("流程部署ID：" + deployment.getId());
        System.out.println("流程部署Name：" + deployment.getName());
    }

    /**
     * 部署流程定义 用zip方式
     */
    @Test
    public void deployWithZip(){
        InputStream inputStream = this.getClass() // 获取当前class对象
                .getClassLoader() // 获取类加载器
                .getResourceAsStream("diagrams/helloWorld.zip"); // 获取指定文件资源流
        ZipInputStream zipInputStream = new ZipInputStream(inputStream); // 实例化zip输入流对象
        Deployment deployment = processEngine.getRepositoryService() // 获取部署Service
                .createDeployment() // 创建部署对象
                .name("HelloWorld流程2") // 设置流程名称
                .addZipInputStream(zipInputStream) // 添加zip输入流
                .deploy(); // 部署
        System.out.println("流程部署ID：" + deployment.getId());
        System.out.println("流程部署Name：" + deployment.getName());
    }
}
