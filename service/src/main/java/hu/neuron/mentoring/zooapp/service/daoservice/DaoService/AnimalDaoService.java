package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.entity.Animal;

import java.util.List;


public interface AnimalDaoService {

    List<Animal> getAll();

    Animal findById(int id);

    void save(Animal animal);

    void delete(Animal animal);

    public List<Animal> findByZoo(Integer zooId);
}
