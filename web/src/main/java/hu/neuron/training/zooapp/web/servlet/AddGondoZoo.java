package hu.neuron.training.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.*;

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
import java.sql.Date;
import java.util.List;

@WebServlet (urlPatterns = "/AddGondoZoo")
public class AddGondoZoo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Date appointmentDate = java.sql.Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = java.sql.Date.valueOf((req.getParameter("birthDate")));

        String g= req.getParameter("gender");
        Character gender = g.charAt(0);

        String[] animals = req.getParameter("suppliedAnimals").split(" ");
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

        storage.saveData();

        req.setAttribute("currentZoo",currentZoo.get(0));

        req.getRequestDispatcher("/listEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


