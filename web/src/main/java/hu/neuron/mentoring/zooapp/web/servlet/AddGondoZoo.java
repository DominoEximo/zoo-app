package hu.neuron.mentoring.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.entity.GondoZoo;
import hu.neuron.mentoring.zooapp.core.enums.Species;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;

import java.sql.*;


import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/zoo/AddGondoZoo")
public class AddGondoZoo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();
        GondoZooDaoService gondoZooDaoService = DaoController.getInstance().getGondoZooDaoService();
        CleanerDaoService cleanerDaoService = DaoController.getInstance().getCleanerDaoService();


        String name = req.getParameter("name");
        Date appointmentDate = java.sql.Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = java.sql.Date.valueOf((req.getParameter("birthDate")));

        String g = req.getParameter("gender");
        Character gender = g.charAt(0);

        String[] animals = req.getParameter("suppliedAnimals").split(" ");
        String animalsToSQL = req.getParameter("suppliedAnimals");
        List<Species> suppliedAnimals = new ArrayList<>();
        for (String animal : animals) {
            switch (animal.toUpperCase()) {
                case ("TIGER"): {
                    suppliedAnimals.add(Species.TIGER);
                    break;
                }
                case ("LION"): {
                    suppliedAnimals.add(Species.LION);
                    break;
                }
                case ("BEAR"): {
                    suppliedAnimals.add(Species.BEAR);
                    break;
                }
                case ("GIRAFFE"): {
                    suppliedAnimals.add(Species.GIRAFFE);
                    break;
                }
                case ("PENGUIN"): {
                    suppliedAnimals.add(Species.PENGUIN);
                    break;
                }
                case ("WHALE"): {
                    suppliedAnimals.add(Species.WHALE);
                    break;
                }
                case ("RHINO"): {
                    suppliedAnimals.add(Species.RHINO);
                    break;
                }
                case ("SEAL"): {
                    suppliedAnimals.add(Species.SEAL);
                    break;
                }
                case ("FOX"): {
                    suppliedAnimals.add(Species.FOX);
                    break;
                }
                case ("WOLF"): {
                    suppliedAnimals.add(Species.WOLF);
                    break;
                }
                case ("PEACOCK"): {
                    suppliedAnimals.add(Species.PEACOCK);
                    break;
                }
            }
        }


        Integer zooID = Integer.parseInt(req.getParameter("zooID"));

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (zooID.equals(zoo.getId())) {
                currentZoo.add(zoo);
                gondoZooDaoService.save(new GondoZoo( name, birthDate, appointmentDate, gender, suppliedAnimals,zoo));
            }

        }



        List<Employee> employees = gondoZooDaoService.findByZoo(currentZoo.get(0).getId());
        employees.addAll(cleanerDaoService.findByZoo(currentZoo.get(0).getId()));
        req.setAttribute("employees", employees);
        req.setAttribute("id", zooID);


        req.getRequestDispatcher("/listEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


