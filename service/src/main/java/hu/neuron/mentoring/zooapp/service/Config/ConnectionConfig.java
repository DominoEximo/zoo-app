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
@EnableJpaRepositories(value = "hu.neuron.mentoring.zooapp.service.DAO")

public class ConnectionConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/zoo2");
        dataSource.setUsername("root");
        dataSource.setPassword("Xbox11223344");
        return dataSource;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("hu.neuron.mentoring.zooapp.service");
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setJpaPropertyMap(jpaProperties);
        return emf;
    }

    @Bean
    public ZooDaoServiceImpl zooDaoServiceImpl() {return new ZooDaoServiceImpl();}


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
