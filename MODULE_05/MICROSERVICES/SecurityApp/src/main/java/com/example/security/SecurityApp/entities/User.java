package com.example.security.SecurityApp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author josesaidolanogarcia
 */
@Data
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private Set<Authority> authorities = new HashSet<>();
}
