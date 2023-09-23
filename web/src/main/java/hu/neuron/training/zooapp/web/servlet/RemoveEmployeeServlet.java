package hu.neuron.training.zooapp.web.servlet;

import Service.Impl.ZooDaoServiceImpl;
import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.mentoring.zooapp.service.DAO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDaoServiceImpl zooDaoServiceImpl = DaoManager.getInstance().getZooDaoServiceImpl();
        GondoZooDao gondoZooDao = DaoManager.getInstance().getGondoZooDao();
        CleanerDao cleanerDao = DaoManager.getInstance().getCleanerDao();


        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer zooID = Integer.parseInt(req.getParameter("zooID"));
        String type = req.getParameter("class");



        Zoo currentZoo = zooDaoServiceImpl.findById(zooID);

        if("class hu.neuron.mentoring.zooapp.service.GondoZoo".equals(type)){
            gondoZooDao.delete(gondoZooDao.findById(id));
        } else if ("class hu.neuron.mentoring.zooapp.service.Cleaner".equals(type)) {
            cleanerDao.delete(cleanerDao.findById(id));
        }


        List<Employee> employees = gondoZooDao.findByZoo(currentZoo);
        employees.addAll(cleanerDao.findByZoo(currentZoo));
        req.setAttribute("employees", employees);
        req.setAttribute("id", zooID);


        req.getRequestDispatcher("listEmployee.jsp").forward(req, resp);

    }
}
