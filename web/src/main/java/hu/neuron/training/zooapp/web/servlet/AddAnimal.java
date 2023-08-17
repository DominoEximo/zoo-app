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
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/AddAnimal")
public class AddAnimal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Integer birthDate=Integer.parseInt(req.getParameter("birthDate"));
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
            currentZoo.get(0).addAnimal(new Animal(species,nickname,birthDate,gender));
        } catch (GondoZooNotAvailableException e) {
            req.getRequestDispatcher("/listAnimals.jsp").forward(req,resp);
        }
        req.setAttribute("currentZoo",currentZoo.get(0));

        req.getRequestDispatcher("/listAnimals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
