package Service.Impl;

import Service.service.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.Cleaner;
import hu.neuron.mentoring.zooapp.service.DAO.CleanerDao;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
