package hu.neuron.mentoring.zooapp.service.daoservice.Impl;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.GondoZooDaoService;
import hu.neuron.mentoring.zooapp.core.dao.GondoZooDao;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.entity.GondoZoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
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

    @Override
    public List<Employee> findByZoo(Integer zooId) {
        List<Employee> gondoZoos = gondoZooDao.findByZoo_id(zooId);
        return gondoZoos;
    }
}
