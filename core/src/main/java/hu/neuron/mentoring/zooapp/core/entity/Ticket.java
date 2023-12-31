package hu.neuron.mentoring.zooapp.core.entity;

import hu.neuron.mentoring.zooapp.core.enums.TicketType;
import hu.neuron.mentoring.zooapp.core.enums.TicketVariant;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TicketType type;
    @Enumerated(EnumType.STRING)
    private TicketVariant variant;

    private Integer price;

    public Ticket(){};
    public Ticket(TicketType type, TicketVariant variant, Integer price) {
        super();
        this.type = type;
        this.variant = variant;
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public TicketVariant getVariant() {
        return variant;
    }

    public void setVariant(TicketVariant variant) {
        this.variant = variant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, type, variant);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        return Objects.equals(price, other.price) && type == other.type && variant == other.variant;
    }

    @Override
    public String toString() {
        return "Ticket [type=" + type + ", variant=" + variant + ", price=" + price + "]";
    }

}

