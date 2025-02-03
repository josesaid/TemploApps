package com.mx.development.sample05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrototypeScopeExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("AwesomeBeans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("prototypeHelloWorld");
        objA.setMessage("I'm object A");

        System.out.println(objA.getMessage());

        HelloWorld objB = (HelloWorld) context.getBean("prototypeHelloWorld");
        System.out.println(objB.getMessage());
    }
}
