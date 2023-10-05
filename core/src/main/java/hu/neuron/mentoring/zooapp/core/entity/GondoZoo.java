package hu.neuron.mentoring.zooapp.core.entity;

import hu.neuron.mentoring.zooapp.core.enums.Species;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.sql.Date;

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

    public GondoZoo(String name, Date birthDate, Date appointmentDate, Character gender,
                    List<Species> suppliedAnimals, Zoo zoo) {
        super(name,birthDate,appointmentDate,gender,zoo);
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


}

