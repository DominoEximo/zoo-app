package hu.neuron.training.zooapp.web.servlet;

import com.google.gson.Gson;
import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.io.IOException;
import java.util.List;

@WebServlet (name = "ZooNames",urlPatterns = "/ZooNames")
public class ZooNamesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);
        ConnectionManager manager = new ConnectionManager();
        ZooDao zooDao = ac.getBean(ZooDao.class);
        zooDao.connect(manager.getMyConn());

        String term = req.getParameter("term");
        String q = term.toLowerCase();

        List<String> names = zooDao.zooNames();
        List<String> filtered = zooDao.filterListByTerm(names,q);
        Gson gson = new Gson();
        String jsonArray = gson.toJson(filtered);

        resp.getWriter().write(jsonArray);
    }
}
