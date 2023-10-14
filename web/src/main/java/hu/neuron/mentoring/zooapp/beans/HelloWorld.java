package hu.neuron.mentoring.zooapp.beans;

import org.springframework.stereotype.Controller;


import java.io.Serializable;


@Controller("helloWorld")
public class HelloWorld implements Serializable {


    private String message = "Hello World";
    public HelloWorld() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
