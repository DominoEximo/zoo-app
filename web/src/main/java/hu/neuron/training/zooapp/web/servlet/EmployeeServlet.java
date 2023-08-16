package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Zoo;
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

        Zoo zoo1 = new Zoo(new Director("Elemer",null,null,'m'));
        Zoo zoo2 = new Zoo(new Director("David",null,null,'m'));
        zoo1.addEmployee(new GondoZoo("Adam",null,null,'m',null));
        zoo1.addEmployee(new GondoZoo("Gabor",null,null,'m',null));
        zoo1.addEmployee(new GondoZoo("Mihály",null,null,'m',null));
        zoo1.addEmployee(new GondoZoo("Bela",null,null,'m',null));
        zoo2.addEmployee(new GondoZoo("Eva",null,null,'f',null));
        zoo2.addEmployee(new GondoZoo("Carl",null,null,'m',null));
        zoo2.addEmployee(new GondoZoo("Ed",null,null,'m',null));
        List<Zoo> zooList = new ArrayList();

        zooList.add(zoo1);
        zooList.add(zoo2);

        String name= req.getParameter("director");

        List<Zoo> currentZoo = new ArrayList<>();

        for(Zoo zoo : zooList)
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
