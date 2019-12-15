package com.vea.is.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vea.is.entities.Person;
import com.vea.is.entities.Student;
import com.vea.is.entities.Teacher;
import com.vea.is.services.PersonService;

@Controller
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService pr) {
		this.personService = pr;
	}

	@GetMapping("/signup")
	public String showSignUpForm(Person person) {
		return "add-person";
	}


	@GetMapping("/persons")
	public String index(Model model){
		var persons = personService.findAll();
		model.addAttribute("persons", persons);
		model.addAttribute("pplActiveSettings","active");
		return "persons";
	}

	@GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "update-person";
    }

	@PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") long id, @Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-user";
        }

        personService.save(person);
        model.addAttribute("persons", personService.findAll());
        return "index";
    }

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model, HttpServletResponse response, HttpServletRequest request) {
		Person person = personService.findById(id);
		personService.delete(person);
		var test = request.getRequestURI();
	    try {
	    	if (person instanceof Student) {
				response.sendRedirect("/students");
			} else if(person instanceof Teacher) {
				response.sendRedirect("/teachers");
			}
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	    return "persons";
	}
}
