package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao implements Dao<Reservation>{

    private Connection conn;
    private PreparedStatement myStmt;

    public ReservationDao(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Reservation> findById(int id) {
        List<Reservation> reservations = new ArrayList<>();
        try {

            myStmt = conn.prepareStatement("select id,name,reservationDate,visitDate,tickets,discount,price from reservation where zooID = ?");
            myStmt.setInt(1,id);
            ResultSet reservationResult = myStmt.executeQuery();

            while (reservationResult.next()){
                List<Ticket> tickets = new ArrayList<>();
                Integer reservationID = reservationResult.getInt("id");
                String reserverName = reservationResult.getString("name");
                Date reservationDate = reservationResult.getDate("reservationDate");
                Date visitDate = reservationResult.getDate("visitDate");
                Integer discount = reservationResult.getInt("discount");
                Integer price = 0;
                myStmt = conn.prepareStatement("select * from ticket where id = ?");
                myStmt.setInt(1,reservationID);
                ResultSet ticketResult = myStmt.executeQuery();
                while (ticketResult.next()){
                    String ticketType = ticketResult.getString("type").toLowerCase();
                    TicketType type = null;

                    switch (ticketType){
                        case ("adult"):{
                            type = TicketType.ADULT;
                            price += 1000;
                            break;
                        }
                        case ("kid"):{
                            type = TicketType.KID;
                            price += 500;
                            break;
                        }
                        case ("retired"):{
                            type = TicketType.RETIRED;
                            price += 500;
                            break;
                        }
                        case ("group"):{
                            type = TicketType.GROUP;
                            price += 3000;
                            break;
                        }
                    }
                    String ticketVariant = ticketResult.getString("variant").toLowerCase();
                    TicketVariant variant = null;

                    switch (ticketVariant){
                        case ("fullDay"):{
                            variant = TicketVariant.FULL_DAY;
                            price += 1500;
                            break;
                        }
                        case ("afternoon"):{
                            variant = TicketVariant.AFTERNOON;
                            price += 700;
                            break;
                        }
                        case ("forenoon"):{
                            variant = TicketVariant.FORENOON;
                            price += 800;
                            break;
                        }

                    }
                    tickets.add(new Ticket(type,variant,price));
                }
                reservations.add(new Reservation(reservationID,reserverName,reservationDate,visitDate,tickets,discount,price));
                conn.commit();
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return reservations;
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
