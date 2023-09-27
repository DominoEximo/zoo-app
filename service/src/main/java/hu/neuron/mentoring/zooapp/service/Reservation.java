package hu.neuron.mentoring.zooapp.service;


import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "reservationDate")
    private Date reservationDate;
    @Column(name = "visitDate")
    private Date visitDate;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_fk")
    private List<Ticket> tickets;
    @Column(name = "discount")
    private Integer discount;
    @Column(name = "price")
    private Integer price;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Zoo zoo;

    public  Reservation(){};
    public Reservation(String name, Date reservationDate, Date visitDate, List<Ticket> tickets, Integer discount,
                       Integer price, Zoo zoo) {
        super();

        this.name = name;
        this.reservationDate = reservationDate;
        this.visitDate = visitDate;
        this.tickets = tickets;
        this.discount = discount;
        this.price = price;
        this.zoo = zoo;
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

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
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

