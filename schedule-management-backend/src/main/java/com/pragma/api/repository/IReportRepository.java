package com.pragma.api.repository;
import com.pragma.api.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository de Spring para las operaciones CRUD.
 * esta parte la debe hacer cristina que es la pro de la base de datos.
 */
@Repository
public interface IReportRepository extends JpaRepository<Program, String> {
}