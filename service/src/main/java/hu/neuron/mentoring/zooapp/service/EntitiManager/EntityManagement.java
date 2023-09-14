package hu.neuron.mentoring.zooapp.service.EntitiManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagement {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZooPU");
    EntityManager em = emf.createEntityManager();

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
