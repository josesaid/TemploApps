package com.cursomicroservicios.ioc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomicroservicios.ioc.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByName(String nombre);
	Usuario findByEmail(String email);
	Usuario findByDirection(String direction);
	List<Usuario> findByEmailOrName(String email, String name);

	List<Usuario> findByRegistry(LocalDate registry);
}
