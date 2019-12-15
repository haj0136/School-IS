package com.vea.is.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

}
