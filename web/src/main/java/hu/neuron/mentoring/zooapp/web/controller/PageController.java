package hu.neuron.mentoring.zooapp.web.controller;


import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("zoo")
public class PageController {

    @Autowired
    ZooDaoService zooDaoService;


    @GetMapping("/zooSelect")
    public String zooSelect(Model model) {
        return "zooSelect.jsf";
    }

    @GetMapping("/animals")
    public String animalSelect(Model model) {
        return "animals.jsp";
    }
    @GetMapping("/zooList")
    public String zooList(Model model) {
        model.addAttribute("zoos",zooDaoService.getAll());
        return "listZoos.jsp";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/listEmployees")
    public String employees(Model model) {
        return "listEmployee.jsp";
    }

    @GetMapping("/addZoo")
    public String addZoo(Model model) {
        return "createZoo.jsp";
    }


    @GetMapping("/listAnimals")
    public String animals(Model model) {
        return "listAnimals.jsp";
    }

    @GetMapping("/listReservations")
    public String listReservations(Model model) {
        return "listReservations.jsp";
    }

    @GetMapping("/createAnimal")
    public String createAnimal(Model model) {
        return "createAnimal.jsp";
    }

    @GetMapping("/createCleaner")
    public String createCleaner(Model model) {
        return "createCleaner.jsp";
    }

    @GetMapping("/createGondoZoo")
    public String createGondoZoo(Model model) {
        return "createGondoZoo.jsp";
    }

    @GetMapping("/createReservation")
    public String createReservation(Model model) {
        return "createReservation.jsp";
    }
    @GetMapping("/payForReserve")
    public String payForReserve(Model model){return "payForReserve.jsp";}

    @GetMapping("/home")
    public String home(Model model){return "/test.xhtml";}

}
