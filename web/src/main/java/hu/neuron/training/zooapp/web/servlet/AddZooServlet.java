package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet ("/AddZoo")
public class AddZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();


        ResultSet myRs = null;

        Integer id= Integer.parseInt(req.getParameter("id"));

        String name= req.getParameter("name");
        String directorName = req.getParameter("directorName");
        Date appointmentDate = Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = Date.valueOf((req.getParameter("birthDate")));

        String g= req.getParameter("gender");
        Character gender = g.charAt(0);

        Zoo newZoo = new Zoo(name);
        newZoo.setDirector(new Director(newZoo.getId(), directorName,birthDate,appointmentDate,gender));
        newZoo.setId(id);
        storage.addZoo(newZoo);

        ConnectionManager manager = new ConnectionManager();
        ZooDao zooDao = new ZooDao(manager.getMyConn());

        zooDao.save(newZoo);
        manager.closeConnection();
        storage.saveData();

        req.getRequestDispatcher("zooList").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
