package com.example.security.SecurityApp.configuration;

import com.example.security.SecurityApp.entities.Authority;
import com.example.security.SecurityApp.entities.User;
import com.example.security.SecurityApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

/**
 * @author josesaidolanogarcia
 */
@Configuration
public class DataLoader {

	@Bean
	public CommandLineRunner datos(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return a -> {
			User said = new User();
			said.setUsername("said");
			said.setPassword(passwordEncoder.encode("said"));
			said.setEnabled(true);
			said.setAuthorities(
					Set.of(
							new Authority(null, "said", "ROLE_ADMIN"),
							new Authority(null, "said", "ROLE_USER"),
							new Authority(null, "said", "ROLE_CUSTOMER")
					)
			);

			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setEnabled(true);
			admin.setAuthorities(Set.of(new Authority(null, "admin", "ROLE_ADMIN")));

			User user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("user"));
			user.setEnabled(true);
			user.setAuthorities(Set.of(new Authority(null, "user", "ROLE_USER")));
			
			User customer = new User();
			customer.setUsername("customer");
			customer.setPassword(passwordEncoder.encode("customer"));
			customer.setEnabled(true);
			customer.setAuthorities(
					Set.of( new Authority(null, "customer", "ROLE_CUSTOMER"))
			);
			
			userRepository.save(said);
			userRepository.save(admin);
			userRepository.save(user);
			userRepository.save(customer);
			
			System.out.print("Datos ingresados correctamente");
		};
	}
}
