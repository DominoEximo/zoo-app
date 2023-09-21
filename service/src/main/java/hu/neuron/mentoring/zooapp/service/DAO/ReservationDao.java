package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDao implements Dao<Reservation>{

    private Connection conn;
    private PreparedStatement myStmt;

    EntityManagerFactory emf = EntityManagement.getInstance().getEmf();
    EntityManager em = EntityManagement.getInstance().getEm();

    public ReservationDao(){}

    public void connect(Connection conn){
        this.conn = conn;
    }

    @Override
    public Reservation findById(int id) {
        Reservation reservation = em.find(Reservation.class, id);
        if (reservation == null) {
            throw new EntityNotFoundException("Can't find Reservation for ID "
                    + id);
        }
        return reservation;
    }

    public List<Reservation> findByZoo(Zoo zoo){
        List<Reservation> reservations = getAll().stream()
                .filter(e -> zoo.getId().equals(e.getZoo().getId()))
                .collect(Collectors.toList());

        return  reservations;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = em.createQuery("Select reservation from Reservation reservation",Reservation.class).getResultList();


        return reservations;
    }

    public void save(Reservation reservation){
        em.getTransaction().begin();
        if(em.contains(reservation)){
            em.merge(reservation);
        }else {
            em.persist(reservation);
        }
        em.getTransaction().commit();
    }

    @Override
    public void update(Reservation reservation, String[] params) {

    }

    @Override
    public void delete(Reservation reservation) {

    }
}
