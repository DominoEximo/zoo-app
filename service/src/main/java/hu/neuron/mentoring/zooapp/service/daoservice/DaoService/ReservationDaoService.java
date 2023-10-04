package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReservationDaoService {

    List<Reservation> getAll();

    Reservation findById(int id);

    void save(Reservation reservation);

    void delete(Reservation reservation);

    public List<Reservation> findByZoo(Integer zooId);
}
