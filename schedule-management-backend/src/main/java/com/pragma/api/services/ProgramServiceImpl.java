package com.pragma.api.services;

import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Program;
import com.pragma.api.repository.IProgramRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
