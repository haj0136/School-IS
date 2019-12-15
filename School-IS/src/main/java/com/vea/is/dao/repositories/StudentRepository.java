package com.vea.is.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
