package Service.service;

import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.List;

public interface GondoZooDaoService {

    List<GondoZoo> getAll();

    GondoZoo findById(int id);

    void save(GondoZoo gondoZoo);

    void delete(GondoZoo gondoZoo);
}
