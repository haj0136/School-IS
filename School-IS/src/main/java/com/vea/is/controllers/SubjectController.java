package com.vea.is.controllers;

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

import com.vea.is.dao.entities.Subject;
import com.vea.is.services.SubjectService;

@Controller
public class SubjectController {

	private static final Logger log = LoggerFactory.getLogger(SubjectController.class);

	@Autowired
	private SubjectService subjectService;

	@GetMapping("/subjects")
	public String index(Model model) {
		var subjects = subjectService.findAll();
		model.addAttribute("subjects", subjects);
		model.addAttribute("subjectsActiveSettings", "active");
		return "listSubjects";
	}

	@GetMapping("/subjects/edit")
	public String showEditSubjectForm(Subject st) {
		return "add-subject";
	}

	@GetMapping("/subjects/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Subject person = subjectService.findById(id);
		model.addAttribute("subject", person);
		return "add-subject";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/subjects/add")
	public String addSubject(@Valid Subject subject, BindingResult result, Model model, HttpServletResponse response) {
		if (result.hasErrors()) {
			return "add-subject";
		}

		subjectService.save(subject);
		return "redirect:/subjects";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/subjects/delete/{id}")
	public String deleteSubject(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		Subject subject = subjectService.findById(id);
		subjectService.delete(subject);
		return "redirect:/subjects";
	}
}
