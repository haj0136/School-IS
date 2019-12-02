package com.vea.cv3.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vea.cv3.entities.Person;
import com.vea.cv3.repositories.IPersonRepository;

@Controller
public class PersonController {
	
	private final IPersonRepository personRepository;
	
	@Autowired
	public PersonController(IPersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@GetMapping("/signup")
	public String showSignUpForm(Person person) {
		return "add-person";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/adduser")
    public String addUser(@Valid Person person, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "add-person";
        }
         
        personRepository.save(person);
        model.addAttribute("persons", personRepository.findAll());
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }
	
	@GetMapping("/")
	public String index(Model model){
		model.addAttribute("persons", personRepository.findAll());
		return "index";
	}
	
	@GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        model.addAttribute("person", person);
        return "update-person";
    }
	
	@PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") long id, @Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-user";
        }
        
        personRepository.save(person);
        model.addAttribute("persons", personRepository.findAll());
        return "index";
    }
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		personRepository.delete(person);
	    model.addAttribute("persons", personRepository.findAll());
	    try {
            response.sendRedirect("/");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	    return "index";
	}
}
