package hu.neuron.mentoring.zooapp.service.daoservice.Impl;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.dao.ZooDao;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooDaoServiceImpl implements ZooDaoService {

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
        zoo = zooDao.findById(id).orElseGet(null);
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
