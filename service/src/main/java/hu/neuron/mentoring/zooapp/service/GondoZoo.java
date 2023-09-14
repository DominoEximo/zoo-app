package hu.neuron.mentoring.zooapp.service;

import javax.persistence.*;
import java.util.ArrayList;
import java.sql.Date;

import java.util.HashSet;
import java.util.List;

import java.util.Objects;
@Entity
public class GondoZoo extends Employee {


    private static final long serialVersionUID = -3608765929799268696L;
    @ElementCollection(targetClass = Species.class)
    @JoinTable(name = "gondozoo_species", joinColumns = @JoinColumn(name = "gondozoo_fk"))
    @Enumerated(EnumType.STRING)
    private List<Species> suppliedAnimals;

    public GondoZoo() {
        super();
    }

    public GondoZoo(Integer id,String name, Date birthDate, Date appointmentDate, Character gender,
                    List<Species> suppliedAnimals) {
        super(id,name,birthDate,appointmentDate,gender);
        this.suppliedAnimals = suppliedAnimals;
    }

    public void addAnimal(Species animal) {
        this.suppliedAnimals.add(animal);
    }

    public List<Species> getSuppliedAnimals() {
        return suppliedAnimals;
    }

    public void setSuppliedAnimals(ArrayList<Species> suppliedAnimals) {
        this.suppliedAnimals = suppliedAnimals;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(suppliedAnimals);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GondoZoo other = (GondoZoo) obj;
        return Objects.equals(suppliedAnimals, other.suppliedAnimals);
    }

    @Override
    public String toString() {
        return super.toString() + "Supplied Animals=" + suppliedAnimals;
    }

    @Override
    public List<Job> logJob(Zoo zoo) {
        ArrayList<Job> records = new ArrayList<>();
        HashSet<Species> currentTypesOfAnimals = new HashSet<>();

        for (Animal animal : zoo.getAnimals()) {
            currentTypesOfAnimals.add(animal.getSpecies());
        }

        for (Species animal : this.getSuppliedAnimals()) {
            if (currentTypesOfAnimals.contains(animal)) {
                records.add(new Job(2, String.format("%s gondozás", animal), this));
            }

        }

        return records;
    }

}

