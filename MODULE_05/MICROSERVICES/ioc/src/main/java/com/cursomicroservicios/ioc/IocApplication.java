package com.cursomicroservicios.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The IocApplication serves as the entry point for the Spring Boot application.
 *
 * This class is annotated with @SpringBootApplication which indicates it as a Spring Boot application configuration
 * class and enables auto-configuration, component scanning, and configuration properties for the entire application.
 *
 * It also uses the @Slf4j annotation to integrate logging capabilities through the SLF4J API,
 * which can facilitate logging operations throughout the application lifecycle.
 *
 * The main() method is the starting point for the application execution,
 * using SpringApplication.run() to bootstrap and launch the application from a Java main method.
 * @author josesaidolanogarcia
 */
@SpringBootApplication
@Slf4j
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
		//logger
		//CommandLineRunner
		//Microservice consumiendo a otro
	}

}
