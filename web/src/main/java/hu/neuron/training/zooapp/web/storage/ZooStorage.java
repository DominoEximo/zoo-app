package hu.neuron.training.zooapp.web.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hu.neuron.mentoring.zooapp.service.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.*;

public class ZooStorage {

    private static ZooStorage single_instance = null;
    private List<Zoo> zooList;


    public ZooStorage(){
        zooList = new ArrayList<>();
        if (new File("C:\\Users\\PappD\\IdeaProjects\\zoo-app\\web\\src\\main\\resources\\data.json").exists()){
            this.loadData();
        }
    }

    public static synchronized ZooStorage getInstance()
    {
        if (single_instance == null)
            single_instance = new ZooStorage();

        return single_instance;
    }

    public List<Zoo> getZooList() {
        return zooList;
    }

    public void setZooList(List<Zoo> zooList) {
        this.zooList = zooList;
    }

    public void addZoo(Zoo zoo) {zooList.add(zoo);}

    public void saveData(){
        try {
            File file = new File("C:\\Users\\PappD\\IdeaProjects\\zoo-app\\web\\src\\main\\resources\\data.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.writeValue(file, getZooList());


        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void loadData() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final String DB_URL = "jdbc:mysql://localhost:3306/zoo";
        final String USER = "root";
        final String PASS = "Xbox11223344";

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            myStmt = myConn.prepareStatement("select id,name from zoo");
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                String name = myRs.getString("name");
                Integer id = myRs.getInt("id");
                Zoo zoo = new Zoo(name);
                zoo.setId(id);
                myStmt = myConn.prepareStatement("select id,species,nickname,birthDate,gender from animal where id = ?");
                myStmt.setInt(1,id);
                ResultSet rs = myStmt.executeQuery();
                while (rs.next()){
                    Integer animalId = rs.getInt("id");
                    String specie= rs.getString("species");
                    Species species = null;
                    switch (specie.toUpperCase()) {
                        case ("TIGER"):{
                            species = Species.TIGER;
                            break;
                        }
                        case ("LION"):{
                            species = Species.LION;
                            break;
                        }
                        case ("BEAR"):{
                            species = Species.BEAR;
                            break;
                        }
                        case ("GIRAFFE"):{
                            species = Species.GIRAFFE;
                            break;
                        }
                        case ("PENGUIN"):{
                            species = Species.PENGUIN;
                            break;
                        }
                        case ("WHALE"):{
                            species = Species.WHALE;
                            break;
                        }
                        case ("RHINO"):{
                            species = Species.RHINO;
                            break;
                        }
                        case ("SEAL"):{
                            species = Species.SEAL;
                            break;
                        }
                        case ("FOX"):{
                            species = Species.FOX;
                            break;
                        }
                        case ("WOLF"):{
                            species = Species.WOLF;
                            break;
                        }
                        case ("PEACOCK"):{
                            species = Species.PEACOCK;
                            break;
                        }
                    }
                    String nickname = rs.getString("nickname");
                    Date birthDate = rs.getDate("birthDate");
                    Character gender = rs.getString("gender").charAt(0);

                    zoo.addAnimal(new Animal(animalId,species,nickname,birthDate,gender));

                }

            zooList.add(zoo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GondoZooNotAvailableException e) {
            throw new RuntimeException(e);
        }


    }

    public void removeZoo(Zoo zoo){
        zooList.remove(zoo);
    }

    public List<String> zooNames(){

        List<String> list = new ArrayList<>();

        for (Zoo zoo : zooList){
            list.add(zoo.getName());
        }

        return list;
    }

    public static List<String> filterListByTerm(List<String> list, String term) {

        List<String> matching = list.stream()
                .filter(e -> e.toLowerCase().startsWith(term))
                .collect(Collectors.toList());

        return matching;
    }

}
