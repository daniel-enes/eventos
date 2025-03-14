package com.demandas.eventos.controller;

import java.util.List;
import java.util.Optional;

import com.sun.jdi.event.StepEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demandas.eventos.entity.User;
import com.demandas.eventos.entity.UserType;
import com.demandas.eventos.service.UserTypesService;
import com.demandas.eventos.service.UsersService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

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

			model.addAttribute("error", "O email informado já existe. Tente registrar um outro e-mail.");

			List<UserType> userTypes = userTypesService.getAll( );

			model.addAttribute("userTypes", userTypes);
			model.addAttribute("user", new User());

			return "register";
		}
		
		User newUser = usersService.addNew(user);

		return "redirect:/dashboard";
		
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {

		if(error !=null) {
			model.addAttribute("error", "usuário ou senha incorretos. Por favor, tente novamente");
		}

		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}

}
