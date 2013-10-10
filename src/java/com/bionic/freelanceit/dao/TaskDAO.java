package com.bionic.freelanceit.dao;

import com.bionic.freelanceit.bean.Task;
import com.bionic.freelanceit.factory.ConnectionFactory;
import com.bionic.freelanceit.idao.ITaskDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDAO implements ITaskDAO {
    
    private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    private final String QUERY_SELECT = "SELECT * FROM Task";
    private final String ID_TASK = "idtask";
    private final String DESCRIPTION = "description";
    private final String TITLE = "title";
    private final String ID_EXECUTOR = "id_executor";
    private final String ID_OWNER = "id_owner";
    private final String ACTIVE = "active";
    private final String DONE = "done";
    
    public TaskDAO() {
        
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    @Override
    public void add(Task taskBean) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("INSERT INTO Task(description, title, id_owner) VALUES(?,?,?);");
            ptmt.setString(1, taskBean.getDescription());
            ptmt.setString(2, taskBean.getTitle());
            ptmt.setInt(3, taskBean.getIdOwner());
            ptmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @Override
    public Task findById(int id) {
        Task task = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + "WHERE idtask=?;");
            resultSet = ptmt.executeQuery();
            resultSet.next();
            task = new Task(id);
            task.setTitle(resultSet.getString(TITLE));
            task.setDescription(resultSet.getString(DESCRIPTION));
            task.setIdOwner(resultSet.getInt(ID_OWNER));
            task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
            task.setActive(resultSet.getBoolean(ACTIVE));
            task.setDone(resultSet.getBoolean(DONE));
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return task;
        
    }
    
    @Override
    public ArrayList<Task> findByOwner(int id) {
        ArrayList taskList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE id_owner=?;");
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setDescription(resultSet.getString(DESCRIPTION));
                task.setTitle(resultSet.getString(TITLE));
                task.setIdtask(resultSet.getInt(ID_TASK));
                task.setIdOwner(resultSet.getInt(ID_OWNER));
                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
                task.setActive(resultSet.getBoolean(ACTIVE));
                task.setDone(resultSet.getBoolean(DONE));
                taskList.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return taskList;
    }
    
    @Override
    public ArrayList<Task> findByExecutor(int id) {
        ArrayList taskList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE id_executor=?;");
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setDescription(resultSet.getString(DESCRIPTION));
                task.setTitle(resultSet.getString(TITLE));
                task.setIdtask(resultSet.getInt(ID_TASK));
                task.setIdOwner(resultSet.getInt(ID_OWNER));
                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
                task.setActive(resultSet.getBoolean(ACTIVE));
                task.setDone(resultSet.getBoolean(DONE));
                taskList.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return taskList;
    }
    
    @Override
    public ArrayList<Task> findAll() {
        ArrayList taskList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + ";");
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setDescription(resultSet.getString(DESCRIPTION));
                task.setTitle(resultSet.getString(TITLE));
                task.setIdtask(resultSet.getInt(ID_TASK));
                task.setIdOwner(resultSet.getInt(ID_OWNER));
                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
                task.setActive(resultSet.getBoolean(ACTIVE));
                task.setDone(resultSet.getBoolean(DONE));
                taskList.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return taskList;
    }
    
    @Override
    public ArrayList<Task> findAllActive(Boolean active) {
        ArrayList taskList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + "WHERE active=?;");
            ptmt.setBoolean(1, active);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setDescription(resultSet.getString(DESCRIPTION));
                task.setTitle(resultSet.getString(TITLE));
                task.setIdtask(resultSet.getInt(ID_TASK));
                task.setIdOwner(resultSet.getInt(ID_OWNER));
                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
                task.setActive(resultSet.getBoolean(ACTIVE));
                task.setDone(resultSet.getBoolean(DONE));
                taskList.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return taskList;
    }
    
    @Override
    public ArrayList<Task> findAllDone(Boolean done) {
        ArrayList taskList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + "WHERE done=?;");
            ptmt.setBoolean(1, done);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setDescription(resultSet.getString(DESCRIPTION));
                task.setTitle(resultSet.getString(TITLE));
                task.setIdtask(resultSet.getInt(ID_TASK));
                task.setIdOwner(resultSet.getInt(ID_OWNER));
                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
                task.setActive(resultSet.getBoolean(ACTIVE));
                task.setDone(resultSet.getBoolean(DONE));
                taskList.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return taskList;
    }
    
    @Override
    public void update(Task task) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("UPDATE Task SET description=? title=? id_executor=?"
                    + " active=? done=? WHERE idtask=?;");
            ptmt.setString(1, task.getDescription());
            ptmt.setString(2, task.getTitle());
            ptmt.setInt(3, task.getIdExecutor());
            ptmt.setBoolean(4, task.getActive());
            ptmt.setBoolean(5, task.getDone());
            ptmt.setInt(6, task.getIdtask());
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
