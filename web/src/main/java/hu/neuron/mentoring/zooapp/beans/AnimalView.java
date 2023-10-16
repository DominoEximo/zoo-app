package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Animal;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.AnimalDaoService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller("animalView")
@ViewScoped
public class AnimalView implements Serializable {

    @Autowired
    private AnimalDaoService animalDaoService;

    private Animal selectedAnimal;

    public AnimalView() {
    }

    public AnimalDaoService getAnimalDaoService() {
        return animalDaoService;
    }

    public void setAnimalDaoService(AnimalDaoService animalDaoService) {
        this.animalDaoService = animalDaoService;
    }

    public Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    public void setSelectedAnimal(Animal selectedAnimal) {
        this.selectedAnimal = selectedAnimal;
    }

    public List<Animal> getAnimals(Integer zooId){
        return new ArrayList<>(animalDaoService.findByZoo(zooId));
    }

    public void deleteAnimal(){
        animalDaoService.delete(selectedAnimal);
        selectedAnimal = null;
    }
}
