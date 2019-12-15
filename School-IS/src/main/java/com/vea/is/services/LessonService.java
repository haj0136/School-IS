package com.vea.is.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.vea.is.dao.entities.Lesson;
import com.vea.is.dao.repositories.LessonRepository;

@Service
@DependsOn("personService")
public class LessonService {

	private static final Logger log = LoggerFactory.getLogger(LessonService.class);

	@Autowired
	private LessonRepository lessonRepository;


	public Lesson save(Lesson lesson) {
		return lessonRepository.save(lesson);
	}

	public List<Lesson> findAll() {
		return (List<Lesson>)lessonRepository.findAll();
	}

	public Lesson findById(long id) {
		return lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + id));
	}

	public void delete(Lesson lesson) {
		lessonRepository.delete(lesson);
	}
}
