package com.vea.is.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vea.is.security.UserInfo;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserInfo currentPrincipalName = (UserInfo)authentication.getPrincipal();
		return "mainPage";
	}
}
