package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class CleanerDao implements Dao<Cleaner>{

    EntityManagerFactory emf = EntityManagement.getInstance().getEmf();
    EntityManager em = EntityManagement.getInstance().getEm();

    public CleanerDao(){}

    @Override
    public Cleaner findById(int id) {
        Cleaner employee = em.find(Cleaner.class, id);
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
    public List<Cleaner> getAll() {
        List<Cleaner> employees = em.createQuery("Select cleaner from Cleaner cleaner",Cleaner.class).getResultList();
        return employees;
    }

    public void save(Cleaner newEmployee) {
        em.getTransaction().begin();
        if(em.contains(newEmployee)){
            em.merge(newEmployee);
        }else {
            em.persist(newEmployee);
        }
        em.getTransaction().commit();

    }

    @Override
    public void update(Cleaner employee, String[] params) {

    }

    @Override
    public void delete(Cleaner employee) {
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }
}

