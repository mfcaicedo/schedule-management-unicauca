package com.pragma.api.services;

import com.pragma.api.domain.*;
import com.pragma.api.domain.ScheduleRequestDTO;
import com.pragma.api.domain.ScheduleResponseDTO;

import java.util.List;

public interface IScheduleService {
    /**
     * Metodo para guardar una franja horaria
     * @param save objeto con la informacion de la franja horaria
     * @return objeto con la informacion de la franja horaria guardada
     */
    ScheduleResponseDTO saveSchedule(ScheduleRequestDTO save);
    ScheduleResponseDTO updateSchedule(Long code, ScheduleRequestDTO update);
    Boolean deleteSchedule(Long code);
    List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId);
//    List<ScheduleResponseDTO> getAllByTeacher(String teacherCode);
    ScheduleResponseDTO getScheduleById(Long id);

// Metodo para regresar la lista de calendarios que tengan en comun un ambiente(salon, lab, auditorio)
public Response<List<ScheduleResponseDTO>> findAllByEnvironmentId(Integer EnvironmentId);
}
