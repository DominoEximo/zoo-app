package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeDao implements Dao<Employee>{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZooPU");
    EntityManager em = emf.createEntityManager();

    public EmployeeDao(){}

    public void connect(){}
    @Override
    public Employee findById(int id) {
        Employee employee = em.find(Employee.class, id);
        if (employee == null) {
            throw new EntityNotFoundException("Can't find Employee for ID "
                    + id);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }


    public void save(Employee newEmployee) {
        em.getTransaction().begin();
        em.persist(newEmployee);
        em.getTransaction().commit();
        emf.close();
        em.close();
    }

    @Override
    public void update(Employee employee, String[] params) {

    }

    @Override
    public void delete(Employee employee) {
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
        emf.close();
        em.close();
    }
}
