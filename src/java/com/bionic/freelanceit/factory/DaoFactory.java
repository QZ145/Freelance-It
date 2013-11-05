
package com.bionic.freelanceit.factory;

import com.bionic.freelanceit.dao.TaskDAO;
import com.bionic.freelanceit.dao.UserDAO;
import com.bionic.freelanceit.dao.UserTaskDAO;

public class DaoFactory {
    public static UserDAO getUserDAO() {
        return new UserDAO();
    }
    
    public static TaskDAO getTaskDAO() {
        return new TaskDAO();
    }
    
    public static UserTaskDAO getUserTaskDAO() {
        return new UserTaskDAO();
    }
}
