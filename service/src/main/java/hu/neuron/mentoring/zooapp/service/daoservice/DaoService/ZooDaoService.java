package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.entity.Zoo;

import java.util.List;


public interface ZooDaoService{

    List<Zoo> getAll();

    Zoo findById(int id);

    void save(Zoo zoo);

    void delete(Zoo zoo);

}
