
package com.bionic.freelanceit.idao;

import com.bionic.freelanceit.entity.Task;
import java.util.ArrayList;

public interface ITaskDAO {
    public Task add(Task task);
    public Task findById(int id);
    public ArrayList<Task> findByOwner(int id);
    public ArrayList<Task> findByExecutor(int id);
    public ArrayList<Task> findAll();
    public ArrayList<Task> findAllActive(Boolean active);
    public ArrayList<Task> findAllDone(Boolean done);
    public void update(Task task);
}
