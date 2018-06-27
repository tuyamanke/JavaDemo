package com.wen.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wen on 2018/6/27
 */
public class GetProcessImgById {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void getProcessImgById() throws IOException {
        InputStream inputStream = processEngine.getRepositoryService()
                .getResourceAsStream("10001", "helloWorld.png"); // 根据流程部署ID和资源名称获取输入流
        FileUtils.copyInputStreamToFile(inputStream, new File("D://helloWorld.png"));
    }
}
