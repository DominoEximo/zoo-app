package hu.neuron.training.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/AddGondoZoo")
public class AddGondoZoo extends HttpServlet {

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
        String name = req.getParameter("name");
        Date appointmentDate = java.sql.Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = java.sql.Date.valueOf((req.getParameter("birthDate")));

        String g= req.getParameter("gender");
        Character gender = g.charAt(0);

        String[] animals = req.getParameter("suppliedAnimals").split(" ");
        String animalsToSQL = req.getParameter("suppliedAnimals");
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


        ZooStorage storage = ZooStorage.getInstance();

        String zooName = req.getParameter("zooName");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : storage.getZooList()) {
            if (zooName.equals(zoo.getName())) {

                currentZoo.add(zoo);
            }

        }

        currentZoo.get(0).addEmployee(new GondoZoo(currentZoo.get(0).getId(),name,birthDate,appointmentDate,gender,suppliedAnimals));
        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Inserting records into the table...");

            myStmt = myConn.prepareStatement("INSERT INTO employee(id,type,name,birthDate,appointmentDate,gender,suppliedAnimals) VALUES(?,?,?,?,?,?,?)");
            myStmt.setInt(1,currentZoo.get(0).getId());
            myStmt.setString(2, "gondoZoo");
            myStmt.setString(3,name);
            myStmt.setDate(4,birthDate);
            myStmt.setDate(5,appointmentDate);
            myStmt.setString(6, String.valueOf(gender));
            myStmt.setString(7,animalsToSQL);
            myStmt.executeUpdate();
            myConn.close();




        } catch (SQLException e) {
            e.printStackTrace();
        }
        storage.saveData();

        req.setAttribute("currentZoo",currentZoo.get(0));

        req.getRequestDispatcher("/listEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


