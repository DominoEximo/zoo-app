package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
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

@WebServlet(urlPatterns = "/deleteZoo")
public class DeleteZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("name");

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);

        ConnectionManager manager = ac.getBean(ConnectionManager.class);

        ZooDao zooDao = new ZooDao(manager.getMyConn());


        for (Zoo zoo : zooDao.getAll()) {
            if (name.equals(zoo.getName())) {
                zooDao.delete(zoo);
                break;
            }

        }

        req.setAttribute("zoos", zooDao.getAll());
        manager.closeConnection();
        req.getRequestDispatcher("listZoos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
