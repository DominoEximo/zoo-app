package hu.neuron.mentoring.zooapp.service.Connection;

import hu.neuron.mentoring.zooapp.service.Config.ConnectionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextManager {

    private static ContextManager instance = null;
    private ApplicationContext ac;

    public ContextManager(){
        ac = new AnnotationConfigApplicationContext(ConnectionConfig.class);
    }

    public static synchronized ContextManager getInstance(){
        if (instance == null){
            instance = new ContextManager();
        }
        return instance;
    }

    public ApplicationContext getAc(){
        return ac;
    }
}
