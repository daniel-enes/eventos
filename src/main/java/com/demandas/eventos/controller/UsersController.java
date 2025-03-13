package com.demandas.eventos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demandas.eventos.entity.User;
import com.demandas.eventos.entity.UserType;
import com.demandas.eventos.service.UserTypesService;
import com.demandas.eventos.service.UsersService;

import jakarta.validation.Valid;

@Controller
public class UsersController {

	private final UserTypesService userTypesService;
	private final UsersService usersService;
	
	@Autowired
	public UsersController(UserTypesService userTypesService, UsersService usersService) {
		
		this.userTypesService = userTypesService;
		this.usersService = usersService;
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		
		List<UserType> userTypes = userTypesService.getAll();
		
		model.addAttribute("userTypes", userTypes);
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register/new")
	public String userRegister(@Valid User user, Model model) {

		Optional<User> optionalUSer = usersService.getUserByEmail(user.getEmail());


		if(optionalUSer.isPresent()) {
			model.addAttribute("error", "" +
					"O email informado já existe. Tente registrar um outro e-mail." +
					"Email already registered, try to login or register with other email");

			List<UserType> userTypes = userTypesService.getAll();

			model.addAttribute("userTypes", userTypes);
			model.addAttribute("user", new User());

			return "register";
		}
		
		User newUser = usersService.addNew(user);

		return "dashboard";
		
	}
}
