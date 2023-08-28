package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet (urlPatterns = "/deleteZoo")
public class DeleteZooServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ZooStorage storage = ZooStorage.getInstance();


        String name = req.getParameter("name");

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

        for (Zoo zoo : storage.getZooList()) {
            if (name.equals(zoo.getName())) {
                try {
                    myConn = DriverManager.getConnection(DB_URL,USER,PASS);

                    myStmt = myConn.prepareStatement("DELETE from zoo where id = ?");
                    myStmt.setInt(1,zoo.getId());
                    myStmt.executeUpdate();




                } catch (SQLException e) {
                    e.printStackTrace();
                }
                storage.removeZoo(zoo);
                break;
            }

        }






        req.setAttribute("zoos",storage.getZooList());

        req.getRequestDispatcher("listZoos.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
