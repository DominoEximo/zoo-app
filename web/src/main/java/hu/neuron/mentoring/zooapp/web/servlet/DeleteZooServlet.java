package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Controller.Service.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/deleteZoo")
public class DeleteZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer id = Integer.parseInt(req.getParameter("zooID"));

        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();

        if (!zooDaoService.findById(id).equals(null)){
            zooDaoService.delete(zooDaoService.findById(id));
        }

        req.setAttribute("zoos", zooDaoService.getAll());
        req.getRequestDispatcher("listZoos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
