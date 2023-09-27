package Service.Impl;

import Service.service.ReservationDaoService;
import hu.neuron.mentoring.zooapp.service.DAO.ReservationDao;
import hu.neuron.mentoring.zooapp.service.Reservation;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReservationDaoServiceImpl implements ReservationDaoService {

    @Autowired
    ReservationDao reservationDao;

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = null;
        reservations = (List<Reservation>) reservationDao.findAll();
        return  reservations;
    }

    @Override
    public Reservation findById(int id) {
        Reservation reservation = null;
        reservation = reservationDao.findById(id).get();
        return reservation;
    }

    @Override
    public void save(Reservation reservation) {
        reservationDao.save(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);
    }
}
