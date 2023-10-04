package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.Employee;
import hu.neuron.mentoring.zooapp.core.GondoZoo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GondoZooDaoService {

    List<GondoZoo> getAll();

    GondoZoo findById(int id);

    void save(GondoZoo gondoZoo);

    void delete(GondoZoo gondoZoo);

    public List<Employee> findByZoo(Integer zooId);
}
