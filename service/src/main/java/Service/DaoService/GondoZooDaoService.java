package Service.DaoService;

import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.GondoZoo;

import java.util.List;

public interface GondoZooDaoService {

    List<GondoZoo> getAll();

    GondoZoo findById(int id);

    void save(GondoZoo gondoZoo);

    void delete(GondoZoo gondoZoo);

    public List<Employee> findByZoo(Integer zooId);
}
