package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.entity.Cleaner;
import hu.neuron.mentoring.zooapp.core.entity.Employee;


import java.util.List;


public interface CleanerDaoService {

    List<Cleaner> getAll();

    Cleaner findById(int id);

    void save(Cleaner cleaner);

    void delete(Cleaner cleaner);

    public List<Employee> findByZoo(Integer zooId);
}
