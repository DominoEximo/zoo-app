package Service.Impl;

import Service.service.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.service.DAO.GondoZooDao;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GondoZooDaoServiceImpl implements GondoZooDaoService {

    @Autowired
    GondoZooDao gondoZooDao;

    @Override
    public List<GondoZoo> getAll() {
        List<GondoZoo> gondoZoos = null;
        gondoZoos = (List<GondoZoo>) gondoZooDao.findAll();
        return  gondoZoos;
    }

    @Override
    public GondoZoo findById(int id) {
        GondoZoo gondoZoo = null;
        gondoZoo = gondoZooDao.findById(id).get();
        return gondoZoo;
    }

    @Override
    public void save(GondoZoo gondoZoo) {
        gondoZooDao.save(gondoZoo);
    }

    @Override
    public void delete(GondoZoo gondoZoo) {
        gondoZooDao.delete(gondoZoo);
    }
}
