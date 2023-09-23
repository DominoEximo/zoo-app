package Service.service;

import hu.neuron.mentoring.zooapp.service.Zoo;

import java.util.List;

public interface ZooDaoService{

    List<Zoo> getAll();

    Zoo findById(int id);

    void save(Zoo zoo);

    void delete(Zoo zoo);

}
