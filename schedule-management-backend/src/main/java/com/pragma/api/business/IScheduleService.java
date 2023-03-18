package com.pragma.api.business;

import com.pragma.api.domain.ScheduleRequestDTO;
import com.pragma.api.domain.ScheduleResponseDTO;

import java.util.List;

public interface IScheduleService {

    ScheduleResponseDTO saveSchedule(ScheduleRequestDTO save);
    ScheduleResponseDTO updateSchedule(Long code, ScheduleRequestDTO update);
    Boolean deleteSchedule(Long code);
    List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId);
    List<ScheduleResponseDTO> getAllByTeacher(String teacherCode);
    ScheduleResponseDTO getScheduleById(Long id);
}
