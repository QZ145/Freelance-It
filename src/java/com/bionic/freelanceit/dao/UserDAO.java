package com.bionic.freelanceit.dao;

import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.factory.ConnectionFactory;
import com.bionic.freelanceit.idao.IUserDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO {

    private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    private final String QUERY_SELECT = "SELECT * FROM user";
    public final String ID = "id";
    public final String NAME = "Name";
    public final String LAST_NAME = "LastName";
    public final String LOGIN = "Login";
    public final String EMAIL = "email";
    public final String BIRTHDAY = "Birthday";
    public final String DATE_OF_REGISTRATION = "DateOfRegistration";
    public final String PASSWORD = "password";
    public final String ACTIVE = "active";

    public UserDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public void add(User userBean) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("INSERT INTO user(Name, Login, email, DateOfRegistration, password) "
                    + "VALUES(?,?,?,?,?);");
            ptmt.setString(1, userBean.getName());
            ptmt.setString(2, userBean.getLogin());
            ptmt.setString(3, userBean.getEmail());
            ptmt.setDate(4, (Date) userBean.getDateOfRegistration());
            ptmt.setString(5, userBean.getPassword());
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

    @Override
    public ArrayList<User> findAll() {
        ArrayList userList = new ArrayList();
        
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + ";");
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setActive(resultSet.getBoolean(ACTIVE));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setLogin(resultSet.getString(LOGIN));
                user.setName(resultSet.getString(NAME));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setBirthday(resultSet.getDate(BIRTHDAY));
                user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));
                userList.add(user);
            }
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

        return userList;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE id =?;");
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            user = new User(id);
            user.setActive(resultSet.getBoolean(ACTIVE));
            user.setEmail(resultSet.getString(EMAIL));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setLogin(resultSet.getString(LOGIN));
            user.setName(resultSet.getString(NAME));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setBirthday(resultSet.getDate(BIRTHDAY));
            user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));

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
        return user;
    }

    @Override
    public ArrayList<User> findByName(String Name) {
        ArrayList userList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE Name =?;");
            ptmt.setString(1, Name);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setActive(resultSet.getBoolean(ACTIVE));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setLogin(resultSet.getString(LOGIN));
                user.setName(resultSet.getString(NAME));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setBirthday(resultSet.getDate(BIRTHDAY));
                user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));

                userList.add(user);
            }
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
        return userList;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE email =?;");
            ptmt.setString(1, email);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt(ID));
            user.setActive(resultSet.getBoolean(ACTIVE));
            user.setEmail(resultSet.getString(EMAIL));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setLogin(resultSet.getString(LOGIN));
            user.setName(resultSet.getString(NAME));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setBirthday(resultSet.getDate(BIRTHDAY));
            user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));
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
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE login =?;");
            ptmt.setString(1, login);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt(ID));
            user.setActive(resultSet.getBoolean(ACTIVE));
            user.setEmail(resultSet.getString(EMAIL));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setLogin(resultSet.getString(LOGIN));
            user.setName(resultSet.getString(NAME));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setBirthday(resultSet.getDate(BIRTHDAY));
            user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));

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
        return user;
    }

    @Override
    public ArrayList<User> findAllActive(Boolean active) {
        ArrayList userList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE active=?;");
            ptmt.setBoolean(1, active);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setActive(resultSet.getBoolean(ACTIVE));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setLogin(resultSet.getString(LOGIN));
                user.setName(resultSet.getString(NAME));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setBirthday(resultSet.getDate(BIRTHDAY));
                user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));

                userList.add(user);
            }
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
        return userList;
    }

    @Override
    public void update(User userBean) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement("UPDATE user SET Name=? LastName=? email=? Birthday=? password=?"
                    + " active=? WHERE id=?");
            ptmt.setString(1, userBean.getName());
            ptmt.setString(2, userBean.getLastName());
            ptmt.setString(3, userBean.getEmail());
            ptmt.setDate(4, (Date) userBean.getBirthday());
            ptmt.setString(5, userBean.getPassword());
            ptmt.setBoolean(6, userBean.getActive());
            ptmt.setInt(7, userBean.getId());
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

    @Override
    public ArrayList<User> findByBirthday(Date date) {
        ArrayList userList = new ArrayList();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE Birthday=?;");
            ptmt.setDate(1, date);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setActive(resultSet.getBoolean(ACTIVE));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setLogin(resultSet.getString(LOGIN));
                user.setName(resultSet.getString(NAME));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setBirthday(resultSet.getDate(BIRTHDAY));
                user.setDateOfRegistration(resultSet.getDate(DATE_OF_REGISTRATION));

                userList.add(user);
            }
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
        return userList;
    }

    @Override
    public Boolean find(String login, String password) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(QUERY_SELECT + " WHERE Login=? AND password=?;");
            ptmt.setString(1, login);
            ptmt.setString(2, password);
            resultSet = ptmt.executeQuery();
            return resultSet.next();

        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
                return false;
            }
        }
    }
}
