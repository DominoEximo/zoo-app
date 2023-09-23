package hu.neuron.mentoring.zooapp.service.EntitiManager;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

public class EntityManagement {
    @Autowired
    EntityManagerFactory emf;
    @Autowired
    EntityManager em;

    private static EntityManagement singleInstance = null;

    public EntityManagement(){}

    public static synchronized EntityManagement getInstance(){
        if (singleInstance == null){
            return new EntityManagement();
        }else{
            return singleInstance;
        }
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
