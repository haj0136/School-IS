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

import com.vea.is.entities.Lesson;
import com.vea.is.services.LessonService;
import com.vea.is.services.SubjectService;
import com.vea.is.services.TeacherService;

@Controller
public class LessonController {

	private static final Logger log = LoggerFactory.getLogger(LessonController.class);

	@Autowired
	private LessonService lessonService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TeacherService teacherService;

	@GetMapping("/lessons")
	public String index(Model model) {
		var lessons = lessonService.findAll();
		model.addAttribute("lessons", lessons);
		model.addAttribute("lessonsActiveSettings", "active");
		return "listLessons";
	}

	@GetMapping("/lessons/edit")
	public String showEditLessonForm(Lesson lesson, Model model) {
		var subjects = subjectService.findAll();
		var teachers = teacherService.findAll();
		model.addAttribute("subjects", subjects);
		model.addAttribute("teachers", teachers);
		return "add-lesson";
	}

	@GetMapping("/lessons/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Lesson lesson = lessonService.findById(id);
		model.addAttribute("lesson", lesson);
		return "add-lesson";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/lessons/add")
	public String addLesson(@Valid Lesson lesson, BindingResult result, Model model, HttpServletResponse response) {
		if (result.hasErrors()) {
			return "add-lesson";
		}

		lessonService.save(lesson);
		try {
			response.sendRedirect("/lessons");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return "listLessons";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/lessons/delete/{id}")
	public String deleteSubject(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		Lesson lesson = lessonService.findById(id);
		lessonService.delete(lesson);
		try {
			response.sendRedirect("/lessons");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return "listLessons";
	}
}
