package hu.neuron.mentoring.zooapp.service;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.*;

@Entity
@Table(name = "Zoo")
public class Zoo implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(Zoo.class.getName());

    private static int counter;
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
    private List<Animal> animals;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "zoo_fk")
    private List<Reservation> reservations;

    public Zoo() {

        animals = new ArrayList<>();

        reservations = new ArrayList<>();
        counter++;
    }
    public Zoo(String name) {
        this.setName(name);

        animals = new ArrayList<>();

        reservations = new ArrayList<>();
        counter++;
    }

    public Zoo(Director director) {

        animals = new ArrayList<>();

        reservations = new ArrayList<>();
        counter++;
        this.setDirector(director);
    }

    static {
        listZoos();
    }

    {
        logger.info("Az állatkert megalapulása: " + LocalTime.now() + "\n");
    }

    /*public void recordJob(Employee employee) {
        Boolean isValid = false;

        if (employees.contains(employee)) {
            isValid = true;
        }

        if (isValid) {

            List<Job> log = employee.logJob(this);
            loggedJobs.addAll(log);

        } else {
            logger.info("Ilyen dongozó nem létezik!");
        }

    }

    public void autoLogAllJobs(List<Employee> list) {

        for (Employee employee : list) {
            recordJob(employee);
        }
    }

    public void listLoggedJobs() {
        if (loggedJobs.size() == 0) {
            logger.info("Jelenleg nincsenek feljegyzett munkák.");
        } else {
            for (Job job : loggedJobs) {
                logger.info(String.format("%s", job));
            }
        }

    }

    public void checkRewardApplicability() {
        if (this.employees.size() == 0) {
            logger.info("Az állatkertnek nincsenek dolgozói!");
        }
        for (Employee employee : this.employees) {
            if (employee instanceof Director) {
                continue;
            } else {
                long diff = Calendar.getInstance().getTimeInMillis() - employee.getAppointmentDate().getTime();
                TimeUnit time = TimeUnit.DAYS;
                long difference = time.convert(diff, TimeUnit.MILLISECONDS);
                if (difference / 365 > 5) {
                    rewardApplicables.add(employee);
                }
            }
        }
    }

    public void listRewardApplicables() {
        if (this.rewardApplicables.size() == 0) {
            logger.info("Jelenleg senki sem részesül jutalomban.");
        } else {
            for (Employee rewardable : this.rewardApplicables) {
                logger.info(String.format("Jutalomban részesül: %s", rewardable.getName()));
            }
        }
    }

    public ArrayList<Job> logJobforGondoZoo(GondoZoo caretaker) {

        ArrayList<Job> records = new ArrayList<>();
        HashSet<Species> currentTypesOfAnimals = new HashSet<>();

        for (Animal animal : animals) {
            currentTypesOfAnimals.add(animal.getSpecies());
        }

        for (Species animal : caretaker.getSuppliedAnimals()) {
            if (currentTypesOfAnimals.contains(animal)) {
                records.add(new Job(2, String.format("%s gondozás", animal), caretaker));
            }

        }

        return records;
    }*/

    public void reserve(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void flushReservations() {
        this.reservations.clear();
    }

    /*public void createSight(Sight sight) {

        if (((GondoZoo) sight.getEmployee()).getSuppliedAnimals().contains(sight.getType())) {
            sights.add(sight);
        } else {
            logger.info("Nincs megfelelő gondozó ilyen hely létrehozásához!");
        }

    }

    public void listSights() {
        for (Sight sight : sights) {
            logger.info(String.format("%s", sight));
        }
    }

    public ArrayList<Job> logJobforCleaner(Cleaner cleaner) {

        ArrayList<Job> records = new ArrayList<>();

        for (CleanedArea area : cleaner.getCleanedAreas()) {
            records.add(new Job(3, String.format("%s takarÍtása", area), cleaner));
        }

        return records;
    }*/

    public void listAnimalsWithSpecies(Species species) {
        for (Animal animal : animals) {
            if (animal.getSpecies().equals(species)) {
                logger.info(String.format("%s", animal));
            }
        }

    }

    public static void listZoos() {
        logger.info("Az országnak " + counter + " állatkertje van jelenleg. \n");
    }




    public void sellAnimal(Animal animal) {
        logger.info("Az " + animal.getNickname() + " nevú állatot eladták.");
        this.animals.remove(animal);
    }






    public void fireDirector() throws ZooEmployeeException {
        if (this.director == null) {
            logger.info(String.format("Az állatkertnek nincs jelenleg igazgatója! \n"));
        } else {
            logger.info("Az állatkert " + director.getName() + " igazgatója eltávozott! \n");
            this.director = null;
            throw new ZooEmployeeException("Az állatkertnek nincs igazgatója!");

        }

    }

    public void animalCount() {
        logger.info("Az állatkertnek " + animals.size() + " lakója van jelenleg! \n");
    }

    public void listAnimals() {
        if (animals != null) {
            for (Animal animal : animals) {
                logger.info(String.format("%s", animal));
            }
        }

    }

    public void sortAnimals() {
        Collections.sort(animals, new CompareAnimals());
        for (Animal animal : animals) {
            logger.info(String.format("%s", animal));
        }
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
        if (this.director == null) {
            this.director = director;
            logger.info("Az állatkert igazgatója " + director.getName() + " lett! \n");
        } else {
            logger.info(String.format("Az állatkertnek már van igazgatója. \n"));
        }

    }



    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void listReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

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

    class Moving {

        public void move(Zoo from, Zoo to) {
            to.setAnimals(from.getAnimals());
            to.setDirector(from.getDirector());
            counter--;
            from.setDirector(null);
            from.setAnimals(null);
        }

    }

    public class CompareAnimals implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return new CompareToBuilder().append(o1.getSpecies(), o2.getSpecies())
                    .append(o1.getNickname(), o2.getNickname()).build();
        }

    }

}
