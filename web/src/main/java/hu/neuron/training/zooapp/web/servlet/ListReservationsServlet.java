package hu.neuron.training.zooapp.web.servlet;

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

@WebServlet (urlPatterns = "/listReservationsServlet")
public class ListReservationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("zoo");

        ZooStorage storage = ZooStorage.getInstance();

        List<Zoo> currentZoo = new ArrayList<>();

        for(Zoo zoo : storage.getZooList())
        {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }

        req.setAttribute("currentZoo",currentZoo.get(0));

        storage.saveData();

        req.getRequestDispatcher("/listReservations.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}