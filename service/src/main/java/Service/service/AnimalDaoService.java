package Service.service;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.List;

public interface AnimalDaoService {

    List<Animal> getAll();

    Animal findById(int id);

    void save(Animal animal);

    void delete(Animal animal);
}
