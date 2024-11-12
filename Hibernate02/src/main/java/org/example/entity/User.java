package org.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 * The Class User.
 */
@Entity
@Table(name ="USERS")
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

	/*
	@ManyToMany
	private List<Community> communityMemberships = new ArrayList<Community>();


	@OneToMany(mappedBy = "creator")
	@BatchSize(size = 5)
	private List<Community> communitiesCreated = new ArrayList<Community>();
	

	@ManyToMany
	@Fetch(FetchMode.SUBSELECT)
	private List<Skill> skills = new ArrayList<Skill>();
	

	@ManyToMany
	private List<Tool> tools = new ArrayList<Tool>();
	

	@OneToMany(mappedBy = "user")
	private List<Donation> donations = new ArrayList<Donation>();
	

	@OneToMany(mappedBy = "submitter")
	@OrderColumn(name = "projectsSubmitted_index")
	private List<Project> projectsSubmitted = new ArrayList<Project>();
	

	@OneToMany(mappedBy = "organizer")
	@OrderColumn(name = "projectsOrganized_index")
	private List<Project> projectsOrganized = new ArrayList<Project>();
	

	@ManyToMany(mappedBy = "volunteers")
	private List<Project> projectsVolunteered = new ArrayList<Project>();
	

	@OneToMany(mappedBy = "submitter")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@OrderColumn(name = "comments_index") // Index necessary to know which element to lazy-init!
	private List<Comment> comments = new ArrayList<Comment>();
	

	@OneToMany(mappedBy = "organizer")
	private List<ServiceEvent> serviceEventsOrganized = new ArrayList<ServiceEvent>();
	*/
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	


}
