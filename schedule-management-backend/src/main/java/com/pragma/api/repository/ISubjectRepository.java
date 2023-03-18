package com.pragma.api.repository;

import com.pragma.api.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository de Spring para las operaciones CRUD sobre la tabla SUBJECT.
 */
@Repository
public interface ISubjectRepository extends JpaRepository<Subject,String> {
}
