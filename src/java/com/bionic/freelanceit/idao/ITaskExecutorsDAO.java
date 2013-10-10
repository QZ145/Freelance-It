
package com.bionic.freelanceit.idao;

import java.util.ArrayList;

public interface ITaskExecutorsDAO {
    public void add(int id_task, int id_user);
    public void update(int id_task, int id_user, Boolean active);
    public ArrayList<Integer> findExecutorsId(int id_task);
    public ArrayList<Integer> findTasksId(int id_user);
    public void setActiveForTask(int id_task, Boolean active);
}
