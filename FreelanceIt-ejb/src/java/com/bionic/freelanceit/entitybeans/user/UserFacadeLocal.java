/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.user;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author qz
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    User findByLogin(String login);

    List<Task> getTasksHeCreated(User user);

    User findByEmail(String email);

    int count();
}
