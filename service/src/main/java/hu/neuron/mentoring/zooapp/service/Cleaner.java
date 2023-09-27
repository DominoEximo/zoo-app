package hu.neuron.mentoring.zooapp.service;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
@Entity
public class Cleaner extends Employee {


    private static final long serialVersionUID = -6192349358679720176L;
    @ElementCollection(targetClass = CleanedArea.class)
    @JoinTable(name = "cleaner_cleaned_areas", joinColumns = @JoinColumn(name = "cleanaer_fk"))
    @Enumerated(EnumType.STRING)
    private List<CleanedArea> cleanedAreas;


    public Cleaner() {
        super();
    }

    public Cleaner(String name, Date birthDate, Date appointmentDate, Character gender, List<CleanedArea> cleanedAreas, Zoo zoo) {
        super(name, birthDate,appointmentDate, gender,zoo);

        this.cleanedAreas = cleanedAreas;
    }

    public List<CleanedArea> getCleanedAreas() {
        return cleanedAreas;
    }

    public void setCleanedAreas(List<CleanedArea> cleanedAreas) {
        this.cleanedAreas = cleanedAreas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(cleanedAreas);
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
        Cleaner other = (Cleaner) obj;
        return Objects.equals(cleanedAreas, other.cleanedAreas);
    }

    @Override
    public String toString() {
        return super.toString() + "cleanedAreas=" + cleanedAreas;
    }

    @Override
    public List<Job> logJob(Zoo zoo) {
        ArrayList<Job> records = new ArrayList<>();

        for (CleanedArea area : this.getCleanedAreas()) {
            records.add(new Job(3, String.format("%s takarÍtása", area), this));
        }

        return records;

    }

}

