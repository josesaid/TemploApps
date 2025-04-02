package com.example.security.SecurityApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author josesaidolanogarcia
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String authority;
	
}
