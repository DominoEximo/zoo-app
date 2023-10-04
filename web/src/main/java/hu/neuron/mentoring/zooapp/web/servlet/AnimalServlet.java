package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.AnimalDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import hu.neuron.mentoring.zooapp.core.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/animals")
public class AnimalServlet extends HttpServlet {





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();
        AnimalDaoService animalDaoService = DaoController.getInstance().getAnimalDaoService();
        String name = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }
        if (currentZoo.size() != 0) {
            req.setAttribute("animals", animalDaoService.findByZoo(currentZoo.get(0).getId()));
            req.setAttribute("id", currentZoo.get(0).getId());


        }

        req.getRequestDispatcher("/listAnimals.jsp").forward(req, resp);
        //resp.sendRedirect("listEmployee.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
