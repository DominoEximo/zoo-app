package hu.neuron.mentoring.zooapp.service.daoservice.DaoService;

import hu.neuron.mentoring.zooapp.core.Zoo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZooDaoService{

    List<Zoo> getAll();

    Zoo findById(int id);

    void save(Zoo zoo);

    void delete(Zoo zoo);

}
