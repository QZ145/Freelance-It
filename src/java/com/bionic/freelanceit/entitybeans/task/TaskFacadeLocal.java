/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.task;

import com.bionic.freelanceit.entity.Task;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author qz
 */
@Local
public interface TaskFacadeLocal {

    void create(Task task);

    void edit(Task task);

    void remove(Task task);

    Task find(Object id);

    List<Task> findAll();

    List<Task> findRange(int[] range);

    int count();
    
}
