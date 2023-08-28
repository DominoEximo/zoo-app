package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.Zoo;
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

@WebServlet ("/AddZoo")
public class AddZooServlet extends HttpServlet {

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

        Integer id= Integer.parseInt(req.getParameter("id"));

        String name= req.getParameter("name");
        String directorName = req.getParameter("directorName");
        Date appointmentDate = Date.valueOf((req.getParameter("appointmentDate")));

        Date birthDate = Date.valueOf((req.getParameter("birthDate")));

        String g= req.getParameter("gender");
        Character gender = g.charAt(0);

        Zoo newZoo = new Zoo(name);
        newZoo.setDirector(new Director(newZoo.getId(), directorName,birthDate,appointmentDate,gender));
        newZoo.setId(id);
        storage.addZoo(newZoo);

        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Inserting records into the table...");

            myStmt = myConn.prepareStatement("INSERT INTO zoo VALUES(?,?,?,?,?,?)");
            myStmt.setInt(1,newZoo.getId());
            myStmt.setString(2, newZoo.getName());
            myStmt.setInt(3,newZoo.getId());
            myStmt.setInt(4,newZoo.getId());
            myStmt.setInt(5,newZoo.getId());
            myStmt.setInt(6,newZoo.getId());
            myStmt.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
        }
        storage.saveData();

        req.getRequestDispatcher("zooList").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
