package hu.neuron.mentoring.zooapp.service.daoservice.Impl;

import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.core.entity.Cleaner;
import hu.neuron.mentoring.zooapp.core.dao.CleanerDao;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleanerDaoServiceImpl implements CleanerDaoService {

    @Autowired
    CleanerDao cleanerDao;

    @Override
    public List<Cleaner> getAll() {
        List<Cleaner> cleaners = null;
        cleaners = (List<Cleaner>) cleanerDao.findAll();
        return  cleaners;
    }

    @Override
    public Cleaner findById(int id) {
        Cleaner cleaner = null;
        cleaner = cleanerDao.findById(id).get();
        return cleaner;
    }

    @Override
    public void save(Cleaner cleaner) {
        cleanerDao.save(cleaner);
    }

    @Override
    public void delete(Cleaner cleaner) {
        cleanerDao.delete(cleaner);
    }

    @Override
    public List<Employee> findByZoo(Integer zooId) {
        List<Employee> cleaners = cleanerDao.findByZoo_id(zooId);
        return cleaners;
    }
}
