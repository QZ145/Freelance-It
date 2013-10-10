/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import com.bionic.freelanceit.dao.UserDAO;
import com.bionic.freelanceit.factory.DaoFactory;

/**
 *
 * @author qz
 */
public class Runner {
    public static void main(String[] args) {
        UserDAO userDao = DaoFactory.getUserDao();
//        System.out.println(userDao.find("root", "root"));
        System.out.print(userDao.findByLogin("root").getName());
    }
}
