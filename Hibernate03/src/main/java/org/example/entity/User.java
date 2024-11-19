package org.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import org.example.converters.RatingAttributeConverter;
import org.example.enums.Rating;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;


/**
 * The Class User.
 */
@Entity
@Table(name ="USER")
@Data
public class User implements Serializable {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The email. */
	@Column
	private String email;

	/** The name. */
	@Column
	private String name;

	/** The phone. */
	@Column
	private String phone;

	@Convert(converter = RatingAttributeConverter.class)
	private Rating rating;

	@Column(name = "importantValue")
	@Formula("concat(name, '@', 'codegym.com')")
	public String importantValue;


	@Embedded
	public UserAddress userAddress;

}
