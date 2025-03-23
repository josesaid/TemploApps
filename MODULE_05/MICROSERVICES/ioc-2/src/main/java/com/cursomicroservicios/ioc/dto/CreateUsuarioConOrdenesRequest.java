package com.cursomicroservicios.ioc.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateUsuarioConOrdenesRequest {
	private String name;
	private String direction;
	private String dni;
	private String email;
	private List<String> descripciones;
}
