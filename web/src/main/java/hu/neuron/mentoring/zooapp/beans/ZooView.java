package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

@Data
@Controller("zooView")
@ViewScoped
public class ZooView implements Serializable {

    @Autowired
    private ZooDaoService zooDaoService;

    private Zoo selectedZoo;

    private String zooName;

    private String directorName;

    private Date birthDate;

    private Date appointmentDate;

    private String gender;

    public ZooView() {
    }


    public List<Zoo> getZoos(){
        return zooDaoService.getAll();
    }

    public void deleteZoo(){
        zooDaoService.delete(selectedZoo);
        selectedZoo = null;
    }

    public void addZoo(){
        Zoo newZoo = new Zoo(zooName);
        zooDaoService.save(newZoo);
    }
}