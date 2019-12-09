package com.vea.is.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.entities.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}
