package com.demandas.eventos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demandas.eventos.entity.User;
import com.demandas.eventos.repository.UsersRepository;

@Service
public class UsersService {

	private final UsersRepository usersRepository;
	public final PasswordEncoder passwordEncoder;

	@Autowired
	public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {

		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
	}

	
	public User addNew(User user) {
		
		user.setActive(true);
		user.setCreatedAt(new Date(System.currentTimeMillis()));

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User createdUser = usersRepository.save(user);

		//String userRole = user.getUserType().getRole();

		return createdUser;
	}

	/*
	public Object getCurrentUserProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();

			User user = usersRepository.findByEmail(username).orElseThrow(()_> new UsernameNotFoundException("Não encontrado " + "user"));

			int userId = user.getId();

			if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("")))

		}
	}
	 */

	public Optional<User> getUserByEmail(String email) {

		return usersRepository.findByEmail(email);
	}
}
