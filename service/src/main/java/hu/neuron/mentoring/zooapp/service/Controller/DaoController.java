package hu.neuron.mentoring.zooapp.service.Controller;

import Service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

@Controller("DaoController")
@SessionScope
public class DaoController {

    private static DaoController instance = null;

    @Autowired
    private ZooDaoService zooDaoService;

    @Autowired
    private GondoZooDaoService gondoZooDaoService;

    @Autowired
    private CleanerDaoService cleanerDaoService;

    @Autowired
    private AnimalDaoService animalDaoService;

    @Autowired
    private ReservationDaoService reservationDaoService;


    public static synchronized DaoController getInstance(){
        if (instance == null){
            instance = new DaoController();
        }
        return instance;
    }

    public ZooDaoService getZooDaoService() {
        return zooDaoService;
    }

    public void setZooDaoService(ZooDaoService zooDaoService) {
        this.zooDaoService = zooDaoService;
    }

    public GondoZooDaoService getGondoZooDaoService() {
        return gondoZooDaoService;
    }

    public void setGondoZooDaoService(GondoZooDaoService gondoZooDaoService) {
        this.gondoZooDaoService = gondoZooDaoService;
    }

    public CleanerDaoService getCleanerDaoService() {
        return cleanerDaoService;
    }

    public void setCleanerDaoService(CleanerDaoService cleanerDaoService) {
        this.cleanerDaoService = cleanerDaoService;
    }

    public AnimalDaoService getAnimalDaoService() {
        return animalDaoService;
    }

    public void setAnimalDaoService(AnimalDaoService animalDaoService) {
        this.animalDaoService = animalDaoService;
    }

    public ReservationDaoService getReservationDaoService() {
        return reservationDaoService;
    }

    public void setReservationDaoService(ReservationDaoService reservationDaoService) {
        this.reservationDaoService = reservationDaoService;
    }
}
