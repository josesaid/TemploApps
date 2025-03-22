package com.cursomicroservicios.ioc.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursomicroservicios.ioc.entities.Order;
import com.cursomicroservicios.ioc.entities.Usuario;
import com.cursomicroservicios.ioc.notification.INotificacionService;
import com.cursomicroservicios.ioc.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final INotificacionService service;
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(INotificacionService service, UsuarioRepository usuarioRepository) {
		this.service=service;
		this.usuarioRepository=usuarioRepository;
	}
	
	public Usuario registrarUsuario(Usuario usuario) {

		System.out.println("Registrando usuario: " + usuario.getName());
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		
		service.enviarNoticacion(usuario.getName(), "Usuario registrado, " + usuario.getName());

		return usuarioGuardado;
	}
	
	public Usuario crearUsuarioConOrdenes(Usuario usuario, List<Order> descripcionesOrdenes) {
		for (Order ord: descripcionesOrdenes) {
			usuario.addOrder(ord);
		}
		return usuarioRepository.save(usuario);
	}

	public void hallarUsuario(Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()){
			usuarioRepository.delete(usuarioOptional.get());
		}
	}
}
