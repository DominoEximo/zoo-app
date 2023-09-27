package Service.Impl;

import Service.service.AnimalDaoService;
import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnimalDaoServiceImpl implements AnimalDaoService {

    @Autowired
    AnimalDao animalDao;

    @Override
    public List<Animal> getAll() {
        List<Animal> animals = null;
        animals = (List<Animal>) animalDao.findAll();
        return  animals;
    }

    @Override
    public Animal findById(int id) {
        Animal animal = null;
        animal = animalDao.findById(id).get();
        return animal;
    }

    @Override
    public void save(Animal animal) {
        animalDao.save(animal);
    }

    @Override
    public void delete(Animal animal) {
        animalDao.delete(animal);
    }

    @Override
    public List<Animal> findByZoo(Integer zooId) {
        List<Animal> animals = animalDao.findByZoo_id(zooId);
        return animals;
    }
}
