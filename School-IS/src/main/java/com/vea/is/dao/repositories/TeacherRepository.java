package com.vea.is.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.entities.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}
