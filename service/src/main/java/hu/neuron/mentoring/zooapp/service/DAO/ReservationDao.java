package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.EntitiManager.EntityManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ReservationDao extends JpaRepository<Reservation,Integer> {

    public List<Employee> findByZoo(Zoo zoo);
}