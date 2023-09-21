package hu.neuron.training.zooapp.web.servlet;


import java.sql.*;

import hu.neuron.mentoring.zooapp.service.CleanedArea;
import hu.neuron.mentoring.zooapp.service.Cleaner;
import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.DAO.*;
import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/AddCleaner")
public class AddCleaner extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooDao zooDao = DaoManager.getInstance().getZooDao();
        GondoZooDao gondoZooDao = DaoManager.getInstance().getGondoZooDao();
        CleanerDao cleanerDao = DaoManager.getInstance().getCleanerDao();

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

        for (Zoo zoo : zooDao.getAll()) {
            if (zooID.equals(zoo.getId())) {
                currentZoo.add(zoo);
                cleanerDao.save(new Cleaner(name, birthDate, appointmentDate, gender, cleanedAreas,zoo));
            }

        }



        List<Employee> employees = gondoZooDao.findByZoo(currentZoo.get(0));
        employees.addAll(cleanerDao.findByZoo(currentZoo.get(0)));
        req.setAttribute("employees", employees);
        req.setAttribute("id", zooID);
        req.getRequestDispatcher("/listEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

