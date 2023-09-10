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
        ZooDao zooDao = ac.getBean(ZooDao.class);
        zooDao.connect();
        EmployeeDao empDao = ac.getBean(EmployeeDao.class);
        empDao.connect();

        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer zooID = Integer.parseInt(req.getParameter("zooID"));

        String employeeToBeRemoved = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        empDao.delete(empDao.findById(id));


        req.setAttribute("employees", zooDao.findById(zooID).getEployees());
        req.setAttribute("id", zooID);
        manager.closeConnection();

        req.getRequestDispatcher("listEmployee.jsp").forward(req, resp);

    }
}
