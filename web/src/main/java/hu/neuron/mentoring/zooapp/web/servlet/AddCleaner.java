package hu.neuron.mentoring.zooapp.web.servlet;


import java.sql.*;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.CleanedArea;
import hu.neuron.mentoring.zooapp.core.entity.Cleaner;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/zoo/AddCleaner")
public class AddCleaner extends HttpServlet {
    @Autowired
    ZooDaoService zooDaoService;
    @Autowired
    GondoZooDaoService gondoZooDaoService;
    @Autowired
    CleanerDaoService cleanerDaoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("name");
        Date appointmentDate = java.sql.Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = java.sql.Date.valueOf((req.getParameter("birthDate")));

        String g = req.getParameter("gender");
        Character gender = g.charAt(0);

        String[] areas = req.getParameter("cleanedAreas").split(" ");
        List<CleanedArea> cleanedAreas = new ArrayList<>();

        for (String area : areas) {
            switch (area.toUpperCase()) {
                case ("TERRARIUM"):
                    cleanedAreas.add(CleanedArea.TERRARIUM);
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


        Integer zooID = Integer.parseInt(req.getParameter("zooID"));

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (zooID.equals(zoo.getId())) {
                currentZoo.add(zoo);
                cleanerDaoService.save(new Cleaner(name, birthDate, appointmentDate, gender, cleanedAreas,zoo));
            }

        }



        List<Employee> employees = gondoZooDaoService.findByZoo(currentZoo.get(0).getId());
        employees.addAll(cleanerDaoService.findByZoo(currentZoo.get(0).getId()));
        req.setAttribute("employees", employees);
        req.setAttribute("id", zooID);
        req.getRequestDispatcher("listEmployees").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

