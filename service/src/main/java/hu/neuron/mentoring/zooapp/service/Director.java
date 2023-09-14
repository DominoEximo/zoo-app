package hu.neuron.mentoring.zooapp.service;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
@Entity
public class Director extends Employee implements Serializable {


    private static final long serialVersionUID = -7800782665546564977L;

    public Director() {
        super();
    }

    public Director(String name, Date birthDate, Date appointmentDate, Character gender) {
        super(null,name, birthDate, appointmentDate, gender);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public List<Job> logJob(Zoo zoo) {
        return null;
    }

}

