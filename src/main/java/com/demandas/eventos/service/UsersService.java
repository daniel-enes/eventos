package com.demandas.eventos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Optional<User> getUserByEmail(String email) {

		return usersRepository.findByEmail(email);
	}
}
