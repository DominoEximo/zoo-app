package Service.service;

import hu.neuron.mentoring.zooapp.service.Cleaner;
import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.GondoZoo;

import java.util.List;

public interface CleanerDaoService {

    List<Cleaner> getAll();

    Cleaner findById(int id);

    void save(Cleaner cleaner);

    void delete(Cleaner cleaner);

    public List<Employee> findByZoo(Integer zooId);
}
