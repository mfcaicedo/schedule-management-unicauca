package com.pragma.api.repository;

import com.pragma.api.model.Program;
import com.pragma.api.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * Repository de Spring para las operaciones CRUD sobre la tabla SUBJECT.
 */
@Repository
public interface ISubjectRepository extends JpaRepository<Subject,String> {

    @Query(value = "SELECT * FROM SUBJECT s WHERE s.program_id = :id_program", nativeQuery = true)
    Page<Subject> findAllByProgramId(@Param("id_program") String Id, Pageable pageable);

}
