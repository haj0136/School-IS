package com.vea.is.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vea.is.services.SubjectService;

@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping("/subjects")
	public String index(Model model){
		var persons = subjectService.findAll();
		model.addAttribute("subjects", persons);
		model.addAttribute("subjectsActiveSettings","active");
		return "listSubjects";
	}
}
