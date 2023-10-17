package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller("autoCompleteView")
@SessionScope
public class AutoCompleteView implements Serializable {

    private String zooName;

    @Autowired
    private ZooDaoService zooDaoService;

    private Zoo currentZoo = new Zoo();

    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> zooList = new ArrayList<>();
        List<Zoo> zoos = zooDaoService.getAll();
        for (Zoo zoo : zoos) {
            zooList.add(zoo.getName());
        }

        return zooList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public String getZooName() {
        return zooName;
    }

    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

    public Zoo getCurrentZoo() {
        return currentZoo;
    }

    public void setCurrentZoo(Zoo currentZoo) {
        this.currentZoo = currentZoo;
    }

    public ZooDaoService getZooDaoService() {
        return zooDaoService;
    }

    public void setZooDaoService(ZooDaoService zooDaoService) {
        this.zooDaoService = zooDaoService;
    }

    public void setupCurrentZooByName(){

        for (Zoo zoo : zooDaoService.getAll() ){
            if (zoo.getName().equals(zooName)){
                currentZoo.setId(zoo.getId());
            }
        }

    }
}
