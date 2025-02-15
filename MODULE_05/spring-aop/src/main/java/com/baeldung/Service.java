package com.baeldung;

import org.springframework.stereotype.Component;

@Component
public class Service {

    @LogExecutionTime
    public void serve() throws InterruptedException {
        System.out.println("Serving");
        //Thread.sleep(3 * 1000);
        System.out.println("Already Served");
    }

    @AlexBehavior
    public void alex(){
        System.out.println("Hola Soy Alex...");
    }
}
