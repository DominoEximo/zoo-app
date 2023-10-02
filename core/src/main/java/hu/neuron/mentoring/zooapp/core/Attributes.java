package hu.neuron.mentoring.zooapp.core;

import java.util.Objects;

public class Attributes {

    private String origin;

    private Boolean Carnivore;

    public Attributes(String origin, Boolean carnivore) {
        super();
        this.origin = origin;
        Carnivore = carnivore;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Boolean getCarnivore() {
        return Carnivore;
    }

    public void setCarnivore(Boolean carnivore) {
        Carnivore = carnivore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Carnivore, origin);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Attributes other = (Attributes) obj;
        return Objects.equals(Carnivore, other.Carnivore) && Objects.equals(origin, other.origin);
    }

    @Override
    public String toString() {
        return "Attributes [origin=" + origin + ", Carnivore=" + Carnivore + "]";
    }

}
