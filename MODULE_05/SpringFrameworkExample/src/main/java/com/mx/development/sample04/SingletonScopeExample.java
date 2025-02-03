package com.mx.development.sample04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonScopeExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("AwesomeBeans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("singletonHelloWorld");

        objA.setMessage("I'm object A");
        System.out.println(objA.getMessage());

        HelloWorld objB = (HelloWorld) context.getBean("singletonHelloWorld");
        System.out.println(objB.getMessage());
    }
}
