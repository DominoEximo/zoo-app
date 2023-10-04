package hu.neuron.mentoring.zooapp.service.controller;



import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.Zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NameManager {

    public NameManager() {
    }

    ZooDaoService zooDaoService;
    public static List<String> filterListByTerm(List<String> list, String term) {

        List<String> matching = list.stream()
                .filter(e -> e.toLowerCase().startsWith(term))
                .collect(Collectors.toList());

        return matching;
    }
    public List<String> zooNames(){

        List<String> list = new ArrayList<>();

        for (Zoo zoo : zooDaoService.getAll()){
            list.add(zoo.getName());
        }

        return list;
    }
}
