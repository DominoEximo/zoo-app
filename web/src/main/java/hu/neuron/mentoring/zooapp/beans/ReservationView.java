package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Reservation;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ReservationDaoService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller("reservationView")
@ViewScoped
public class ReservationView implements Serializable {

    @Autowired
    private ReservationDaoService reservationDaoService;

    private Reservation selectedReservation;

    public ReservationView() {
    }

    public ReservationDaoService getReservationDaoService() {
        return reservationDaoService;
    }

    public void setReservationDaoService(ReservationDaoService reservationDaoService) {
        this.reservationDaoService = reservationDaoService;
    }

    public Reservation getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(Reservation selectedReservation) {
        this.selectedReservation = selectedReservation;
    }


    public void deleteReservation(){
        reservationDaoService.delete(selectedReservation);
        selectedReservation = null;
    }

    public List<Reservation> getReservation(Integer zooID){
        return new ArrayList<>(reservationDaoService.findByZoo(zooID));
    }
}
