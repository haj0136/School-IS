package com.vea.is.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.vea.is.services.StudentService;

@Controller
public class StudentController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public String index(Model model){
		var persons = studentService.findAll();
		model.addAttribute("students", persons);
		model.addAttribute("pplActiveSettings","active");
		return "listStudents";
	}

	@GetMapping("/students/edit")
	public String showAddStudentForm(Student st) {
		return "add-student";
	}

	@GetMapping("/students/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Person person = studentService.findById(id);
        model.addAttribute("student", person);
        return "add-student";
    }

	@Secured("ROLE_ADMIN")
	@PostMapping("/students/add")
    public String addStudent(@Valid Student student, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentService.save(student);
        model.addAttribute("students", studentService.findAll());
        try {
            response.sendRedirect("/students");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return "listStudents";
    }
}
