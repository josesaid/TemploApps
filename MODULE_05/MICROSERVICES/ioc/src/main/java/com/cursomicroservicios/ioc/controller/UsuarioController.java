package com.cursomicroservicios.ioc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cursomicroservicios.ioc.dto.UsuarioBorrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cursomicroservicios.ioc.dto.CreateUsuarioConOrdenesRequest;
import com.cursomicroservicios.ioc.entities.Order;
import com.cursomicroservicios.ioc.entities.Usuario;
import com.cursomicroservicios.ioc.service.UsuarioService;

/**
 * @author josesaidolanogarcia
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/erick2")
	public String saludarSobrinoConSuma(){
		int x = 10;
		int y = 20;
		int z = x + y;
		return "saludando al sobrino con el resultado de la suma: " + z + "";
	}

	@GetMapping("/saludito")
	public String saludar(){
		return "Hola Mundo 2";
	}

	@DeleteMapping()
	public ResponseEntity<String> eliminarUsuario(@RequestParam("id") Long id){
		/*usuarioService.hallarUsuario(id);
		UsuarioBorrado usuarioBorrado = new UsuarioBorrado();
		usuarioBorrado.setId(id);
		usuarioBorrado.setNombre("Pedro");
		usuarioBorrado.setStatus("Borrado");
		return new ResponseEntity<>(usuarioBorrado, HttpStatus.OK);

		 */
		return new ResponseEntity<>( "usuario borrado: " + id, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario request) {
		Usuario usuarioGuardadoEnBD = usuarioService.registrarUsuario(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardadoEnBD);

		//return ResponseEntity.ok("Usuario Resgistrado Correctamente");
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
