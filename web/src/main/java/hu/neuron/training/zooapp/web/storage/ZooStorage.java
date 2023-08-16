package hu.neuron.training.zooapp.web.storage;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.ArrayList;
import java.util.List;

public class ZooStorage {

    private List<Zoo> zooList;

    public ZooStorage(){
        zooList = new ArrayList<>();
        Zoo zoo1 = new Zoo(new Director("Elemer",null,null,'m'));
        Zoo zoo2 = new Zoo(new Director("David",null,null,'m'));
        zoo1.addEmployee(new GondoZoo("Adam",null,null,'m',null));
        zoo1.addEmployee(new GondoZoo("Gabor",null,null,'m',null));
        zoo1.addEmployee(new GondoZoo("Mihály",null,null,'m',null));
        zoo1.addEmployee(new GondoZoo("Bela",null,null,'m',null));
        zoo2.addEmployee(new GondoZoo("Eva",null,null,'f',null));
        zoo2.addEmployee(new GondoZoo("Carl",null,null,'m',null));
        zoo2.addEmployee(new GondoZoo("Ed",null,null,'m',null));


        zooList.add(zoo1);
        zooList.add(zoo2);
    }

    public List<Zoo> getZooList() {
        return zooList;
    }
}
