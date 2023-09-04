package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Species;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDao implements Dao<Animal>{

    private Connection conn;
    private PreparedStatement myStmt = null;
    private ResultSet zooResult = null;

    public AnimalDao(){}

    public void connect(Connection conn){
        this.conn = conn;
    }
    @Override
    public List<Animal> findById(int id) {

        List<Animal> animals = new ArrayList<>();

        try {

            myStmt = conn.prepareStatement("select id,species,nickname,birthDate,gender from animal where id = ?");
            myStmt.setInt(1, id);
            ResultSet animalResult = myStmt.executeQuery();
            while (animalResult.next()) {
                Integer animalId = animalResult.getInt("id");
                String specie = animalResult.getString("species");
                Species species = null;
                switch (specie.toUpperCase()) {
                    case ("TIGER"): {
                        species = Species.TIGER;
                        break;
                    }
                    case ("LION"): {
                        species = Species.LION;
                        break;
                    }
                    case ("BEAR"): {
                        species = Species.BEAR;
                        break;
                    }
                    case ("GIRAFFE"): {
                        species = Species.GIRAFFE;
                        break;
                    }
                    case ("PENGUIN"): {
                        species = Species.PENGUIN;
                        break;
                    }
                    case ("WHALE"): {
                        species = Species.WHALE;
                        break;
                    }
                    case ("RHINO"): {
                        species = Species.RHINO;
                        break;
                    }
                    case ("SEAL"): {
                        species = Species.SEAL;
                        break;
                    }
                    case ("FOX"): {
                        species = Species.FOX;
                        break;
                    }
                    case ("WOLF"): {
                        species = Species.WOLF;
                        break;
                    }
                    case ("PEACOCK"): {
                        species = Species.PEACOCK;
                        break;
                    }
                }
                String nickname = animalResult.getString("nickname");
                Date birthDate = animalResult.getDate("birthDate");
                Character gender = animalResult.getString("gender").charAt(0);
                animals.add(new Animal(animalId,species,nickname,birthDate,gender));
            }
            conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return animals;
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
