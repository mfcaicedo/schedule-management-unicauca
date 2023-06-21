package com.pragma.api.services;

import com.pragma.api.domain.DepartmentDTO;

import java.util.List;

public interface IDepartmentService {
    /**
     * Metodo para encontrar todos los departamentos
     * @return Lista de departamentos
     */
    public List<DepartmentDTO> findAll();
}
