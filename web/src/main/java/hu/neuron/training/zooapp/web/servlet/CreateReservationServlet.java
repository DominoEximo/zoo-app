package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet (urlPatterns = "/createReservationServlet")
public class CreateReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();

        String zooName = req.getParameter("zoo");
        String name = req.getParameter("name");
        Date reservationDate= null;
        try {
            reservationDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("reservationDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date visitDate= null;
        try {
            visitDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("visitDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String ticketType = req.getParameter("ticketType");
        Integer price = 0;
        Integer discount = 0;
        TicketType type = null;

        switch (ticketType){
            case ("adult"):{
                type = TicketType.ADULT;
                price += 1000;
                break;
            }
            case ("kid"):{
                type = TicketType.KID;
                price += 500;
                break;
            }
            case ("retired"):{
                type = TicketType.RETIRED;
                price += 500;
                break;
            }
            case ("group"):{
                type = TicketType.GROUP;
                price += 3000;
                break;
            }
        }

        String ticketVariant = req.getParameter("ticketVariant");
        TicketVariant variant = null;

        switch (ticketVariant){
            case ("fullDay"):{
                variant = TicketVariant.FULL_DAY;
                price += 1500;
                break;
            }
            case ("afternoon"):{
                variant = TicketVariant.AFTERNOON;
                price += 700;
                break;
            }
            case ("forenoon"):{
                variant = TicketVariant.FORENOON;
                price += 800;
                break;
            }

        }

        List<Ticket> tickets = new ArrayList<>();

        tickets.add(new Ticket(type,variant,price));

        List<Zoo> currentZoo = new ArrayList<>();

        for(Zoo zoo : storage.getZooList())
        {
            if (zooName.equals(zoo.getName())) {
                currentZoo.add(zoo);
            }

        }

        currentZoo.get(0).reserve(new Reservation(name,reservationDate,visitDate,tickets,discount,price));

        req.setAttribute("currentZoo",currentZoo.get(0));
        req.setAttribute("price",price);

        req.getRequestDispatcher("/payForReserve.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
