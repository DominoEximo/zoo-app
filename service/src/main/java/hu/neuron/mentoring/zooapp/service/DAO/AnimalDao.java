package hu.neuron.mentoring.zooapp.service.DAO;

import com.beust.ah.A;
import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;
import hu.neuron.mentoring.zooapp.service.Species;
import hu.neuron.mentoring.zooapp.service.Zoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalDao implements Dao<Animal>{

    private Connection conn;
    private PreparedStatement myStmt = null;
    private ResultSet zooResult = null;

    EntityManagerFactory emf = EntityManagement.getInstance().getEmf();
    EntityManager em = EntityManagement.getInstance().getEm();

    public AnimalDao(){}

    public void connect(Connection conn){
        this.conn = conn;
    }
    @Override
    public Animal findById(int id) {

        Animal animal = em.find(Animal.class, id);
        if (animal == null) {
            throw new EntityNotFoundException("Can't find Animal for ID "
                    + id);
        }
        return animal;
    }

    public List<Animal> findbyZoo(Zoo zoo){
        List<Animal> animals = getAll().stream()
                .filter(e -> zoo.getId().equals(e.getZoo().getId()))
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
