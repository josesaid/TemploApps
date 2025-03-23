package com.cursomicroservicios.ioc.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cursomicroservicios.ioc.entities.Order;
import com.cursomicroservicios.ioc.entities.Usuario;
import com.cursomicroservicios.ioc.notification.INotificacionService;
import com.cursomicroservicios.ioc.repository.UsuarioRepository;

/**
 * @author josesaidolanogarcia
 */
@Service
@Slf4j
public class UsuarioService {

	private final INotificacionService service;
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(INotificacionService service, UsuarioRepository usuarioRepository) {
		this.service=service;
		this.usuarioRepository=usuarioRepository;
	}
	
	public Usuario registrarUsuario(Usuario usuario) {
		log.info("Repositorio - Registrando usuario en la base de datos: " + usuario);
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		log.info("usuarioGuardado: " + usuarioGuardado);
		service.enviarNoticacion(usuario.getName(), "Usuario registrado, " + usuario.getName());
		return usuarioGuardado;
	}
	
	public Usuario crearUsuarioConOrdenes(Usuario usuario, List<Order> descripcionesOrdenes) {
		for (Order ord: descripcionesOrdenes) {
			usuario.addOrder(ord);
		}
		return usuarioRepository.save(usuario);
	}



	/**
	 *
	 * @param id
	 * @return
	 */
	public boolean eliminarUsuario(Long id) {
		try{
			usuarioRepository.deleteById(id);
			log.info("Repositorio - Usuario eliminado exitosamente");
			return true;
		}catch (Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
}
