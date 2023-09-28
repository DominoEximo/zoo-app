package hu.neuron.training.zooapp.web.servlet;

import Service.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.service.Controller.DaoController;
import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/AddZoo")
public class AddZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String name = req.getParameter("name");
        String directorName = req.getParameter("directorName");
        Date appointmentDate = Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = Date.valueOf((req.getParameter("birthDate")));

        String g = req.getParameter("gender");
        Character gender = g.charAt(0);

        Zoo newZoo = new Zoo(name);
        newZoo.setDirector(new Director(directorName, birthDate, appointmentDate, gender,newZoo));

        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();

        zooDaoService.save(newZoo);


        req.getRequestDispatcher("zooList").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
