package hu.neuron.mentoring.zooapp.service;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Reservation implements Serializable {

    private Integer id;
    private String name;

    private Date reservationDate;

    private Date visitDate;

    private List<Ticket> tickets;

    private Integer discount;

    private Integer price;

    public  Reservation(){};
    public Reservation(Integer id,String name, Date reservationDate, Date visitDate, List<Ticket> tickets, Integer discount,
                       Integer price) {
        super();
        this.id = id;
        this.name = name;
        this.reservationDate = reservationDate;
        this.visitDate = visitDate;
        this.tickets = tickets;
        this.discount = discount;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, name, price, reservationDate, tickets, visitDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        return Objects.equals(discount, other.discount) && Objects.equals(name, other.name)
                && Objects.equals(price, other.price) && Objects.equals(reservationDate, other.reservationDate)
                && Objects.equals(tickets, other.tickets) && Objects.equals(visitDate, other.visitDate);
    }

    @Override
    public String toString() {
        return "Reservation [name=" + name + ", reservationDate=" + reservationDate + ", visitDate=" + visitDate
                + ", tickets=" + tickets + ", discount=" + discount + ", price=" + price + "]";
    }

}

