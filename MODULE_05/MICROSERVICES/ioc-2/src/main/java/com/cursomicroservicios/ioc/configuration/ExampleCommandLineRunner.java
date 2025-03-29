package com.cursomicroservicios.ioc.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author josesaidolanogarcia
 */
@Configuration
@Slf4j
public class ExampleCommandLineRunner {

	private static void run(String... args) {
		log.error("cualquier cosa que necesites 01");

		log.warn("cualquier cosa que necesites 02");

		log.info("cualquier cosa que necesites 03");
	}

	@Bean
	public CommandLineRunner said() {
		return args -> run(args);
	}
}
