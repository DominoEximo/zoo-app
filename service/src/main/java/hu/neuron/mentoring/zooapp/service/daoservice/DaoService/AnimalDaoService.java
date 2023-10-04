package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalDaoService {

    List<Animal> getAll();

    Animal findById(int id);

    void save(Animal animal);

    void delete(Animal animal);

    public List<Animal> findByZoo(Integer zooId);
}
