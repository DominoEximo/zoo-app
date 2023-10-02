package hu.neuron.mentoring.zooapp.service.Controller.Service.DaoService;

import hu.neuron.mentoring.zooapp.core.Cleaner;
import hu.neuron.mentoring.zooapp.core.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CleanerDaoService {

    List<Cleaner> getAll();

    Cleaner findById(int id);

    void save(Cleaner cleaner);

    void delete(Cleaner cleaner);

    public List<Employee> findByZoo(Integer zooId);
}
