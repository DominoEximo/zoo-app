package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Employee;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ZooDao implements Dao<Zoo>{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZooPU");
    EntityManager em = emf.createEntityManager();


    @Autowired
    public ZooDao(){
    }
    public void connect(){}

    @Override
    public Zoo findById(int id) {
        return null;

    }

    @Override
    public List<Zoo> getAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Zoo> cq = cb.createQuery(Zoo.class);
        Root<Zoo> rootEntry = cq.from(Zoo.class);
        CriteriaQuery<Zoo> all = cq.select(rootEntry);

        TypedQuery<Zoo> allQuery = em.createQuery(all);
        return allQuery.getResultList();

    }

    public void save(Zoo newZoo) {


        em.getTransaction().begin();
        em.persist(newZoo);
        em.getTransaction().commit();
        emf.close();
        em.close();



    }

    @Override
    public void update(Zoo o, String[] params) {
    }

    @Override
    public void delete(Zoo zoo) {
        em.getTransaction().begin();
        em.remove(zoo);
        em.getTransaction().commit();
        emf.close();
        em.close();
    }

    public static List<String> filterListByTerm(List<String> list, String term) {

        List<String> matching = list.stream()
                .filter(e -> e.toLowerCase().startsWith(term))
                .collect(Collectors.toList());

        return matching;
    }
    public List<String> zooNames(){

        List<String> list = new ArrayList<>();

        for (Zoo zoo : this.getAll()){
            list.add(zoo.getName());
        }

        return list;
    }
}
