package com.mx.development.sample03;

import com.mx.development.sample03.domain.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Creating an object of Employee class
        Employee e = (Employee)applicationContext.getBean("employee");

        // Calling print() method inside main() method
        e.display();
    }
}
