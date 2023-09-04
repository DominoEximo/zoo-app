package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.EmployeeDao;
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

@WebServlet(urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);

        ConnectionManager manager = ac.getBean(ConnectionManager.class);
        ZooDao zooDao = ac.getBean(ZooDao.class);
        zooDao.connect(manager.getMyConn());
        EmployeeDao empDao = new EmployeeDao(manager.getMyConn());

        String name = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDao.getAll()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }
        if (currentZoo.size() != 0 && !empDao.findById(currentZoo.get(0).getId()).isEmpty()) {
            req.setAttribute("employees", empDao.findById(currentZoo.get(0).getId()));

        }

        req.setAttribute("id", String.valueOf(currentZoo.get(0).getId()));
        manager.closeConnection();

        req.getRequestDispatcher("/listEmployee.jsp").forward(req, resp);
        //resp.sendRedirect("listEmployee.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
