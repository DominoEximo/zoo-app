package hu.neuron.mentoring.zooapp.service;

import java.util.Objects;

public class Description<T extends Attributes> {

    private T description;

    public Description(T description) {
        super();
        this.description = description;
    }

    public T getDescription() {
        return description;
    }

    public void setDescription(T description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Description other = (Description) obj;
        return Objects.equals(description, other.description);
    }

    @Override
    public String toString() {
        return "Description [description=" + description + "]";
    }

}
