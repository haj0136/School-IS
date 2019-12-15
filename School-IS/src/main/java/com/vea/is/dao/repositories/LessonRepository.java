package com.vea.is.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.entities.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

}
