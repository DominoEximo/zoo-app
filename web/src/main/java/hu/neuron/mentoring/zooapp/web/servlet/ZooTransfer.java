package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;

import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/zoo/ZooTransfer")
public class ZooTransfer extends HttpServlet {
    @Autowired
    ZooDaoService zooDaoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        Integer id = Integer.parseInt(req.getParameter("zooID"));
        String source = req.getParameter("source");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (id.equals(zoo.getId())) {
                currentZoo.add(zoo);
            }

        }
        if (currentZoo.size() != 0) {
            req.setAttribute("currentZoo", currentZoo.get(0));
        }
        if (source.equals("animal")) {
            req.getRequestDispatcher("createAnimal").forward(req, resp);
        } else if (source.equals("cleaner")) {
            req.getRequestDispatcher("createCleaner").forward(req, resp);
        } else if (source.equals("gondozoo")) {
            req.getRequestDispatcher("createGondoZoo").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}