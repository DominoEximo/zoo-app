package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.EmployeeDao;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
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

@WebServlet(urlPatterns = "/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);

        ConnectionManager manager = ac.getBean(ConnectionManager.class);
        ZooDao zooDao = new ZooDao(manager.getMyConn());
        EmployeeDao empDao = new EmployeeDao(manager.getMyConn());

        Integer id = Integer.parseInt(req.getParameter("id"));

        String employeeToBeRemoved = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDao.getAll()) {
            if (id.equals(zoo.getId())) {
                currentZoo.add(zoo);
            }

        }

        for (Employee employee : empDao.findById(currentZoo.get(0).getId())) {
            if (employeeToBeRemoved.equals(employee.getName())) {
                empDao.delete(employee);

                break;
            }
        }


        req.setAttribute("employees", empDao.findById(currentZoo.get(0).getId()));
        manager.closeConnection();

        req.getRequestDispatcher("listEmployee.jsp").forward(req, resp);

    }
}
