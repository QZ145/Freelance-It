
package com.bionic.freelanceit.idao;

import java.util.ArrayList;

public interface IUserTypesDAO {
    public void add(Integer id, String type);
    public ArrayList<Integer> findByType(String type);
    public String findById(int id);
    public void setType(int id, String type);
}
