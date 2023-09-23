package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;
import hu.neuron.mentoring.zooapp.service.Zoo;

import javax.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalDao implements Dao<Animal>{

    EntityManagerFactory emf = EntityManagement.getInstance().getEmf();
    EntityManager em = EntityManagement.getInstance().getEm();

    public AnimalDao(){}

    @Override
    public Animal findById(int id) {

        Animal animal = em.find(Animal.class, id);
        if (animal == null) {
            throw new EntityNotFoundException("Can't find Animal for ID "
                    + id);
        }
        return animal;
    }

    public List<Animal> findbyZoo(Optional<Zoo> zoo){
        Zoo zoo1 = zoo.get();
        List<Animal> animals = getAll().stream()
                .filter(e -> zoo1.getId().equals(e.getZoo().getId()))
                .collect(Collectors.toList());

        return  animals;
    }
    @Override
    public List<Animal> getAll() {
        List<Animal> animals = em.createQuery("Select animal from Animal animal",Animal.class).getResultList();


        return animals;
    }

    public void save(Animal animal){
        em.getTransaction().begin();
        if(em.contains(animal)){
            em.merge(animal);
        }else {
            em.persist(animal);
        }
        em.getTransaction().commit();

    }

    @Override
    public void update(Animal animal, String[] params) {

    }

    @Override
    public void delete(Animal animal) {
        em.getTransaction().begin();
        em.remove(animal);
        em.getTransaction().commit();
    }
}
