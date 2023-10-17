package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Animal;
import hu.neuron.mentoring.zooapp.core.enums.Species;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.AnimalDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("animalView")
@ViewScoped
public class AnimalView implements Serializable {

    @Autowired
    private AnimalDaoService animalDaoService;

    @Autowired
    private ZooDaoService zooDaoService;

    private Animal selectedAnimal;

    private String animalName;

    private String species;

    private Date birthDate;

    private String gender;

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

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Animal> getAnimals(Integer zooId){
        return new ArrayList<>(animalDaoService.findByZoo(zooId));
    }

    public void deleteAnimal(){
        animalDaoService.delete(animalDaoService.findById(selectedAnimal.getId()));
        selectedAnimal = null;
    }

    public void addAnimal(Integer id)  {

        java.sql.Date sqlDate = new java.sql.Date(birthDate.getTime());

        Animal newAnimal = new Animal(Species.valueOf(species),animalName,sqlDate,gender.charAt(0),zooDaoService.findById(id));
        animalDaoService.save(newAnimal);
    }
}
