package hu.neuron.mentoring.zooapp.core.dao;

import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.entity.GondoZoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GondoZooDao extends JpaRepository<GondoZoo,Integer> {

    public List<Employee> findByZoo_id(Integer zooId);
}
