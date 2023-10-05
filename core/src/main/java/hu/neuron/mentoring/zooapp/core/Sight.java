package hu.neuron.mentoring.zooapp.core;

import hu.neuron.mentoring.zooapp.core.entity.Animal;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.enums.Species;

import java.util.List;
import java.util.Objects;

public class Sight {

    private String name;

    private Integer gps;

    private List<Animal> creatures;

    private Species type;

    private Employee employee;

    private Description<?> description;

    public Sight(String name, Integer gps, List<Animal> creatures, Species type, Employee employee,
                 Description<?> description) {
        super();
        this.name = name;
        this.gps = gps;
        this.creatures = creatures;
        this.type = type;
        this.employee = employee;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGps() {
        return gps;
    }

    public void setGps(Integer gps) {
        this.gps = gps;
    }

    public List<Animal> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<Animal> creatures) {
        this.creatures = creatures;
    }

    public Species getType() {
        return type;
    }

    public void setType(Species type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Description<?> getDescription() {
        return description;
    }

    public void setDescription(Description<?> description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatures, description, employee, gps, name, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sight other = (Sight) obj;
        return Objects.equals(creatures, other.creatures) && Objects.equals(description, other.description)
                && Objects.equals(employee, other.employee) && Objects.equals(gps, other.gps)
                && Objects.equals(name, other.name) && Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return "Sights [name=" + name + ", gps=" + gps + ", creatures=" + creatures + ", type=" + type + ", employee="
                + employee + ", description=" + description + "]";
    }

}

