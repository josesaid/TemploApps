package com.example.security.SecurityApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author josesaidolanogarcia
 */
@RestController
public class UsuarioControlller {

	@GetMapping("/")
	public String home() {
		return "Bienvenidos";
	}
	
	@GetMapping("/user")
	public String usuarios() {
		return  "Vista de usuarios";
	}
	
	@GetMapping("/admin")
	public String administradores() {
		return  "Vista de administradores";
	}
	@GetMapping("/customer")
	public String customer() {
		return  "Vista de customer";
	}
	
}
