package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet(urlPatterns = "/removeAnimal")
public class RemoveAnimalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);


        ZooDao zooDao = ac.getBean(ZooDao.class);
        zooDao.connect();
        AnimalDao animalDao = ac.getBean(AnimalDao.class);


        Integer animalID = Integer.parseInt(req.getParameter("animalID"));

        Integer zooID = Integer.parseInt(req.getParameter("zooID"));


        animalDao.delete( animalDao.findById(animalID));

        req.setAttribute("animals", animalDao.findbyZoo(zooDao.findById(zooID)));
        req.setAttribute("id", zooID);


        req.getRequestDispatcher("listAnimals.jsp").forward(req, resp);


    }
}
