package hu.neuron.mentoring.zooapp.service;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
@Entity
public class Animal implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "species")
    @Enumerated(EnumType.STRING)
    private Species species;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "birthDate")
    private Date birthDate;
    @Column(name = "gender")
    private Character gender;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Zoo zoo;


    public Animal() {
        super();
    }


    public Animal(Species species, String nickname, Date birth_date, Character gender,Zoo zoo) {
        super();
        this.species = species;
        this.nickname = nickname;
        this.birthDate = birth_date;
        this.gender = gender;
        this.zoo = zoo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirth_date() {
        return birthDate;
    }

    public void setBirth_date(Date birth_date) {
        this.birthDate = birth_date;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, gender, nickname, species);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        return birthDate == other.birthDate && gender == other.gender && Objects.equals(nickname, other.nickname)
                && species == other.species;
    }

    @Override
    public String toString() {
        return "Animal [species=" + species + ", nickname=" + nickname + ", birth_date=" + birthDate + ", gender="
                + gender + "]";
    }

}

