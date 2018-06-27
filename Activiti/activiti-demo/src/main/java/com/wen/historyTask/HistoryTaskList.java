package com.wen.historyTask;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.Test;

import java.util.List;

/**
 * Created by wen on 2018/6/27
 */
public class HistoryTaskList {
    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件中的配置
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void historyTaskList(){
        List<HistoricTaskInstance> historicTaskInstanceList = processEngine.getHistoryService() // 历史任务Service
                .createHistoricTaskInstanceQuery() // 创建历史任务查询
                .taskAssignee("tuyamanke") // 指定办理人
                .finished() // 查询已经完成的任务
                .list();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList){
            System.out.println("任务ID:" + historicTaskInstance.getId());
            System.out.println("流程实例ID:" + historicTaskInstance.getProcessInstanceId());
            System.out.println("办理人：" + historicTaskInstance.getAssignee());
            System.out.println("创建时间：" + historicTaskInstance.getCreateTime());
            System.out.println("结束时间：" + historicTaskInstance.getEndTime());
            System.out.println("===========================");
        }
    }
}
