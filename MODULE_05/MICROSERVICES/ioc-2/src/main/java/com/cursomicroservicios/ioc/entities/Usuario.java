package com.cursomicroservicios.ioc.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre", nullable = false)
	@NotBlank(message = "El nombre no puede ser vacio")
	private String name;
	
	@Column(name="direccion", nullable = false)
	@NotBlank(message = "La direccion no puede ser vacia")
	private String direction;

	@Column(name="dni", nullable = false, length = 8)
	@NotBlank(message = "El dni no puede ser vacio")
	private String dni;

	@Column(name="email", nullable = false, unique = true)
	@Email(message = "ingrese un correo valido")
	private String email;
	
	@Column(name="registry")
	private LocalDate registry;
	
	@Column(name="last_update")
	private LocalDate lastUpdate;
	
	@Column(name="row_state")
	private String rowState;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();
	
	@PrePersist
	protected void onCreate() {
		this.registry=LocalDate.now();
		this.lastUpdate=LocalDate.now();
		this.rowState="ACTIVE";
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdate=LocalDate.now();
	}
	
	public void addOrder(Order ord) {
		ord.setUsuario(this);
		orders.add(ord);
	}
	public void removeOrder(Order ord) {
		ord.setUsuario(null);
		orders.remove(ord);
		
	}
}
