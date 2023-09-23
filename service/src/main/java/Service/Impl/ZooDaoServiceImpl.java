package Service.Impl;

import Service.service.ZooDaoService;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZooDaoServiceImpl implements ZooDaoService {


    public ZooDaoServiceImpl() {

    }

    @Autowired
    ZooDao zooDao;



    @Override
    public List<Zoo> getAll() {
        List<Zoo> zoos = null;
        zoos = (List<Zoo>) zooDao.findAll();
        return  zoos;
    }

    @Override
    public Zoo findById(int id) {
        Zoo zoo = null;
        zoo = zooDao.findById(id).get();
        return zoo;
    }

    @Override
    public void save(Zoo zoo) {
        zooDao.save(zoo);

    }

    @Override
    public void delete(Zoo zoo) {
        zooDao.delete(zoo);
    }
}
