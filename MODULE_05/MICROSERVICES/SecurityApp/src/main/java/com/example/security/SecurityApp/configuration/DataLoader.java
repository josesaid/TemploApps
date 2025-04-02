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
			User jose = new User();
			jose.setUsername("codegym");
			jose.setPassword(passwordEncoder.encode("codegym"));
			jose.setEnabled(true);
			jose.setAuthorities(Set.of(new Authority(null, "codegym", "ROLE_ADMIN")));

			User codegym = new User();
			codegym.setUsername("codegym2");
			codegym.setPassword(passwordEncoder.encode("codegym"));
			codegym.setEnabled(true);
			codegym.setAuthorities(Set.of(new Authority(null, "codegym2", "ROLE_ADMIN")));

			User maria = new User();
			maria.setUsername("maria");
			maria.setPassword(passwordEncoder.encode("maria"));
			maria.setEnabled(true);
			maria.setAuthorities(Set.of(new Authority(null, "maria", "ROLE_USER")));
			
			User melissa = new User();
			melissa.setUsername("juan");
			melissa.setPassword(passwordEncoder.encode("juan"));
			melissa.setEnabled(true);
			melissa.setAuthorities(
					Set.of( new Authority(null, "juan", "ROLE_ADMIN"),
							new Authority(null, "juan", "ROLE_USER")
					)
			);
			
			userRepository.save(jose);
			userRepository.save(codegym);
			userRepository.save(maria);
			userRepository.save(melissa);
			
			System.out.print("Datos ingresados correctamente");
		};
	}
}
