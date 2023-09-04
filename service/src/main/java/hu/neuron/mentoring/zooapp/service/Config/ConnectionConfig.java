package hu.neuron.mentoring.zooapp.service.Config;


import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hu.neuron.mentoring.zooapp.service.Connection")
public class ConnectionConfig {

    @Bean
    public ConnectionManager connectionManager() {
        return new ConnectionManager();
    }

}
