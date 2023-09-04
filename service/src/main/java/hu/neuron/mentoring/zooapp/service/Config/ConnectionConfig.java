package hu.neuron.mentoring.zooapp.service.Config;


import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.ZooDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hu.neuron.mentoring.zooapp.service")
public class ConnectionConfig {

    @Bean
    public ConnectionManager connectionManager() {
        return new ConnectionManager();
    }

    @Bean
    public ZooDao zooDao() {return new ZooDao();}

}
