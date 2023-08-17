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

@WebServlet (urlPatterns = "/animals")
public class AnimalServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();

        String name= req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for(Zoo zoo : storage.getZooList())
        {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }
        if (currentZoo.size() != 0){
            req.setAttribute("currentZoo",currentZoo.get(0));
        }

        req.getRequestDispatcher("/listAnimals.jsp").forward(req,resp);
        //resp.sendRedirect("listEmployee.jsp");





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
