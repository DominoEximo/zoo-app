package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ReservationDao extends JpaRepository<Reservation,Integer> {

    public List<Reservation> findByZoo_id(Integer zooId);
}