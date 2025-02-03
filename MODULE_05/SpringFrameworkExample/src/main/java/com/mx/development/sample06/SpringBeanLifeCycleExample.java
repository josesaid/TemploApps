package com.mx.development.sample06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifeCycleExample {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("SpringBe   anLifeCycle.xml");

        HelloWorld obj = (HelloWorld) context.getBean("aBean");
        obj.getMessage();
        context.registerShutdownHook();
    }
}
