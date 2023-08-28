package hu.neuron.training.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.GondoZooNotAvailableException;
import hu.neuron.mentoring.zooapp.service.Species;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/AddAnimal")
public class AddAnimal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        String specie= req.getParameter("species");
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
        String nickname= req.getParameter("nickname");
        Date birthDate = java.sql.Date.valueOf((req.getParameter("birthDate")));

        String g= req.getParameter("gender");
        Character gender = g.charAt(0);


        ZooStorage storage = ZooStorage.getInstance();

        String name = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : storage.getZooList()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }




        try {
            currentZoo.get(0).addAnimal(new Animal(currentZoo.get(0).getId(),species,nickname,birthDate,gender));
        } catch (GondoZooNotAvailableException e) {
            req.getRequestDispatcher("/listAnimals.jsp").forward(req,resp);
        }
        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Inserting records into the table...");

            myStmt = myConn.prepareStatement("INSERT INTO animal(id,species,nickname,birthDate,gender) VALUES(?,?,?,?,?)");
            myStmt.setInt(1,currentZoo.get(0).getId());
            myStmt.setString(2, req.getParameter("species"));
            myStmt.setString(3,nickname);
            myStmt.setDate(4,birthDate);
            myStmt.setString(5,String.valueOf(gender));
            myStmt.executeUpdate();
            myConn.close();




        } catch (SQLException e) {
            e.printStackTrace();
        }
        storage.saveData();
        req.setAttribute("currentZoo",currentZoo.get(0));

        req.getRequestDispatcher("/listAnimals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
