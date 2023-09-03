package hu.neuron.training.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
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
        ConnectionManager manager = new ConnectionManager();
        ZooDao zooDao = new ZooDao(manager.getMyConn());
        AnimalDao animalDao = new AnimalDao(manager.getMyConn());

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



        Integer zooID = Integer.parseInt(req.getParameter("zooID"));

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDao.getAll()) {
            if (zooID.equals(zoo.getId())) {
                currentZoo.add(zoo);
            }

        }


        animalDao.save(new Animal(currentZoo.get(0).getId(),species,nickname,birthDate,gender));






        req.setAttribute("animals",animalDao.findById(currentZoo.get(0).getId()));
        manager.closeConnection();

        req.getRequestDispatcher("/listAnimals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
