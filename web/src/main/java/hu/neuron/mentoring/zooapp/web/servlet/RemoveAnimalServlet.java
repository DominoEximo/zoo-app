package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.AnimalDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/zoo/removeAnimal")
public class RemoveAnimalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();

        AnimalDaoService animalDaoService = DaoController.getInstance().getAnimalDaoService();


        Integer animalID = Integer.parseInt(req.getParameter("animalID"));

        Integer zooID = Integer.parseInt(req.getParameter("zooID"));


        animalDaoService.delete( animalDaoService.findById(animalID));

        req.setAttribute("animals", animalDaoService.findByZoo(zooDaoService.findById(zooID).getId()));
        req.setAttribute("id", zooID);


        req.getRequestDispatcher("listAnimals.jsp").forward(req, resp);


    }
}
