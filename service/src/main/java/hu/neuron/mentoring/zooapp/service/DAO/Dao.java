package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.List;

public interface Dao<T> {


    List<T> findById(int id);

    List<T> getAll();



    void update(T t, String[] params);

    void delete(T t);
}
