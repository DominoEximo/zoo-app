package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.Species;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDao implements Dao<Animal>{

    private Connection conn;
    private PreparedStatement myStmt = null;
    private ResultSet zooResult = null;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZooPU");
    EntityManager em = emf.createEntityManager();

    public AnimalDao(){}

    public void connect(Connection conn){
        this.conn = conn;
    }
    @Override
    public Animal findById(int id) {

        Animal animal = em.find(Animal.class, id);
        if (animal == null) {
            throw new EntityNotFoundException("Can't find Animal for ID "
                    + id);
        }
        return animal;
    }

    @Override
    public List<Animal> getAll() {
        return null;
    }

    public void save(Animal animal){
        try{
            myStmt = conn.prepareStatement("INSERT INTO animal(id,species,nickname,birthDate,gender) VALUES(?,?,?,?,?)");
            myStmt.setInt(1,animal.getId());
            myStmt.setString(2, String.valueOf(animal.getSpecies()));
            myStmt.setString(3,animal.getNickname());
            myStmt.setDate(4,animal.getBirth_date());
            myStmt.setString(5,String.valueOf(animal.getGender()));
            myStmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Animal animal, String[] params) {

    }

    @Override
    public void delete(Animal animal) {
        try{
            myStmt = conn.prepareStatement("DELETE FROM animal where id = ? and nickname = ?");
            myStmt.setInt(1,animal.getId());
            myStmt.setString(2, animal.getNickname());
            myStmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
