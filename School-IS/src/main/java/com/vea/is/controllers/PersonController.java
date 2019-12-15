package com.vea.is.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vea.is.dao.entities.Person;
import com.vea.is.dao.entities.Student;
import com.vea.is.dao.entities.Teacher;
import com.vea.is.services.LessonService;
import com.vea.is.services.PersonService;

@Controller
public class PersonController {

	private final PersonService personService;
	private final LessonService lessonService;

	@Autowired
	public PersonController(PersonService pr, LessonService ls) {
		this.personService = pr;
		this.lessonService = ls;
	}

	@GetMapping("/signup")
	public String showSignUpForm(Person person) {
		return "add-person";
	}


	@Secured("ROLE_ADMIN")
	@GetMapping("/persons")
	public String index(Model model){
		var persons = personService.findAll();
		model.addAttribute("persons", persons);
		model.addAttribute("pplActiveSettings","active");
		return "persons";
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

	@GetMapping("/profile")
	public String userProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		var person = personService.findByLogin(authentication.getName());
		if(person instanceof Student) {
			model.addAttribute("student", person);
			model.addAttribute("lessons", lessonService.findAll());
			return "profile-student";
		} else if (person instanceof Teacher) {
			model.addAttribute("teacher", person);
			return "profile-teacher";
		}
		return "mainPage";
	}
}
