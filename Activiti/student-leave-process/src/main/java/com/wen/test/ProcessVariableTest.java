package com.wen.test;

import com.wen.model.Student;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.Date;

/**
 * Created by wen on 2018/6/27
 */
public class ProcessVariableTest {

    /**
     * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml文件
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署定义
     */
    @Test
    public void deploy(){
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("diagrams/StudentLeaveProcess.bpmn")
                .addClasspathResource("diagrams/StudentLeaveProcess.png")
                .name("学生请假流程")
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署name：" + deployment.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void start(){
        //启动并获取流程实例
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("studentLeaveProcess");
        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("流程定义ID：" + processInstance.getProcessInstanceId());
    }

    /**
     * 设置流程变量及值
     *
     * 这里我们来说明下，首先要设置流程变量，我们需要获取Service，这里的话，TaskService可以设置变量，RuntimeService也可以设置流程变量。
     *
     * 假如节点不是任务节点的时候，我们只能用RuntimeService。接口、方法和TaskService一样；
     *
     * 这里我们设置了请假日期、请假天数、请假理由、请假对象。当然这里set变量的时候需要一个任务ID，大家可以从任务表里去找；
     *
     * 后面的变量都是key:value形式，这里的key当然也可以用中文，设置的时候也可以一次性设置多个变量，都有重载方法的，大家自行研究下；
     *
     * 我们执行下这个方法，会对应的把数据库查询到对应的流程变量表中，当然那个对象序列的话，特殊点，存到了字节文件表里去了；
     *
     * 当然这里还要讲一个概念，就是全局流程实例变量和任务节点本地变量；
     *
     * setVariableLocal 和 setVariable 前者仅仅在某个任务节点有作用 后者在整个流程实例都有效，一般开发用后者即可。
     */
    @Test
    public void setVariablesValue(){
        TaskService taskService = processEngine.getTaskService();
        String taskId = "102504";
        taskService.setVariableLocal(taskId, "days", 2); // 存储Integer类型
        taskService.setVariable(taskId, "date", new Date()); // 存储日期类型
        taskService.setVariable(taskId, "reason", "世界这么大，我想去看看~！");
        Student student = new Student();
        student.setId(1);
        student.setName("王小二");
        taskService.setVariable(taskId, "student", student); // 存储序列化对象
    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask(){
        processEngine.getTaskService().complete("102504");
    }

    /**
     * 获取流程变量以及值
     *
     * 天数days没取到，原因上面讲了，是任务节点本地变量，只有在前面那个节点作用域内有效，所以在这个节点是取不到的；
     */
    @Test
    public void getVariablesValue(){
        TaskService taskService = processEngine.getTaskService();
        String taskId = "107502";
        Integer days = (Integer) taskService.getVariable(taskId, "days");
        Date date = (Date) taskService.getVariable(taskId, "date");
        String reason = (String) taskService.getVariable(taskId, "reaason");
        Student student = (Student) taskService.getVariable(taskId, "student");
        System.out.println("请假天数：" + days);
        System.out.println("请假日期：" + date);
        System.out.println("请假原因：" + reason);
        System.out.println("请假对象：" + student);
    }
    /**
     * 继续执行completeTask方法 ，当然任务ID现在变成了107502
     */

}
