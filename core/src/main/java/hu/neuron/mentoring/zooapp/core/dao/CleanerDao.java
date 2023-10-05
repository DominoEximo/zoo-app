package hu.neuron.mentoring.zooapp.core.dao;

import hu.neuron.mentoring.zooapp.core.entity.Cleaner;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CleanerDao extends JpaRepository<Cleaner,Integer> {



    public List<Employee> findByZoo_id(Integer zooId);


}

