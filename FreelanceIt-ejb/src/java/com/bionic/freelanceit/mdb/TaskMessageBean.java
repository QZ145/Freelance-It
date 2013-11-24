/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.mdb;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.UserTask;
import com.bionic.freelanceit.entitybeans.task.TaskFacadeLocal;
import com.bionic.freelanceit.entitybeans.usertask.UserTaskFacadeLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

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
        ObjectMessage m = (ObjectMessage) message;
        try {
            Task task = (Task) m.getObject();
            Collection<UserTask> utCollection = task.getUserTaskCollection();
            task.setUserTaskCollection(null);
            taskFacade.create(task);
            UserTask userTask = (UserTask) utCollection.toArray()[0];
            userTask.setTask(task);
            userTaskFacade.create(userTask);
        } catch (JMSException ex) {
            Logger.getLogger(TaskMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
