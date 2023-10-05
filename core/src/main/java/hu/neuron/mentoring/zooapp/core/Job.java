package hu.neuron.mentoring.zooapp.core;

import hu.neuron.mentoring.zooapp.core.entity.Employee;

import java.util.Objects;

public class Job {

    private Integer time;

    private String description;

    private Employee employee;

    public Job(Integer time, String description, Employee employee) {
        super();
        this.time = time;
        this.description = description;
        this.employee = employee;
    }



    public Integer getTime() {
        return time;
    }



    public void setTime(Integer time) {
        this.time = time;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public Employee getEmployee() {
        return employee;
    }



    public void setEmployee(Employee employee) {
        this.employee = employee;
    }





    @Override
    public int hashCode() {
        return Objects.hash(employee, description, time);
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Job other = (Job) obj;
        return Objects.equals(employee, other.employee) && Objects.equals(description, other.description)
                && Objects.equals(time, other.time);
    }



    @Override
    public String toString() {
        return "Job [time=" + time + ", description=" + description + ", Employee=" + employee.getName() + "]";
    }

}
