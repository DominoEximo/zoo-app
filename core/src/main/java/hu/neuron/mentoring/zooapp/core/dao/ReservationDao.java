package hu.neuron.mentoring.zooapp.core.dao;

import hu.neuron.mentoring.zooapp.core.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation,Integer> {

    public List<Reservation> findByZoo_id(Integer zooId);
}