package hu.neuron.mentoring.zooapp.service.Config;


import Service.Impl.ZooDaoServiceImpl;
import hu.neuron.mentoring.zooapp.service.DAO.*;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("hu.neuron.mentoring.zooapp.service")
@EnableTransactionManagement
@EnableJpaRepositories(value = "hu.neuron.mentoring.zooapp.service.*",entityManagerFactoryRef="emf")
public class ConnectionConfig {

    @Bean
    public ZooDaoServiceImpl zooDaoServiceImpl() {return new ZooDaoServiceImpl();}



}
