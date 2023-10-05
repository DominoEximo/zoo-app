package hu.neuron.mentoring.zooapp.service.controller;



import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.ZooDaoService;
import hu.neuron.mentoring.zooapp.core.entity.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class NameManager {
    @Autowired
    ZooDaoService zooDaoService;
    public NameManager() {
    }

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
