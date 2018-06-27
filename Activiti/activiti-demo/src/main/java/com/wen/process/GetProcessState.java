package com.wen.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * Created by wen on 2018/6/27
 */
public class GetProcessState {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void processState(){
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId("27501")
                .singleResult();
        if (processInstance != null){
            System.out.println("流程正在执行...");
        }else {
            System.out.println("流程已经执行结束~！");
        }
    }
}
