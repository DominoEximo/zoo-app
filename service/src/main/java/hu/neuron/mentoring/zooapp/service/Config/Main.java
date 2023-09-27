package hu.neuron.mentoring.zooapp.service.Config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@ComponentScan(basePackages = "hu.neuron.mentoring.zooapp.*")
@EnableJpaRepositories(basePackages="hu.neuron.mentoring.zooapp.service.DAO", entityManagerFactoryRef="emf")
@SpringBootApplication
@EntityScan("hu.neuron.mentoring.zooapp.*")
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/zoo2");
        dataSource.setUsername("root");
        dataSource.setPassword("Xbox11223344");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(h2DataSource());
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.schema-generation.database.action", "create");
        factory.setJpaProperties(properties);
        factory.setPackagesToScan("hu.neuron.mentoring.zooapp.*");
        return factory;
    }
}
