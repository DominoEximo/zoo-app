package hu.neuron.training.zooapp.web.storage;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.ArrayList;
import java.util.List;

public class ZooStorage {

    private static ZooStorage single_instance = null;
    private List<Zoo> zooList;

    public ZooStorage(){
        zooList = new ArrayList<>();
        Zoo zoo1 = new Zoo("Debreceni Állatkert");
        Zoo zoo2 = new Zoo("Nyíregyházi Állatkert");
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

    public static synchronized ZooStorage getInstance()
    {
        if (single_instance == null)
            single_instance = new ZooStorage();

        return single_instance;
    }

    public List<Zoo> getZooList() {
        return zooList;
    }
}
