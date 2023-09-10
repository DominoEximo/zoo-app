package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao implements Dao<Reservation>{

    private Connection conn;
    private PreparedStatement myStmt;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZooPU");
    EntityManager em = emf.createEntityManager();

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

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    public void save(Reservation reservation, Zoo currentZoo){
        try {
        myStmt = conn.prepareStatement("INSERT INTO reservation VALUES(?,?,?,?,?,?,?,?)");
        myStmt.setInt(1,reservation.getId());
        myStmt.setInt(2, currentZoo.getId());
        myStmt.setString(3,reservation.getName());
        myStmt.setDate(4,reservation.getReservationDate());
        myStmt.setDate(5,reservation.getVisitDate());
        myStmt.setInt(6,reservation.getId() );
        myStmt.setInt(7,reservation.getDiscount());
        myStmt.setInt(8,reservation.getPrice());
        myStmt.executeUpdate();
        myStmt = conn.prepareStatement("INSERT INTO ticket VALUES(?,?,?)");
        myStmt.setInt(1,reservation.getId());
        myStmt.setString(2,String.valueOf(reservation.getTickets().get(0).getType()));
        myStmt.setString(3,String.valueOf(reservation.getTickets().get(0).getVariant()));
        myStmt.executeUpdate();
        conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reservation reservation, String[] params) {

    }

    @Override
    public void delete(Reservation reservation) {

    }
}
