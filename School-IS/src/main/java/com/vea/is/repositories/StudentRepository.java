package com.vea.is.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
