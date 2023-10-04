package hu.neuron.mentoring.zooapp.web.servlet;


import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import hu.neuron.mentoring.zooapp.core.Employee;
import hu.neuron.mentoring.zooapp.core.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();

        GondoZooDaoService gondoZooDaoService = DaoController.getInstance().getGondoZooDaoService();

        CleanerDaoService cleanerDaoService = DaoController.getInstance().getCleanerDaoService();


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


        req.getRequestDispatcher("/listEmployee.jsp").forward(req, resp);
        //resp.sendRedirect("listEmployee.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
