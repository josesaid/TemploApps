package com.example.security.SecurityApp.service;

import com.example.security.SecurityApp.entities.User;
import com.example.security.SecurityApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author josesaidolanogarcia
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository= userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		
		List<GrantedAuthority> authorities = user.getAuthorities()
				.stream().map(a-> new SimpleGrantedAuthority(
						a.getAuthority())).collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
				user.isEnabled(), true, true, true,
				authorities);
		
	}

}
