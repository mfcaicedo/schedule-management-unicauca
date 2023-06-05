package com.pragma.api.services;

import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Program;
import com.pragma.api.repository.IProgramRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements IProgramService {

    private final ModelMapper modelMapper;

    private final IProgramRepository iProgramRepository;

    @Autowired
    public ProgramServiceImpl(ModelMapper modelMapper, IProgramRepository iProgramRepository) {
        this.modelMapper = modelMapper;
        this.iProgramRepository = iProgramRepository;
    }

    @Override
    public List<ProgramDTO> findAllProgram() {
        List<Program> programs = this.iProgramRepository.findAll();

        if(programs.isEmpty()) throw new ScheduleBadRequestException("bad.request.program.empty", "");

        return programs.stream().map(program ->  modelMapper.map(program, ProgramDTO.class)).collect(Collectors.toList());

    }

    @Override
    public ProgramDTO findByProgramId(String id){
        Optional<Program> program = iProgramRepository.findById(id);
        if(program.isEmpty()) throw new ScheduleBadRequestException("bad.request.program.empty", "");
        return  modelMapper.map(program.get(), ProgramDTO.class);
    }


    //Metodo para traer los programas por un id facultad en especifico
    @Override
    public Response<List<ProgramDTO>> findAllProgramByFacultyId(String facultyId) {

        //Acordarse de cambiar el mensaje de la excepcion porque necesitamos uno de programa
        if(!this.iProgramRepository.existsBy()) throw  new ScheduleBadRequestException("bad.request.event.event_name","");

        List<Object[]> programsForIdFaculty = this.iProgramRepository.findProgramDataByFacultyId(facultyId);

        List<ProgramDTO> programDTOList = new ArrayList<>();
        for (Object[] program : programsForIdFaculty) {
        String programId = (String) program[0];
        String programName = (String) program[1];

        ProgramDTO programDTO = new ProgramDTO(programId, programName);
        programDTOList.add(programDTO);
        }


        Response<List<ProgramDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of programs Finded successfully");
        response.setDeveloperMessage("List of program Finded successfully");
        response.setMoreInfo("localhost:8081/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(programDTOList);
        return response;
    }

    @Override
    public Response<List<ProgramDTO>> findAllByDepartmentId(Integer department_id){

        Response<List<ProgramDTO>> response = new Response<>();
        List<Program> programList = this.iProgramRepository.findByDepartmentId(department_id);
        List<ProgramDTO> programDTOlist = modelMapper.map(programList, new org.modelmapper.TypeToken<List<ProgramDTO>>() {
        }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Programs Finded successfully");
        response.setDeveloperMessage("List of Availability Programs Finded successfully");
        response.setMoreInfo("localhost:8080/api/program(toDO)");
        response.setErrorCode("");
        response.setData(programDTOlist);
        return response;
    }

}
