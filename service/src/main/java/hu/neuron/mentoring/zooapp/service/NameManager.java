package hu.neuron.mentoring.zooapp.service;

import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NameManager {

    public NameManager() {
    }

    ZooDao zooDao;
    public static List<String> filterListByTerm(List<String> list, String term) {

        List<String> matching = list.stream()
                .filter(e -> e.toLowerCase().startsWith(term))
                .collect(Collectors.toList());

        return matching;
    }
    public List<String> zooNames(){

        List<String> list = new ArrayList<>();

        for (Zoo zoo : zooDao.findAll()){
            list.add(zoo.getName());
        }

        return list;
    }
}
