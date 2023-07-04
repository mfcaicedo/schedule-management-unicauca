package com.pragma.api.services;

import com.pragma.api.domain.*;
import com.pragma.api.model.Environment;
import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;


import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface IEnvironmentService {
    /**
     * Metodo que permite crear un nuevo registro en base de datos, sobre la tabla Environment, insertando la informacion del ambiente que se
     * recibe mediante el DTO.
     *
     * @param environmentDTO
     *            {@link EnvironmentDTO} Objeto con la información a insertar, recibido en el cuerpo de la petición al servicio Rest
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    public Response<EnvironmentDTO> createEnvironment(EnvironmentDTO environmentDTO);

    /**
     * Metodo que permite actualizar un registro en base de datos, sobre la tabla Environment,
     * actualizando la informacion del ambiente que se pasa por parametro.
     * @param environmentDTO objero con la informacion a actualizar del ambiente
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la actualizacion
     */
    public Response<EnvironmentDTO> updateEnvironment(EnvironmentDTO environmentDTO, Integer id);


    /**
     * Método que permite consultar la información de una materia mediante su codigo
     *
     * @param code
     *            Codigo de la materia a consultar
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de la materia consultada
     */
    Response<EnvironmentDTO> getEnvironmentByCode(Integer code);

    /**
     * Método que permite consultar la información de todas las materias existentes
     *
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de las materias consultadas
     */
    Response<GenericPageableResponse> findAll(final Pageable pageable);

    /**
     * Método que permite agregar recursos a un ambiente
     *
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de las materias consultadas
     */
    Response<Boolean> addResourceToEnvironment(ResourceList resourceList, Integer environmentId);

    /**
     * Método que permite editar los recursos a un ambiente
     *
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de las materias consultadas
     */
    Response<Boolean> updateResourceToEnvironment(ResourceList resourceList, Integer environmentId);

    public Response<GenericPageableResponse> findAllByResourceId(Pageable pageable, Integer resourceId);

    public Response<GenericPageableResponse> findAllByFacultyId(Pageable pageable, String facultyId);

    public Response<GenericPageableResponse> findAllByEnvironmentType(Pageable pageable, EnvironmentTypeEnumeration environmentType);

    public Response<List<EnvironmentDTO>> findAllByTypeAndParentId(EnvironmentTypeEnumeration environmentType, Integer parentId);

    Environment findById(final Integer id);

    List<EnvironmentTypeEnumeration> findAllTypesEnvironment();
    public void deleteById(Integer environmentId);

    Response<GenericPageableResponse> findAllByAvailabilityAndDayRecurrence(Date starting_date,LocalTime starting_time,
    LocalTime ending_time,Pageable pageable);

    Response<GenericPageableResponse> findAllByAvailabilityAndWeekRecurrence(Date starting_date,LocalTime starting_time,
    LocalTime ending_time,DaysEnumeration day,Integer weeks,Pageable pageable);

    Response<GenericPageableResponse> findAllByAvailabilityAndSemesterRecurrence(LocalTime starting_time,
    LocalTime ending_time,DaysEnumeration day,Pageable pageable);

    //Metodo para consultar todos los edificio, trayendolos por id de facultad
    public Response<List<EnvironmentDTO>> findAllBuildings(String facultyId);

    //Metodo para realizar el listado de ambientes y que pueden ser seleccionados por su tipo
    public Response<List<EnvironmentDTO>> findAllEnvironmentByIdFacultyAndBuilding(String facultyId);

}
