package hu.neuron.training.zooapp.web.storage;


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
        this.loadData();

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
        ResultSet zooResult = null;
        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            myStmt = myConn.prepareStatement("select id,name from zoo");
            zooResult = myStmt.executeQuery();
            while (zooResult.next()) {
                String name = zooResult.getString("name");
                Integer id = zooResult.getInt("id");
                Zoo zoo = new Zoo(name);
                zoo.setId(id);
                myStmt = myConn.prepareStatement("select * from employee where id = ?");
                myStmt.setInt(1,id);
                ResultSet employeeResult = myStmt.executeQuery();
                while(employeeResult.next()){
                    String type = employeeResult.getString("type");
                    String employeeName = employeeResult.getString("name");
                    Date birthDate = employeeResult.getDate("birthDate");
                    Date appointmentDate = employeeResult.getDate("appointmentDate");
                    Character gender = employeeResult.getString("gender").charAt(0);
                    if (type.equals("director")){
                        zoo.setDirector(new Director(zoo.getId(),employeeName,birthDate,appointmentDate,gender));
                    } else if (type.equals("gondoZoo")) {
                        String animalsFromSQL = employeeResult.getString("suppliedAnimals");
                        String[] animals = animalsFromSQL.split(" ");
                        List<Species> suppliedAnimals = new ArrayList<>();
                        for (String animal : animals){
                            switch (animal.toUpperCase()) {
                                case ("TIGER"):{
                                    suppliedAnimals.add(Species.TIGER);
                                    break;
                                }
                                case ("LION"):{
                                    suppliedAnimals.add(Species.LION);
                                    break;
                                }
                                case ("BEAR"):{
                                    suppliedAnimals.add(Species.BEAR);
                                    break;
                                }
                                case ("GIRAFFE"):{
                                    suppliedAnimals.add(Species.GIRAFFE);
                                    break;
                                }
                                case ("PENGUIN"):{
                                    suppliedAnimals.add(Species.PENGUIN);
                                    break;
                                }
                                case ("WHALE"):{
                                    suppliedAnimals.add(Species.WHALE);
                                    break;
                                }
                                case ("RHINO"):{
                                    suppliedAnimals.add(Species.RHINO);
                                    break;
                                }
                                case ("SEAL"):{
                                    suppliedAnimals.add(Species.SEAL);
                                    break;
                                }
                                case ("FOX"):{
                                    suppliedAnimals.add(Species.FOX);
                                    break;
                                }
                                case ("WOLF"):{
                                    suppliedAnimals.add(Species.WOLF);
                                    break;
                                }
                                case ("PEACOCK"):{
                                    suppliedAnimals.add(Species.PEACOCK);
                                    break;
                                }
                            }
                        }
                        zoo.addEmployee(new GondoZoo(zoo.getId(),employeeName,birthDate,appointmentDate,gender,suppliedAnimals));
                    } else if (type.equals("cleaner")) {
                        String cleanedAreasFromSQL = employeeResult.getString("cleanedAreas");
                        String[] areas = cleanedAreasFromSQL.split(" ");
                        List<CleanedArea> cleanedAreas = new ArrayList<>();

                        for (String area : areas){
                            switch (area.toUpperCase()){
                                case ("TERRARIUM"):
                                    cleanedAreas.add(CleanedArea.valueOf("TERRARIUM")); //REAFACTOR
                                    break;
                                case ("POOL"):
                                    cleanedAreas.add(CleanedArea.POOL);
                                    break;
                                case ("CAGE"):
                                    cleanedAreas.add(CleanedArea.CAGE);
                                    break;
                                case ("RUNWAY"):
                                    cleanedAreas.add(CleanedArea.RUNWAY);
                                    break;
                            }
                        }
                        zoo.addEmployee(new Cleaner(zoo.getId(),employeeName,birthDate,appointmentDate,gender,cleanedAreas));

                    }


                }
                myStmt = myConn.prepareStatement("select id,species,nickname,birthDate,gender from animal where id = ?");
                myStmt.setInt(1,id);
                ResultSet animalResult = myStmt.executeQuery();
                while (animalResult.next()){
                    Integer animalId = animalResult.getInt("id");
                    String specie= animalResult.getString("species");
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
                    String nickname = animalResult.getString("nickname");
                    Date birthDate = animalResult.getDate("birthDate");
                    Character gender = animalResult.getString("gender").charAt(0);

                    zoo.addAnimal(new Animal(animalId,species,nickname,birthDate,gender));

                }
                myStmt = myConn.prepareStatement("select id,name,reservationDate,visitDate,tickets,discount,price from reservation where zooID = ?");
                myStmt.setInt(1,id);
                ResultSet reservationResult = myStmt.executeQuery();

                while (reservationResult.next()){
                    List<Ticket> tickets = new ArrayList<>();
                    Integer reservationID = reservationResult.getInt("id");
                    String reserverName = reservationResult.getString("name");
                    Date reservationDate = reservationResult.getDate("reservationDate");
                    Date visitDate = reservationResult.getDate("visitDate");
                    Integer discount = reservationResult.getInt("discount");
                    Integer price = 0;
                    myStmt = myConn.prepareStatement("select * from ticket where id = ?");
                    myStmt.setInt(1,reservationID);
                    ResultSet ticketResult = myStmt.executeQuery();
                    while (ticketResult.next()){
                        String ticketType = ticketResult.getString("type");
                        TicketType type = null;

                        switch (ticketType){
                            case ("adult"):{
                                type = TicketType.ADULT;
                                price += 1000;
                                break;
                            }
                            case ("kid"):{
                                type = TicketType.KID;
                                price += 500;
                                break;
                            }
                            case ("retired"):{
                                type = TicketType.RETIRED;
                                price += 500;
                                break;
                            }
                            case ("group"):{
                                type = TicketType.GROUP;
                                price += 3000;
                                break;
                            }
                        }
                        String ticketVariant = ticketResult.getString("variant");
                        TicketVariant variant = null;

                        switch (ticketVariant){
                            case ("fullDay"):{
                                variant = TicketVariant.FULL_DAY;
                                price += 1500;
                                break;
                            }
                            case ("afternoon"):{
                                variant = TicketVariant.AFTERNOON;
                                price += 700;
                                break;
                            }
                            case ("forenoon"):{
                                variant = TicketVariant.FORENOON;
                                price += 800;
                                break;
                            }

                        }
                        tickets.add(new Ticket(type,variant,price));
                    }
                    zoo.reserve(new Reservation(reservationID,reserverName,reservationDate,visitDate,tickets,discount,price));
                }
                zooList.add(zoo);

            }
            myConn.close();
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
