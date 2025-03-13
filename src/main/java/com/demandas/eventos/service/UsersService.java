package com.demandas.eventos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demandas.eventos.entity.User;
import com.demandas.eventos.repository.UsersRepository;

@Service
public class UsersService {

	private final UsersRepository usersRepository;

	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public User addNew(User user) {
		
		user.setActive(true);
		user.setCreatedAt(new Date(System.currentTimeMillis()));

		User createdUser = usersRepository.save(user);
		
		return createdUser;
	}

	public Optional<User> getUserByEmail(String email) {

		return usersRepository.findByEmail(email);
	}
}
