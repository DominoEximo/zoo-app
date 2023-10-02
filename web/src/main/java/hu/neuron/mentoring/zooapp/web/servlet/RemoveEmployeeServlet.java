package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Controller.Service.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.Controller.Service.DaoService.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.service.Controller.Service.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.Employee;
import hu.neuron.mentoring.zooapp.core.Zoo;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
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




        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();
        GondoZooDaoService gondoZooDaoService = DaoController.getInstance().getGondoZooDaoService();
        CleanerDaoService cleanerDaoService = DaoController.getInstance().getCleanerDaoService();


        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer zooID = Integer.parseInt(req.getParameter("zooID"));
        String type = req.getParameter("class");



        Zoo currentZoo = zooDaoService.findById(zooID);

        if("class hu.neuron.mentoring.zooapp.service.GondoZoo".equals(type)){
            gondoZooDaoService.delete(gondoZooDaoService.findById(id));
        } else if ("class hu.neuron.mentoring.zooapp.service.Cleaner".equals(type)) {
            cleanerDaoService.delete(cleanerDaoService.findById(id));
        }


        List<Employee> employees = gondoZooDaoService.findByZoo(currentZoo.getId());
        employees.addAll(cleanerDaoService.findByZoo(currentZoo.getId()));
        req.setAttribute("employees", employees);
        req.setAttribute("id", zooID);


        req.getRequestDispatcher("listEmployee.jsp").forward(req, resp);

    }
}
