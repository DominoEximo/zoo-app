package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.*;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@WebServlet (urlPatterns = "/createReservationServlet")
public class CreateReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final String DB_URL = "jdbc:mysql://localhost:3306/zoo";
        final String USER = "root";
        final String PASS = "Xbox11223344";

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        Integer id = Integer.parseInt(req.getParameter("id"));

        String zooName = req.getParameter("zoo");
        String name = req.getParameter("name");
        Date reservationDate = java.sql.Date.valueOf((req.getParameter("reservationDate")));

        Date visitDate = java.sql.Date.valueOf((req.getParameter("visitDate")));


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

        currentZoo.get(0).reserve(new Reservation(id,name,reservationDate,visitDate,tickets,discount,price));

        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Inserting records into the table...");

            myStmt = myConn.prepareStatement("INSERT INTO reservation VALUES(?,?,?,?,?,?,?,?)");
            myStmt.setInt(1,id);
            myStmt.setInt(2, currentZoo.get(0).getId());
            myStmt.setString(3,name);
            myStmt.setDate(4,reservationDate);
            myStmt.setDate(5,visitDate);
            myStmt.setInt(6,id );
            myStmt.setInt(7,discount);
            myStmt.setInt(8,price);
            myStmt.executeUpdate();
            myStmt = myConn.prepareStatement("INSERT INTO ticket VALUES(?,?,?)");
            myStmt.setInt(1,id);
            myStmt.setString(2,ticketType);
            myStmt.setString(3,ticketVariant);
            myStmt.executeUpdate();
            myConn.close();




        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("currentZoo",currentZoo.get(0));
        req.setAttribute("price",price);

        req.getRequestDispatcher("/payForReserve.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
