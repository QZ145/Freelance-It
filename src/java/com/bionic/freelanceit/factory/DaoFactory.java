
package com.bionic.freelanceit.factory;

import com.bionic.freelanceit.dao.TaskDAO;
import com.bionic.freelanceit.dao.TaskExecutorsDAO;
import com.bionic.freelanceit.dao.UserDAO;
import com.bionic.freelanceit.dao.UserTypesDAO;

public class DaoFactory {
    public static UserDAO getUserDao() {
        return new UserDAO();
    }
    
    public static TaskDAO getTaskDao() {
        return new TaskDAO();
    }
    
    public static UserTypesDAO getUserTypesDAO() {
        return new UserTypesDAO();
    }
    
    public static TaskExecutorsDAO getTaskExecutorsDAO() {
        return new TaskExecutorsDAO();
    }
}
