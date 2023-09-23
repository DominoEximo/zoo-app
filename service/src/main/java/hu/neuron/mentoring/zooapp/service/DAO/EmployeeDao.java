package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDao implements Dao<Employee>{

    EntityManagerFactory emf = EntityManagement.getInstance().getEmf();
    EntityManager em = EntityManagement.getInstance().getEm();

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

    public List<Employee> findByZoo(Zoo zoo){
        List<Employee> employees = getAll().stream()
                .filter(e -> zoo.getId().equals(e.getZoo().getId()))
                .collect(Collectors.toList());

        return  employees;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = em.createQuery("Select gondozoo from GondoZoo gondozoo",Employee.class).getResultList();
        List<Employee> employees1 = em.createQuery("Select cleaner from Cleaner cleaner",Employee.class).getResultList();
        employees.addAll(employees1);

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
    public void update(Employee employee, String[] params) {

    }

    @Override
    public void delete(Employee employee) {
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }
}
