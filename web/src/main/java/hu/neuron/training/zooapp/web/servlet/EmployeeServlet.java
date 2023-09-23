package hu.neuron.training.zooapp.web.servlet;

import Service.Impl.ZooDaoServiceImpl;
import hu.neuron.mentoring.zooapp.service.DAO.*;
import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.Zoo;
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


        ZooDaoServiceImpl zooDaoServiceImpl = DaoManager.getInstance().getZooDaoServiceImpl();

        GondoZooDao gondoZooDao = DaoManager.getInstance().getGondoZooDao();

        CleanerDao cleanerDao = DaoManager.getInstance().getCleanerDao();


        String name = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoServiceImpl.getAll()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
                System.out.println(currentZoo.get(0).getId());
            }

        }

            List<Employee> employees = gondoZooDao.findByZoo(currentZoo.get(0));
            employees.addAll(cleanerDao.findByZoo(currentZoo.get(0)));
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
