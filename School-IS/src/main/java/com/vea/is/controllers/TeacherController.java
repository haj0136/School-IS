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
import com.vea.is.entities.Teacher;
import com.vea.is.services.TeacherService;

@Controller
public class TeacherController {

	private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;

	@GetMapping("/teachers")
	public String index(Model model){
		var persons = teacherService.findAll();
		model.addAttribute("teachers", persons);
		model.addAttribute("pplActiveSettings","active");
		return "listTeachers";
	}

	@GetMapping("/teachers/edit")
	public String showAddTeacherForm(Teacher t) {
		return "add-teacher";
	}

	@GetMapping("/teachers/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Person person = teacherService.findById(id);
        model.addAttribute("teacher", person);
        return "add-teacher";
    }

	@Secured("ROLE_ADMIN")
	@PostMapping("/teachers/add")
    public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "add-teacher";
        }

        teacherService.save(teacher);
        model.addAttribute("teachers", teacherService.findAll());
        try {
            response.sendRedirect("/teachers");
        } catch (IOException e) {
        	log.error(e.getMessage());
        }
        return "listTeachers";
    }
}
