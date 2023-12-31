package hu.neuron.mentoring.zooapp.web.servlet;

import com.google.gson.Gson;
import hu.neuron.mentoring.zooapp.service.controller.NameManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.List;

@WebServlet (name = "ZooNames",urlPatterns = "/zoo/ZooNames")
public class ZooNamesServlet extends HttpServlet {
    @Autowired
    NameManager nameManager;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");



        String term = req.getParameter("term");
        String q = term.toLowerCase();

        List<String> names = nameManager.zooNames();
        List<String> filtered = NameManager.filterListByTerm(names,q);
        Gson gson = new Gson();
        String jsonArray = gson.toJson(filtered);

        resp.getWriter().write(jsonArray);
    }
}
