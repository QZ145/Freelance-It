/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.mdb;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.UserTask;
import com.bionic.freelanceit.entitybeans.task.TaskFacadeLocal;
import com.bionic.freelanceit.entitybeans.usertask.UserTaskFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author qz
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queueDestination")
})
public class TaskMessageBean implements MessageListener {
    
    @EJB
    TaskFacadeLocal taskFacade;
    
    @EJB
    UserTaskFacadeLocal userTaskFacade;
    
    public TaskMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        Message m = message;
        try {
            Task task = m.getBody(Task.class);
            taskFacade.create(task);
            UserTask userTask = m.getBody(UserTask.class);
            userTask.setTask(task);
            userTaskFacade.create(userTask);
        } catch (JMSException ex) {
            Logger.getLogger(TaskMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
