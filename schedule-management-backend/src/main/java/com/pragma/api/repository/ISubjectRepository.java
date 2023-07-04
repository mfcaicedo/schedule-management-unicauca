package com.pragma.api.repository;

import com.pragma.api.model.Program;
import com.pragma.api.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla SUBJECT.
 */
@Repository
public interface ISubjectRepository extends JpaRepository<Subject, String> {

    boolean existsByNameAndProgram_ProgramId(String name, String programId);
//    @Query("SELECT CASE WHEN COUNT(s.subject_code) > 0 THEN true ELSE false END FROM subject s WHERE UPPER(s.name) LIKE UPPER(:name) AND UPPER(s.program_id) LIKE UPPER(:program_id)")
//    boolean existsByNameAndProgram(@Param("name") String name, @Param("program_id") String program_id);

    public List<Subject> findAllByProgramOrderBySemester(Program program);

    /*@Query(value = "SELECT * FROM SUBJECT s WHERE s.program_id = :id_program", nativeQuery = true)
    Page<Subject> findAllByProgramId(@Param("id_program") String Id, Pageable pageable);
    */
    Page<Subject> findAllByProgram_ProgramId(String programId, Pageable pageable);

    //Consulta para verificar si existe una materia que haga parte del programa a consultar
    boolean existsBy();

    //Metodo para consultar todos los semestres asociados a una materia y a un program_id
    @Query(value = "SELECT DISTINCT semester,program_id FROM subject WHERE program_id= :program_id ORDER BY semester ASC", nativeQuery = true)
    List<Object[]> findSemesterByProgramId(@Param("program_id") String program_id);

    @Query(value = "SELECT * FROM subject", nativeQuery = true)
    List<Subject> findAllProgram();
}
