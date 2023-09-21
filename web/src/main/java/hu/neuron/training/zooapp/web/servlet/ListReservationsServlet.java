package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.DAO.DaoManager;
import hu.neuron.mentoring.zooapp.service.DAO.ReservationDao;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet(urlPatterns = "/listReservationsServlet")
public class ListReservationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDao zooDao = DaoManager.getInstance().getZooDao();

        ReservationDao resDao = DaoManager.getInstance().getReservationDao();



        req.setAttribute("Zoos", zooDao.getAll());
        req.setAttribute("Reservations", resDao);


        req.getRequestDispatcher("/listReservations.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
