package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();

        String name = req.getParameter("zoo");

        String employeeToBeRemoved = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : storage.getZooList()) {
            if (name.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }

        for (Employee employee : currentZoo.get(0).getEployees()){
            if (employeeToBeRemoved.equals(employee.getName())){
                if (employee instanceof GondoZoo){
                    try {
                        currentZoo.get(0).fireGondoZoo((GondoZoo) employee);
                    } catch (ZooEmployeeException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    currentZoo.get(0).fireCleaner((Cleaner) employee);
                }
                break;
            }
        }

        storage.saveData();

        req.setAttribute("currentZoo",currentZoo.get(0));

        req.getRequestDispatcher("listEmployee.jsp").forward(req,resp);

    }
}
