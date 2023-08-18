package hu.neuron.training.zooapp.web.storage;

import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Species;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ZooStorage {

    private static ZooStorage single_instance = null;
    private List<Zoo> zooList;

    public ZooStorage(){
        zooList = new ArrayList<>();
        Zoo zoo1 = new Zoo("Debreceni Állatkert");
        Zoo zoo2 = new Zoo("Nyíregyházi Állatkert");
        try {
            zoo1.setDirector(new Director("David",new SimpleDateFormat("dd/MM/yyyy").parse("05/04/2002"),null,'m'));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            zoo2.setDirector(new Director("Elemer",new SimpleDateFormat("dd/MM/yyyy").parse("05/04/2002"),null,'m'));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        zoo1.addEmployee(new GondoZoo("Adam",null,null,'m',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));
        zoo1.addEmployee(new GondoZoo("Gabor",null,null,'m',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));
        zoo1.addEmployee(new GondoZoo("Mihály",null,null,'m',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));
        zoo1.addEmployee(new GondoZoo("Bela",null,null,'m',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));
        zoo2.addEmployee(new GondoZoo("Eva",null,null,'f',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));
        zoo2.addEmployee(new GondoZoo("Carl",null,null,'m',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));
        zoo2.addEmployee(new GondoZoo("Ed",null,null,'m',new ArrayList<>(List.of(Species.TIGER,Species.BEAR,Species.FOX))));


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

    public void addZoo(Zoo zoo) {zooList.add(zoo);}
}
