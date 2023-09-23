package hu.neuron.training.zooapp.web.servlet;

import Service.Impl.ZooDaoServiceImpl;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import hu.neuron.mentoring.zooapp.service.DAO.DaoManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/removeAnimal")
public class RemoveAnimalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDaoServiceImpl zooDaoServiceImpl = DaoManager.getInstance().getZooDaoServiceImpl();

        AnimalDao animalDao = DaoManager.getInstance().getAnimalDao();


        Integer animalID = Integer.parseInt(req.getParameter("animalID"));

        Integer zooID = Integer.parseInt(req.getParameter("zooID"));


        animalDao.delete( animalDao.findById(animalID));

        req.setAttribute("animals", animalDao.findbyZoo(Optional.ofNullable(zooDaoServiceImpl.findById(zooID))));
        req.setAttribute("id", zooID);


        req.getRequestDispatcher("listAnimals.jsp").forward(req, resp);


    }
}
