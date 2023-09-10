package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Employee;

import java.util.List;

public interface Dao<T> {


    T findById(int id);

    List<T> getAll();



    void update(T t, String[] params);

    void delete(T t);
}
