package hu.neuron.mentoring.zooapp.service.DAO;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ZooDao extends CrudRepository<Zoo,Integer> {



}
