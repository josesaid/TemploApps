package com.example.security.SecurityApp.repository;


import com.example.security.SecurityApp.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author josesaidolanogarcia
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
}
