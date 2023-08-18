package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet ("/AddZoo")
public class AddZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();

        String name= req.getParameter("name");
        String directorName = req.getParameter("directorName");
        Date appointmentDate= null;
        try {
            appointmentDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("appointmentDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date birthDate= null;
        try {
            birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String g= req.getParameter("gender");
        Character gender = g.charAt(0);

        Zoo newZoo = new Zoo(name);
        newZoo.setDirector(new Director(directorName,birthDate,appointmentDate,gender));
        storage.addZoo(newZoo);

        req.getRequestDispatcher("zooList").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
