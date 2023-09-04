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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ZooTransfer")
public class ZooTransfer extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);

        ConnectionManager manager = ac.getBean(ConnectionManager.class);
        ZooDao zooDao = ac.getBean(ZooDao.class);
        zooDao.connect(manager.getMyConn());

        Integer id = Integer.parseInt(req.getParameter("zooID"));
        String source = req.getParameter("source");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDao.getAll()) {
            if (id.equals(zoo.getId())) {
                currentZoo.add(zoo);
            }

        }
        if (currentZoo.size() != 0) {
            req.setAttribute("currentZoo", currentZoo.get(0));
        }
        if (source.equals("animal")) {
            req.getRequestDispatcher("/createAnimal.jsp").forward(req, resp);
        } else if (source.equals("cleaner")) {
            req.getRequestDispatcher("/createCleaner.jsp").forward(req, resp);
        } else if (source.equals("gondozoo")) {
            req.getRequestDispatcher("/createGondoZoo.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}