
package com.bionic.freelanceit.idao;

import com.bionic.freelanceit.entity.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {
    public User add(User user);
    public List<User> findAll();
    public User findById(Long id);
    public ArrayList<User> findByName(String Name);
    public User findByEmail(String email);
    public User findByLogin(String login);
    public void update(User user);
    public ArrayList<User> findAllActive(Boolean active);
    public ArrayList<User> findByBirthday(Date date);
    public Boolean find(String login, String password);
}
