package com.vea.is.controllers;

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

import com.vea.is.entities.Person;
import com.vea.is.entities.Student;
import com.vea.is.entities.Teacher;
import com.vea.is.repositories.PersonRepository;
import com.vea.is.services.PersonService;
import com.vea.is.repositories.StudentRepository;
import com.vea.is.repositories.TeacherRepository;

@Controller
public class PersonController {
	
	private final PersonService personService;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	
	@Autowired
	public PersonController(PersonService pr, StudentRepository sr, TeacherRepository tr) {
		this.studentRepository = sr;
		this.teacherRepository = tr;
		this.personService = pr;
	}
	
	@GetMapping("/signup")
	public String showSignUpForm(Person person) {
		return "add-person";
	}
	
	@GetMapping("/student")
	public String showAddStudentForm(Student st) {
		return "add-student";
	}
	
	@GetMapping("/teacher")
	public String showAddTeacherForm(Teacher teacher) {
		return "add-teacher";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/adduser")
    public String addUser(@Valid Person person, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "add-person";
        }
         
        personService.save(person);
        model.addAttribute("persons", personService.findAll());
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/addstudent")
    public String addStudent(@Valid Student student, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "add-student";
        }
         
        studentRepository.save(student);
        model.addAttribute("persons", personService.findAll());
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/addteacher")
    public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "add-teacher";
        }
         
        teacherRepository.save(teacher);
        model.addAttribute("persons", personService.findAll());
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }
	
	@GetMapping("/index")
	public String index(Model model){
		var persons = personService.findAll();
		model.addAttribute("persons", persons);
		return "index";
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
	public String deleteUser(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		Person person = personService.findById(id);
		personService.delete(person);
	    model.addAttribute("persons", personService.findAll());
	    try {
            response.sendRedirect("/");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	    return "index";
	}
}
