package hu.neuron.mentoring.zooapp.service.Controller.Service.Impl;

import hu.neuron.mentoring.zooapp.service.Controller.Service.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.dao.ZooDao;
import hu.neuron.mentoring.zooapp.core.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
