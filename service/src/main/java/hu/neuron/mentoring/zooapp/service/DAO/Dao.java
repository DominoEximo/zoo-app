package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Dao<T>{


    T findById(int id);

    List<T> getAll();
    void update(T t, String[] params);

    void delete(T t);
}
