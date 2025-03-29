package com.cursomicroservicios.ioc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cursomicroservicios.ioc.dto.UsuarioBorrado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cursomicroservicios.ioc.dto.CreateUsuarioConOrdenesRequest;
import com.cursomicroservicios.ioc.entities.Order;
import com.cursomicroservicios.ioc.entities.Usuario;
import com.cursomicroservicios.ioc.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@Slf4j
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/saludito")
	public String saludar(){
		log.info("saludar");
		return "Hola Mundo 5";
	}

	@DeleteMapping()
	public ResponseEntity<String> eliminarUsuario(@RequestParam("id") Long id){
		boolean usuarioEliminado = usuarioService.eliminarUsuario(id);
		log.info("Controller - Usuario eliminado? - " + usuarioEliminado);
		if(!usuarioEliminado){
			return new ResponseEntity<>( "El usuario: " + id + "NO fue eliminado exitosamente", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>( "usuario borrado: " + id, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario request) {
		Usuario usuarioGuardadoEnBD = usuarioService.registrarUsuario(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardadoEnBD);
	}

	@PostMapping("/registrarordenes")
	public ResponseEntity<Usuario> registrarUsuarioConOrdenes(@RequestBody CreateUsuarioConOrdenesRequest registro) {
		Usuario usuario = new Usuario();
		usuario.setName(registro.getName());
		usuario.setDirection(registro.getDirection());
		usuario.setDni(registro.getDni());
		usuario.setEmail(registro.getEmail());

		List<Order> orders = new ArrayList<>();
		for (String descripcion : registro.getDescripciones()) {
			orders.add(new Order(descripcion));
		}
		Usuario usuariocreado = usuarioService.crearUsuarioConOrdenes(usuario, orders);

		return new ResponseEntity<>(usuariocreado, HttpStatus.CREATED);
	}
}
