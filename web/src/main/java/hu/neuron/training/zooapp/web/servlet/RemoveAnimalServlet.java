package hu.neuron.training.zooapp.web.servlet;

import hu.neuron.mentoring.zooapp.service.Animal;
import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import hu.neuron.mentoring.zooapp.service.DAO.EmployeeDao;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import hu.neuron.mentoring.zooapp.service.Zoo;
import hu.neuron.training.zooapp.web.storage.ZooStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/removeAnimal")
public class RemoveAnimalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionManager manager = new ConnectionManager();
        ZooDao zooDao = new ZooDao(manager.getMyConn());
        AnimalDao animalDao = new AnimalDao(manager.getMyConn());

        Integer zooID = Integer.parseInt(req.getParameter("zooID"));

        String animalToBeRemoved = req.getParameter("name");

        List<Zoo> currentZoo = new ArrayList<>();

        for (Zoo zoo : zooDao.getAll()) {
            if (zooID.equals(zoo.getId())) {
                currentZoo.add(zoo);
            }

        }

        for (Animal animal : animalDao.findById(currentZoo.get(0).getId())){
            if (animalToBeRemoved.equals(animal.getNickname())){
                animalDao.delete(animal);

                break;
            }
        }


        if (currentZoo.size() != 0 && !animalDao.findById(currentZoo.get(0).getId()).isEmpty()){
            req.setAttribute("animals",animalDao.findById(currentZoo.get(0).getId()));


        }
        req.setAttribute("id",currentZoo.get(0).getId());
        manager.closeConnection();

        req.getRequestDispatcher("listAnimals.jsp").forward(req,resp);



    }
}
