package com.wen.historyProcess;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.junit.Test;

/**
 * Created by wen on 2018/6/27
 */
public class HistoryProcess {

    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void getHistoryProcessInstance(){
        HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processInstanceId("2501")
                .singleResult();
        System.out.println("流程实例ID:"+historicProcessInstance.getId());
        System.out.println("创建时间："+historicProcessInstance.getStartTime());
        System.out.println("结束时间："+historicProcessInstance.getEndTime());    }
}
