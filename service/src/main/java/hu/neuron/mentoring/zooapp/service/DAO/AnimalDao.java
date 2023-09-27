package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface AnimalDao extends JpaRepository<Animal,Integer> {



    public List<Animal> findbyZoo(Zoo zoo);



}
