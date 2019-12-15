package com.vea.is.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vea.is.dao.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

}
