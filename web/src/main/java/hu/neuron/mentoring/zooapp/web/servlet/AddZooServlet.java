package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import hu.neuron.mentoring.zooapp.core.entity.Director;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AddZoo", urlPatterns = "/zoo/AddZoo")
public class AddZooServlet extends HttpServlet {
    @Autowired
    ZooDaoService zooDaoService;
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

        zooDaoService.save(newZoo);


        req.getRequestDispatcher("zooList").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
