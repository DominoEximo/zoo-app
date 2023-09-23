package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;

import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class GondoZooDao implements Dao<GondoZoo>{

    EntityManagerFactory emf = EntityManagement.getInstance().getEmf();
    EntityManager em = EntityManagement.getInstance().getEm();

    public GondoZooDao(){}

    public void connect(){}
    @Override
    public GondoZoo findById(int id) {
        GondoZoo employee = em.find(GondoZoo.class, id);
        if (employee == null) {
            throw new EntityNotFoundException("Can't find Employee for ID "
                    + id);
        }
        return employee;
    }

    public List<Employee> findByZoo(Zoo zoo){
        List<Employee> employees = getAll().stream()
                .filter(e -> zoo.getId().equals(e.getZoo().getId()))
                .collect(Collectors.toList());

        return  employees;
    }

    @Override
    public List<GondoZoo> getAll() {
        List<GondoZoo> employees = em.createQuery("Select gondozoo from GondoZoo gondozoo",GondoZoo.class).getResultList();

        return employees;
    }

    public void save(Employee newEmployee) {
        em.getTransaction().begin();
        if(em.contains(newEmployee)){
            em.merge(newEmployee);
        }else {
            em.persist(newEmployee);
        }
        em.getTransaction().commit();

    }

    @Override
    public void update(GondoZoo employee, String[] params) {

    }

    @Override
    public void delete(GondoZoo employee) {
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }
}
