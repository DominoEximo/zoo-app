package hu.neuron.mentoring.zooapp.beans;

import jakarta.faces.annotation.View;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@Component
@View("request")
public class HelloWorld {

    public HelloWorld() {
        System.out.println("HelloWorld started!");
    }

    public String getMessage() {
        return "Hello World!";
    }


}
