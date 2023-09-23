package hu.neuron.training.zooapp.web.servlet;

import Service.Impl.ZooDaoServiceImpl;
import hu.neuron.mentoring.zooapp.service.Connection.ContextManager;
import hu.neuron.mentoring.zooapp.service.DAO.DaoManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import hu.neuron.mentoring.zooapp.service.Zoo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ZooTransfer")
public class ZooTransfer extends HttpServlet {
    ApplicationContext ac = null;
    @Override
    public void init() throws ServletException {
        super.init();
        ac = ContextManager.getInstance().getAc();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        ZooDaoServiceImpl zooDaoServiceImpl = DaoManager.getInstance().getZooDaoServiceImpl();


        Integer id = Integer.parseInt(req.getParameter("zooID"));
        String source = req.getParameter("source");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoServiceImpl.getAll()) {
            if (id.equals(zoo.getId())) {
                currentZoo.add(zoo);
            }

        }
        if (currentZoo.size() != 0) {
            req.setAttribute("currentZoo", currentZoo.get(0));
        }
        if (source.equals("animal")) {
            req.getRequestDispatcher("/createAnimal.jsp").forward(req, resp);
        } else if (source.equals("cleaner")) {
            req.getRequestDispatcher("/createCleaner.jsp").forward(req, resp);
        } else if (source.equals("gondozoo")) {
            req.getRequestDispatcher("/createGondoZoo.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}