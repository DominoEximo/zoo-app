package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet (urlPatterns = "/deleteZoo")
public class DeleteZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();


        String name = req.getParameter("name");

        ConnectionManager manager = new ConnectionManager();

        ZooDao zooDao = new ZooDao(manager.getMyConn());



        for (Zoo zoo : storage.getZooList()) {
            if (name.equals(zoo.getName())) {
                zooDao.delete(zoo);
                manager.closeConnection();
                storage.removeZoo(zoo);
                break;
            }

        }






        req.setAttribute("zoos",storage.getZooList());

        req.getRequestDispatcher("listZoos.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
