package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
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

@WebServlet(urlPatterns = "/removeAnimal")
public class RemoveAnimalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);

        ConnectionManager manager = ac.getBean(ConnectionManager.class);
        ZooDao zooDao = ac.getBean(ZooDao.class);
        zooDao.connect();
        AnimalDao animalDao = ac.getBean(AnimalDao.class);
        animalDao.connect(manager.getMyConn());

        Integer animalID = Integer.parseInt(req.getParameter("animalID"));

        Integer zooID = Integer.parseInt(req.getParameter("zooID"));


        animalDao.delete( animalDao.findById(animalID));

        req.setAttribute("animals", zooDao.findById(zooID).getAnimals());
        req.setAttribute("id", zooID);
        manager.closeConnection();

        req.getRequestDispatcher("listAnimals.jsp").forward(req, resp);


    }
}
