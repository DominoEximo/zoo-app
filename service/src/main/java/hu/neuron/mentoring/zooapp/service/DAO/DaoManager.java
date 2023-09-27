package hu.neuron.mentoring.zooapp.service.DAO;

import Service.Impl.ZooDaoServiceImpl;
import hu.neuron.mentoring.zooapp.service.Connection.ContextManager;

public class DaoManager {

    private static DaoManager instance = null;

    private ZooDaoServiceImpl zooDaoServiceImpl = ContextManager.getInstance().getAc().getBean(ZooDaoServiceImpl.class);

    private GondoZooDao gondoZooDao = ContextManager.getInstance().getAc().getBean(GondoZooDao.class);

    private CleanerDao cleanerDao = ContextManager.getInstance().getAc().getBean(CleanerDao.class);

    private AnimalDao animalDao = ContextManager.getInstance().getAc().getBean(AnimalDao.class);

    private ReservationDao reservationDao = ContextManager.getInstance().getAc().getBean(ReservationDao.class);

    public static synchronized DaoManager getInstance(){
        if (instance == null){
            instance = new DaoManager();
        }
        return instance;
    }

    public ZooDaoServiceImpl getZooDaoServiceImpl() {
        return zooDaoServiceImpl;
    }

    public void setZooDao(ZooDaoServiceImpl zooDaoServiceImpl) {
        this.zooDaoServiceImpl = zooDaoServiceImpl;
    }
    public GondoZooDao getGondoZooDao() {
        return gondoZooDao;
    }

    public void setGondoZooDao(GondoZooDao gondoZooDao) {
        this.gondoZooDao = gondoZooDao;
    }

    public CleanerDao getCleanerDao() {
        return cleanerDao;
    }

    public void setCleanerDao(CleanerDao cleanerDao) {
        this.cleanerDao = cleanerDao;
    }

    public AnimalDao getAnimalDao() {
        return animalDao;
    }

    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    public ReservationDao getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
