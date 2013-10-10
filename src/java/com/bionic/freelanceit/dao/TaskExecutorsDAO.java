
package com.bionic.freelanceit.dao;

import com.bionic.freelanceit.factory.ConnectionFactory;
import com.bionic.freelanceit.idao.ITaskExecutorsDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskExecutorsDAO implements ITaskExecutorsDAO{
    
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    @Override
    public void add(int id_task, int id_user) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("INSERT INTO Task_executors(id_task, id_user) VALUES(?,?);");
            ptmt.setInt(1, id_task);
            ptmt.setInt(2, id_user);
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskExecutorsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(int id_task, int id_user, Boolean active) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("UPDATE Task_executors SET id_task=? id_user=? active=?"
                    + " WHERE id_task=? id_user=?;");
            ptmt.setInt(1, id_task);
            ptmt.setInt(2, id_user);
            ptmt.setBoolean(3, active);
            ptmt.setInt(4, id_task);
            ptmt.setInt(5, id_user);
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskExecutorsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public ArrayList<Integer> findExecutorsId(int id_task) {
        ArrayList list = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("SELECT id_user FROM Task_executors WHERE id_task=?");
            ptmt.setInt(1, id_task);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
                list.add(resultSet.getInt("id_user"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskExecutorsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return list;
    }

    @Override
    public ArrayList<Integer> findTasksId(int id_user) {
        ArrayList list = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("SELECT id_task FROM Task_executors WHERE id_user=?");
            ptmt.setInt(1, id_user);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
                list.add(resultSet.getInt("id_task"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskExecutorsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return list;
    }

    @Override
    public void setActiveForTask(int id_task, Boolean active) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("UPDATE Task_executors SET active=? WHERE id_task=?;");
            ptmt.setBoolean(1, active);
            ptmt.setInt(2, id_task);
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskExecutorsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
