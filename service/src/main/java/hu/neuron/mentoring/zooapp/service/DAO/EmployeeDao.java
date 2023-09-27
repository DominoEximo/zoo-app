package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

    public List<Employee> findByZoo(Zoo zoo);
}
