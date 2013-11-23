/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.usertask;

import com.bionic.freelanceit.entities.UserTask;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author qz
 */
@Local
public interface UserTaskFacadeLocal {

    void create(UserTask userTask);

    void edit(UserTask userTask);

    void remove(UserTask userTask);

    UserTask find(Object id);

    List<UserTask> findAll();

    List<UserTask> findRange(int[] range);

    int count();
}
