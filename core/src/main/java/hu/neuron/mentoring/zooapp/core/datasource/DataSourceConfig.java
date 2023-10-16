package hu.neuron.mentoring.zooapp.core.datasource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import javax.sql.DataSource;
import java.util.Properties;

@ComponentScan(basePackages = "hu.neuron.mentoring.zooapp.*")
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/zoo3");
        dataSource.setUsername("root");
        dataSource.setPassword("Xbox11223344");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Properties properties = new Properties();
        properties.setProperty("jakarta.persistence.schema-generation.database.action", "validate");
        factory.setJpaProperties(properties);
        factory.setPackagesToScan("hu.neuron.mentoring.zooapp.*");
        return factory;
    }


}
