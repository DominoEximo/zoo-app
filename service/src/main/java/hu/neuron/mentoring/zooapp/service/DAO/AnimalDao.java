package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AnimalDao extends JpaRepository<Animal,Integer> {



    public List<Animal> findByZoo_id(Integer zooId);



}
