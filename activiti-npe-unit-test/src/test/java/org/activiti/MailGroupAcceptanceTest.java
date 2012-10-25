package org.activiti;

import static org.junit.Assert.assertNotNull;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class MailGroupAcceptanceTest {

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();
    
    @Test
    @Deployment(resources = {"org/activiti/test/mail-group-acceptance.bpmn20.xml"})
    public void test() throws InterruptedException {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("mail-group-acceptance");
        assertNotNull(processInstance);
        
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        
        Assert.assertEquals(1, activitiRule.getManagementService().createJobQuery().count());
        
        assertNotNull(task);
        
        Thread.sleep(5000);
        
        Assert.assertEquals(1, activitiRule.getManagementService().createJobQuery().count());
        
        Assert.assertTrue(true);
    }
}
