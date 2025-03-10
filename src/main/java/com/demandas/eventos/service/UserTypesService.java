package com.demandas.eventos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demandas.eventos.entity.UserType;
import com.demandas.eventos.repository.UserTypesRepository;

@Service
public class UserTypesService {

	private final UserTypesRepository userTypesRepository;
	
	@Autowired
	public UserTypesService(UserTypesRepository userTypesRepository) {
		this.userTypesRepository = userTypesRepository;
	}
	
	public List<UserType> getAll() {
		
		return userTypesRepository.findAll();
		
	}
	
}
