package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Reservation;
import hu.neuron.mentoring.zooapp.core.entity.Ticket;
import hu.neuron.mentoring.zooapp.core.enums.TicketType;
import hu.neuron.mentoring.zooapp.core.enums.TicketVariant;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ReservationDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("reservationView")
@ViewScoped
public class ReservationView implements Serializable {

    @Autowired
    private ReservationDaoService reservationDaoService;

    @Autowired
    private ZooDaoService zooDaoService;

    private Reservation selectedReservation;

    private String name;

    private Date reservationDate;

    private Date visitDate;

    private Integer price;

    private Integer discount = 0;

    private String ticketType;

    private String ticketVariant;

    public ReservationView() {
    }

    public ReservationDaoService getReservationDaoService() {
        return reservationDaoService;
    }

    public void setReservationDaoService(ReservationDaoService reservationDaoService) {
        this.reservationDaoService = reservationDaoService;
    }

    public Reservation getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(Reservation selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketVariant() {
        return ticketVariant;
    }

    public void setTicketVariant(String ticketVariant) {
        this.ticketVariant = ticketVariant;
    }

    public void deleteReservation(){
        reservationDaoService.delete(reservationDaoService.findById(selectedReservation.getId()));
        selectedReservation = null;
    }

    public List<Reservation> getReservation(Integer zooID){
        return new ArrayList<>(reservationDaoService.findByZoo(zooID));
    }

    public void createReservation(Integer id){
        List<Ticket> tickets = new ArrayList<>();
        price = 0;
        TicketType type = null;
        switch (ticketType) {
            case ("ADULT"): {
                type = TicketType.ADULT;
                price += 1000;
                break;
            }
            case ("KID"): {
                type = TicketType.KID;
                price += 500;
                break;
            }
            case ("RETIRED"): {
                type = TicketType.RETIRED;
                price += 500;
                break;
            }
            case ("GROUP"): {
                type = TicketType.GROUP;
                price += 3000;
                break;
            }
        }
        TicketVariant variant = null;

        switch (ticketVariant) {
            case ("FULL_DAY"): {
                variant = TicketVariant.FULL_DAY;
                price += 1500;
                break;
            }
            case ("AFTERNOON"): {
                variant = TicketVariant.AFTERNOON;
                price += 700;
                break;
            }
            case ("FORENOON"): {
                variant = TicketVariant.FORENOON;
                price += 800;
                break;
            }

        }
        Ticket ticket = new Ticket(type,variant,price);

        tickets.add(ticket);

        java.sql.Date sqlvisitDate = new java.sql.Date(visitDate.getTime());
        java.sql.Date sqlreserveDate = new java.sql.Date(reservationDate.getTime());

        Reservation newReservation = new Reservation(name,sqlreserveDate,sqlvisitDate,tickets,discount,price,zooDaoService.findById(id));

        reservationDaoService.save(newReservation);
    }
}
