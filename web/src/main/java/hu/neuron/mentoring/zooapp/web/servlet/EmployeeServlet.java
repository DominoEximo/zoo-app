package hu.neuron.mentoring.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/zoo/employees")
public class EmployeeServlet extends HttpServlet {
    @Autowired
    ZooDaoService zooDaoService;
    @Autowired
    GondoZooDaoService gondoZooDaoService;
    @Autowired
    CleanerDaoService cleanerDaoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
                System.out.println(currentZoo.get(0).getId());
            }

        }

            List<Employee> employees = gondoZooDaoService.findByZoo(currentZoo.get(0).getId());
            employees.addAll(cleanerDaoService.findByZoo(currentZoo.get(0).getId()));
            req.setAttribute("employees", employees);




        req.setAttribute("id",currentZoo.get(0).getId());


        req.getRequestDispatcher("listEmployees").forward(req, resp);
        //resp.sendRedirect("listEmployee.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
