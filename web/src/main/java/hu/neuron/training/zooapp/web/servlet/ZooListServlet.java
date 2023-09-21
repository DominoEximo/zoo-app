package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.DAO.DaoManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet (urlPatterns = "/zooList")
public class ZooListServlet extends HttpServlet {
    ApplicationContext ac = null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDao zooDao = DaoManager.getInstance().getZooDao();

        req.setAttribute("zoos",zooDao.getAll());

        req.getRequestDispatcher("/listZoos.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
