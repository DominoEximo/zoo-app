package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = new ZooStorage();

        String name= req.getParameter("director");

        List<Zoo> currentZoo = new ArrayList<>();

        for(Zoo zoo : storage.getZooList())
        {
            if (name.equals(zoo.getDirector().getName())) {
                currentZoo.add(zoo);
            }

        }

        req.setAttribute("currentZoo",currentZoo.get(0));
        req.getRequestDispatcher("/listEmployee.jsp").forward(req,resp);
        //resp.sendRedirect("listEmployee.jsp");





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
