package com.bionic.freelanceit.dao;

import com.bionic.freelanceit.entity.Task;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TaskDAO implements ITaskDAO {

    private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    private final String QUERY_SELECT = "SELECT * FROM Task";
    private final String ID = "id";
    private final String DESCRIPTION = "description";
    private final String TITLE = "title";
    private final String STATUS = "status";
    private final String DONE = "done";

    public TaskDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public Task add(Task taskBean) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FreelanceItPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(taskBean);
        tx.commit();
        em.close();
        emf.close();
        return taskBean;

    }

//    @Override
//    public Task findById(int id) {
//        Task task = null;
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement(QUERY_SELECT + "WHERE idtask=?;");
//            resultSet = ptmt.executeQuery();
//            resultSet.next();
//            task = new Task();
//            task.setId(resultSet.getLong(ID));
//            task.setTitle(resultSet.getString(TITLE));
//            task.setDescription(resultSet.getString(DESCRIPTION));
//            task.setStatus(resultSet.getBoolean(STATUS));
//            task.setDone(resultSet.getBoolean(DONE));
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return task;
//        
//    }
//    
//    @Override
//    public ArrayList<Task> findByOwner(int id) {
//        ArrayList taskList = new ArrayList();
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE id_owner=?;");
//            ptmt.setInt(1, id);
//            resultSet = ptmt.executeQuery();
//            while (resultSet.next()) {
//                Task task = new Task();
//                task.setDescription(resultSet.getString(DESCRIPTION));
//                task.setTitle(resultSet.getString(TITLE));
//                task.setIdtask(resultSet.getInt(ID_TASK));
//                task.setIdOwner(resultSet.getInt(ID_OWNER));
//                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
//                task.setActive(resultSet.getBoolean(STATUS));
//                task.setDone(resultSet.getBoolean(DONE));
//                taskList.add(task);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return taskList;
//    }
//    
//    @Override
//    public ArrayList<Task> findByExecutor(int id) {
//        ArrayList taskList = new ArrayList();
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE id_executor=?;");
//            ptmt.setInt(1, id);
//            resultSet = ptmt.executeQuery();
//            while (resultSet.next()) {
//                Task task = new Task();
//                task.setDescription(resultSet.getString(DESCRIPTION));
//                task.setTitle(resultSet.getString(TITLE));
//                task.setIdtask(resultSet.getInt(ID_TASK));
//                task.setIdOwner(resultSet.getInt(ID_OWNER));
//                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
//                task.setActive(resultSet.getBoolean(STATUS));
//                task.setDone(resultSet.getBoolean(DONE));
//                taskList.add(task);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return taskList;
//    }
//    
//    @Override
//    public ArrayList<Task> findAll() {
//        ArrayList taskList = new ArrayList();
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement(QUERY_SELECT + ";");
//            resultSet = ptmt.executeQuery();
//            while (resultSet.next()) {
//                Task task = new Task();
//                task.setDescription(resultSet.getString(DESCRIPTION));
//                task.setTitle(resultSet.getString(TITLE));
//                task.setIdtask(resultSet.getInt(ID_TASK));
//                task.setIdOwner(resultSet.getInt(ID_OWNER));
//                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
//                task.setActive(resultSet.getBoolean(STATUS));
//                task.setDone(resultSet.getBoolean(DONE));
//                taskList.add(task);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return taskList;
//    }
//    
//    @Override
//    public ArrayList<Task> findAllActive(Boolean active) {
//        ArrayList taskList = new ArrayList();
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement(QUERY_SELECT + "WHERE active=?;");
//            ptmt.setBoolean(1, active);
//            resultSet = ptmt.executeQuery();
//            while (resultSet.next()) {
//                Task task = new Task();
//                task.setDescription(resultSet.getString(DESCRIPTION));
//                task.setTitle(resultSet.getString(TITLE));
//                task.setIdtask(resultSet.getInt(ID_TASK));
//                task.setIdOwner(resultSet.getInt(ID_OWNER));
//                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
//                task.setActive(resultSet.getBoolean(STATUS));
//                task.setDone(resultSet.getBoolean(DONE));
//                taskList.add(task);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return taskList;
//    }
//    
//    @Override
//    public ArrayList<Task> findAllDone(Boolean done) {
//        ArrayList taskList = new ArrayList();
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement(QUERY_SELECT + "WHERE done=?;");
//            ptmt.setBoolean(1, done);
//            resultSet = ptmt.executeQuery();
//            while (resultSet.next()) {
//                Task task = new Task();
//                task.setDescription(resultSet.getString(DESCRIPTION));
//                task.setTitle(resultSet.getString(TITLE));
//                task.setIdtask(resultSet.getInt(ID_TASK));
//                task.setIdOwner(resultSet.getInt(ID_OWNER));
//                task.setIdExecutor(resultSet.getInt(ID_EXECUTOR));
//                task.setActive(resultSet.getBoolean(STATUS));
//                task.setDone(resultSet.getBoolean(DONE));
//                taskList.add(task);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return taskList;
//    }
//    
//    @Override
//    public void update(Task task) {
//        try {
//            connection = getConnection();
//            ptmt = connection.prepareStatement("UPDATE Task SET description=? title=? id_executor=?"
//                    + " active=? done=? WHERE idtask=?;");
//            ptmt.setString(1, task.getDescription());
//            ptmt.setString(2, task.getTitle());
//            ptmt.setInt(3, task.getIdExecutor());
//            ptmt.setBoolean(4, task.getActive());
//            ptmt.setBoolean(5, task.getDone());
//            ptmt.setInt(6, task.getIdtask());
//            ptmt.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    @Override
    public Task findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> findByOwner(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> findByExecutor(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> findAllActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> findAllDone(Boolean done) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
