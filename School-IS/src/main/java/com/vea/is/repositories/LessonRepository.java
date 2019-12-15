package com.vea.is.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.entities.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

}
