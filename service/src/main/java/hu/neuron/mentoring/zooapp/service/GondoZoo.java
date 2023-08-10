package hu.neuron.mentoring.zooapp.service;

import java.util.ArrayList;
import java.util.Date;

import java.util.HashSet;
import java.util.List;

import java.util.Objects;

public class GondoZoo extends Employee {


    private static final long serialVersionUID = -3608765929799268696L;
    private ArrayList<Species> suppliedAnimals;

    public GondoZoo() {
        super();
    }

    public GondoZoo(String name, Date birthDate, Date appointmentDate, Character gender,
                    ArrayList<Species> suppliedAnimals) {
        super(name,birthDate,appointmentDate,gender);
        this.suppliedAnimals = suppliedAnimals;
    }

    public void addAnimal(Species animal) {
        this.suppliedAnimals.add(animal);
    }

    public ArrayList<Species> getSuppliedAnimals() {
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
        return super.toString() + "Supplied Animals" + suppliedAnimals;
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

