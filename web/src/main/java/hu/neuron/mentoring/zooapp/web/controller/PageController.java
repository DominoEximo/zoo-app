package hu.neuron.mentoring.zooapp.web.controller;


import hu.neuron.mentoring.zooapp.core.Zoo;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("zoo")
public class PageController {

    @Autowired
    ZooDaoService zooDaoService;

    @GetMapping("/zooSelect")
    public String zooSelect(Model model) {
        return "zooSelect.jsp";
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

    @GetMapping("/employees")
    public String employees(Model model) {
        return "listEmployee.jsp";
    }

    @GetMapping("/addZoo")
    public String addZoo(Model model) {
        return "createZoo.jsp";
    }

    @GetMapping("/deleteZoo")
    public String deleteZoo(Model model) {return "DeleteZooServlet";}
}
