package hu.neuron.mentoring.zooapp.core.dao;

import hu.neuron.mentoring.zooapp.core.Animal;
import org.hibernate.annotations.Checks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AnimalDao extends JpaRepository<Animal,Integer> {



    public List<Animal> findByZoo_id(Integer zooId);



}
