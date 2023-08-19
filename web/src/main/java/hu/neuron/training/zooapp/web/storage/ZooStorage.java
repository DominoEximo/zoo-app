package hu.neuron.training.zooapp.web.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.GondoZoo;
import hu.neuron.mentoring.zooapp.service.Species;
import hu.neuron.mentoring.zooapp.service.Zoo;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ZooStorage {

    private static ZooStorage single_instance = null;
    private List<Zoo> zooList;


    public ZooStorage(){
        zooList = new ArrayList<>();
        if (new File("C:\\Users\\PappD\\IdeaProjects\\zoo-app\\web\\src\\main\\resources\\data.json").exists()){
            this.loadData();
        }
    }

    public static synchronized ZooStorage getInstance()
    {
        if (single_instance == null)
            single_instance = new ZooStorage();

        return single_instance;
    }

    public List<Zoo> getZooList() {
        return zooList;
    }

    public void setZooList(List<Zoo> zooList) {
        this.zooList = zooList;
    }

    public void addZoo(Zoo zoo) {zooList.add(zoo);}

    public void saveData(){
        try {
            File file = new File("C:\\Users\\PappD\\IdeaProjects\\zoo-app\\web\\src\\main\\resources\\data.json");
            ObjectMapper objectMapper =new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.writeValue(file, getZooList());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {

            System.out.println("aAAAAAAAAAAAAAAAAAAA");
            try {
                List<Zoo> zoos = new ObjectMapper().readValue(new File("C:\\Users\\PappD\\IdeaProjects\\zoo-app\\web\\src\\main\\resources\\data.json"),new TypeReference<List<Zoo>>(){});
                setZooList(zoos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }
}
