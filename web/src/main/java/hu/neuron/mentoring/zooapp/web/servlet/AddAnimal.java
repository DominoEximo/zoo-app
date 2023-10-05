package hu.neuron.mentoring.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.AnimalDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.entity.Animal;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import hu.neuron.mentoring.zooapp.core.enums.Species;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/zoo/AddAnimal")
public class AddAnimal extends HttpServlet {
    @Autowired
    ZooDaoService zooDaoService;

    @Autowired
    AnimalDaoService animalDaoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {






        String specie = req.getParameter("species");
        Species species = null;
        switch (specie.toUpperCase()) {
            case ("TIGER"): {
                species = Species.TIGER;
                break;
            }
            case ("LION"): {
                species = Species.LION;
                break;
            }
            case ("BEAR"): {
                species = Species.BEAR;
                break;
            }
            case ("GIRAFFE"): {
                species = Species.GIRAFFE;
                break;
            }
            case ("PENGUIN"): {
                species = Species.PENGUIN;
                break;
            }
            case ("WHALE"): {
                species = Species.WHALE;
                break;
            }
            case ("RHINO"): {
                species = Species.RHINO;
                break;
            }
            case ("SEAL"): {
                species = Species.SEAL;
                break;
            }
            case ("FOX"): {
                species = Species.FOX;
                break;
            }
            case ("WOLF"): {
                species = Species.WOLF;
                break;
            }
            case ("PEACOCK"): {
                species = Species.PEACOCK;
                break;
            }
        }
        String nickname = req.getParameter("nickname");
        Date birthDate = java.sql.Date.valueOf((req.getParameter("birthDate")));

        String g = req.getParameter("gender");
        Character gender = g.charAt(0);


        Integer zooID = Integer.parseInt(req.getParameter("zooID"));

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (zooID.equals(zoo.getId())) {
                currentZoo.add(zoo);
                animalDaoService.save(new Animal(species, nickname, birthDate, gender,zoo));
            }

        }





        req.setAttribute("animals", animalDaoService.findByZoo(currentZoo.get(0).getId()));

        req.getRequestDispatcher("listAnimals").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
