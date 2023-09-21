package hu.neuron.mentoring.zooapp.service.Config;


import hu.neuron.mentoring.zooapp.service.Cleaner;
import hu.neuron.mentoring.zooapp.service.DAO.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hu.neuron.mentoring.zooapp.service")
public class ConnectionConfig {


    @Bean
    public ZooDao zooDao() {return new ZooDao();}

    @Bean
    public AnimalDao animalDao() {return new AnimalDao();}

    @Bean
    public EmployeeDao employeeDao() {return new EmployeeDao();}
    @Bean
    public GondoZooDao gondoZooDao() {return new GondoZooDao();}
    @Bean
    public CleanerDao cleanerDao() {return new CleanerDao();}

    @Bean
    public ReservationDao reservationDao() {return new ReservationDao();}

}
