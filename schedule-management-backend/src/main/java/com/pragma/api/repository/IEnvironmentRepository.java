package com.pragma.api.repository;

import com.pragma.api.model.Environment;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IEnvironmentRepository extends JpaRepository<Environment, Integer> {

    Page<Environment> findAllByFacultyFacultyId(String facultyId, Pageable pageable);

    Page<Environment> findAllByEnvironmentType(EnvironmentTypeEnumeration environmentType, Pageable pageable);

    Page<Environment> findAllByAvailableResourcesId(Integer resourceId, Pageable pageable);

    //Verificar si existe por lo menos un ambiente
    boolean existsBy();

    //consultamos los edificios que existen en el ambiente, por el id facultad
    @Query(value = "SELECT * FROM environment as e WHERE e.parent_id IS NULL AND e.faculty_id= :faculty_id", nativeQuery = true)
    //Se realiza busqueda de facultad mediante id para traer los edificios
    List<Environment> findAllBuildings(@Param("faculty_id") String faculty_id);

    //Realizamos la consulta personalizada para traer los ambientes con id, nombre y tipo, pasando el id facultad
    @Query(value = "SELECT id, name, environmentType FROM environment WHERE parent_id IS NULL AND faculty_id = :faculty_id", nativeQuery = true)
    List<Object[]> findEnvironmentDataByFacultyId(@Param("faculty_id") String facultyId);

    @Query(value = "SELECT * FROM environment  WHERE parent_id = :parent_id AND environmentType = :environmentType", nativeQuery = true)
    List<Environment> findAllByTypeAndParentId(@Param("parent_id") Integer parentId, @Param("environmentType") String environmentType);

}
