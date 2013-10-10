package com.bionic.freelanceit.dao;

import com.bionic.freelanceit.factory.ConnectionFactory;
import com.bionic.freelanceit.idao.IUserTypesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTypesDAO implements IUserTypesDAO {

    private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;

    public UserTypesDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public void add(Integer id, String type) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("INSERT INTO User_types(id_user, type) VALUES(?,?);");
            ptmt.setInt(1, id);
            ptmt.setString(2, type);
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public ArrayList<Integer> findByType(String type) {
        ArrayList list = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("SELECT id FROM Task WHERE type=?;");
            ptmt.setString(1, type);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    @Override
    public String findById(int id) {
        String type = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("SELECT id FROM Task WHERE type=?;");
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            type = resultSet.getString("type");
        } catch (SQLException ex) {
            Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return type;
    }

    @Override
    public void setType(int id, String type) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("UPDATE User_types SET type=? WHERE id_user=?;");
            ptmt.setInt(2, id);
            ptmt.setString(1, type);
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UserTypesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
