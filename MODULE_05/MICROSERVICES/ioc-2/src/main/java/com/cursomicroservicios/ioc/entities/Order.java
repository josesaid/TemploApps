package com.cursomicroservicios.ioc.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descripcion")
	private String description;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Order() {
	}

	public Order(String descripcion) {
		this.description = descripcion;
	}
}
