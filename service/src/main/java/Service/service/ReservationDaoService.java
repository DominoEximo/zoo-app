package Service.service;

import hu.neuron.mentoring.zooapp.service.DAO.ReservationDao;
import hu.neuron.mentoring.zooapp.service.Reservation;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.List;

public interface ReservationDaoService {

    List<Reservation> getAll();

    Reservation findById(int id);

    void save(Reservation reservation);

    void delete(Reservation reservation);
}
