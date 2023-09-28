package hu.neuron.training.zooapp.web.servlet;

import Service.DaoService.ReservationDaoService;
import Service.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.service.Controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/listReservationsServlet")
public class ListReservationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        ZooDaoService zooDaoService = DaoController.getInstance().getZooDaoService();

        ReservationDaoService reservationDaoService = DaoController.getInstance().getReservationDaoService();



        req.setAttribute("Zoos", zooDaoService.getAll());
        req.setAttribute("Reservations", reservationDaoService);


        req.getRequestDispatcher("/listReservations.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
