package hu.neuron.mentoring.zooapp.service.Config;


import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.DAO.AnimalDao;
import hu.neuron.mentoring.zooapp.service.DAO.EmployeeDao;
import hu.neuron.mentoring.zooapp.service.DAO.ReservationDao;
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

    @Bean
    public AnimalDao animalDao() {return new AnimalDao();}

    @Bean
    public EmployeeDao employeeDao() {return new EmployeeDao();}

    @Bean
    public ReservationDao reservationDao() {return new ReservationDao();}

}
