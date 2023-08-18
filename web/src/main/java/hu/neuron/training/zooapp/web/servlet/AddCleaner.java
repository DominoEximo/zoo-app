package hu.neuron.training.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.Animal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import hu.neuron.mentoring.zooapp.service.CleanedArea;
import hu.neuron.mentoring.zooapp.service.Cleaner;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet (urlPatterns = "/AddCleaner")
public class AddCleaner extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id= req.getParameter("id");
        Date appointmentDate= null;
        try {
            appointmentDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("appointmentDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date birthDate= null;
        try {
            birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String g= req.getParameter("gender");
        Character gender = g.charAt(0);

        String[] areas = req.getParameter("cleanedAreas").split(" ");
        List<CleanedArea> cleanedAreas = new ArrayList<>();

        for (String area : areas){
            switch (area.toUpperCase()){
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

        ZooStorage storage = ZooStorage.getInstance();

        String name = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : storage.getZooList()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }

        currentZoo.get(0).addEmployee(new Cleaner(id,birthDate,appointmentDate,gender,cleanedAreas));
        req.setAttribute("currentZoo",currentZoo.get(0));

        req.getRequestDispatcher("/listEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

