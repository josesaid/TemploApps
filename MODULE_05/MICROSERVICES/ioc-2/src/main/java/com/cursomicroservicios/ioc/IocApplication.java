package com.cursomicroservicios.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author josesaidolanogarcia
 */
@SpringBootApplication
@Slf4j
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
		log.info("Application started by Said manually");
		//logger - DONE!
		//CommandLineRunner - DONE!
		//jar to war
	}

}
