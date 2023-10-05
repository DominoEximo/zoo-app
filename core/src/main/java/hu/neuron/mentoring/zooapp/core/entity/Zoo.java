package hu.neuron.mentoring.zooapp.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name = "Zoo")
public class Zoo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;


    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Director director;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "zoo_fk")
    private List<Reservation> reservations;

    public Zoo() {


        reservations = new ArrayList<>();
    }
    public Zoo(String name) {
        this.setName(name);


        reservations = new ArrayList<>();
    }

    public Zoo(Director director) {



        reservations = new ArrayList<>();
        this.setDirector(director);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
