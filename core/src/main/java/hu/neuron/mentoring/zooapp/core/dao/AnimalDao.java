package hu.neuron.mentoring.zooapp.core.dao;

import hu.neuron.mentoring.zooapp.core.entity.Animal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface AnimalDao extends JpaRepository<Animal,Integer> {



    public List<Animal> findByZoo_id(Integer zooId);



}
