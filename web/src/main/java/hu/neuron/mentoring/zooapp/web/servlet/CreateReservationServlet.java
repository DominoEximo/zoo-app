package hu.neuron.mentoring.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.core.entity.Reservation;
import hu.neuron.mentoring.zooapp.core.entity.Ticket;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import hu.neuron.mentoring.zooapp.core.enums.TicketType;
import hu.neuron.mentoring.zooapp.core.enums.TicketVariant;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ReservationDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.web.controller.DaoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = "/zoo/createReservationServlet")
public class CreateReservationServlet extends HttpServlet {

    @Autowired
    ZooDaoService zooDaoService;
    @Autowired
    ReservationDaoService reservationDaoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String zooName = req.getParameter("zoo");
        String name = req.getParameter("name");
        Date reservationDate = java.sql.Date.valueOf((req.getParameter("reservationDate")));

        Date visitDate = java.sql.Date.valueOf((req.getParameter("visitDate")));


        String ticketType = req.getParameter("ticketType");
        Integer price = 0;
        Integer discount = 0;
        TicketType type = null;

        switch (ticketType) {
            case ("adult"): {
                type = TicketType.ADULT;
                price += 1000;
                break;
            }
            case ("kid"): {
                type = TicketType.KID;
                price += 500;
                break;
            }
            case ("retired"): {
                type = TicketType.RETIRED;
                price += 500;
                break;
            }
            case ("group"): {
                type = TicketType.GROUP;
                price += 3000;
                break;
            }
        }

        String ticketVariant = req.getParameter("ticketVariant");
        TicketVariant variant = null;

        switch (ticketVariant) {
            case ("fullDay"): {
                variant = TicketVariant.FULL_DAY;
                price += 1500;
                break;
            }
            case ("afternoon"): {
                variant = TicketVariant.AFTERNOON;
                price += 700;
                break;
            }
            case ("forenoon"): {
                variant = TicketVariant.FORENOON;
                price += 800;
                break;
            }

        }

        List<Ticket> tickets = new ArrayList<>();

        tickets.add(new Ticket(type, variant, price));

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()) {
            if (zooName.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }

        reservationDaoService.save(new Reservation( name, reservationDate, visitDate, tickets, discount, price,currentZoo.get(0)));


        req.setAttribute("currentZoo", currentZoo.get(0));
        req.setAttribute("price", price);

        req.getRequestDispatcher("payForReserve").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
