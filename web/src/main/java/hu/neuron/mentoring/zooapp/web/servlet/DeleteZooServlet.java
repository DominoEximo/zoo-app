package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet(name = "deleteZoo" ,urlPatterns = "/zoo/deleteZoo")
public class DeleteZooServlet extends HttpServlet {
    @Autowired
    ZooDaoService zooDaoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer id = Integer.parseInt(req.getParameter("zooID"));

        System.out.println(id);

        if (!zooDaoService.findById(id).equals(null)){
            zooDaoService.delete(zooDaoService.findById(id));
        }

        req.setAttribute("zoos", zooDaoService.getAll());
        req.getRequestDispatcher("zooList").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
