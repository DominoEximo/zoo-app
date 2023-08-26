package hu.neuron.training.zooapp.web.servlet;

import com.google.gson.Gson;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet (name = "ZooNames",urlPatterns = "/ZooNames")
public class ZooNamesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ZooStorage storage = ZooStorage.getInstance();

        String term = req.getParameter("term");
        String q = term.toLowerCase();

        List<String> names = storage.zooNames();
        List<String> filtered = storage.filterListByTerm(names,q);
        Gson gson = new Gson();
        String jsonArray = gson.toJson(filtered);

        resp.getWriter().write(jsonArray);
    }
}