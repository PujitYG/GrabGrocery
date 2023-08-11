package com.microservice.authservice.Config;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.authservice.DTO.ExceptionDTO;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.repository.AuthRepository;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthRepository authRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserAuthDetails> user = authRepository.findByUsernameIgnoreCase(username);
		
		if(!user.isPresent()) throw new UsernameNotFoundException("user Not found");

		UserAuthDetails userAuthDetails = user.get();
	
		Collection<? extends GrantedAuthority> roles= userAuthDetails.getRoles().stream()
														.map(role->"ROLE_"+role.toString())
														.map(SimpleGrantedAuthority::new)
														.collect(Collectors.toList());
		
		
		ApplicationUserDetails aud = new 
				ApplicationUserDetails(userAuthDetails.getUsername(),userAuthDetails.getPassword(),
						roles);
		
		
		return aud;
	}

}
