package hu.neuron.mentoring.zooapp.web;

import hu.neuron.mentoring.zooapp.core.dao.ZooDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"hu.neuron.mentoring"})
@EnableJpaRepositories(basePackages="hu.neuron.mentoring")
@EnableTransactionManagement
@EntityScan(basePackages="hu.neuron.mentoring")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
