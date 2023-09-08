package hu.neuron.mentoring.zooapp.service;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.LogManager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = GondoZoo.class, name = "GondoZoo"),

        @JsonSubTypes.Type(value = Cleaner.class, name = "Cleaner") })
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Employee extends LogManager implements Serializable {

    private static final long serialVersionUID = 7491430318788362737L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthDate")
    private Date birthDate;
    @Column(name = "appointmentDate")
    private Date appointmentDate;
    @Column(name = "gender")
    private Character gender;

    public Employee() {
        super();
    }

    public Employee(Integer id,String name, Date birthDate, Date appointmentDate, char gender) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.appointmentDate = appointmentDate;
        this.gender = gender;
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

    public char getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public java.sql.Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentDate, birthDate, gender, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return Objects.equals(appointmentDate, other.appointmentDate) && Objects.equals(birthDate, other.birthDate)
                && Objects.equals(gender, other.gender) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", birthDate=" + birthDate + ", appointmentDate=" + appointmentDate
                + ", gender=" + gender + "]";
    }

    public abstract List<Job> logJob(Zoo zoo);
}

