
package com.bionic.freelanceit.idao;

import java.util.ArrayList;

public interface IUserTypeDAO {
    public void add(Long id, String type);
    public ArrayList<Long> findByType(String type);
    public String findById(int id);
    public void setType(int id, String type);
}
