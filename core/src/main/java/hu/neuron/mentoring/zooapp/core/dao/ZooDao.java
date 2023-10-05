package hu.neuron.mentoring.zooapp.core.dao;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooDao extends JpaRepository<Zoo,Integer> {



}
