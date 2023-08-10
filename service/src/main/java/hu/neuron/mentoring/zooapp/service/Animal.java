package hu.neuron.mentoring.zooapp.service;

import java.io.Serializable;
import java.util.Objects;

public class Animal implements Serializable{

    private Species species;
    private String nickname;
    private Integer birthDate;
    private Character gender;


    public Animal() {
        super();
    }


    public Animal(Species species, String nickname, Integer birth_date, Character gender) {
        super();
        this.species = species;
        this.nickname = nickname;
        this.birthDate = birth_date;
        this.gender = gender;
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

    public int getBirth_date() {
        return birthDate;
    }

    public void setBirth_date(int birth_date) {
        this.birthDate = birth_date;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
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

