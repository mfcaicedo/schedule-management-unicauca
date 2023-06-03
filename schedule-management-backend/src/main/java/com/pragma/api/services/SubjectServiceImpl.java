package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.SubjectDTO;
import com.pragma.api.model.Person;
import com.pragma.api.model.Program;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import com.pragma.api.model.Program;
import com.pragma.api.model.Resource;
import com.pragma.api.model.enums.ResourceTypeEnumeration;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Subject;
import com.pragma.api.repository.IProgramRepository;
import com.pragma.api.repository.ISubjectRepository;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements ISubjectService {

    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private IProgramRepository programRepository;

    @Override
    @Transactional
    public Response<SubjectDTO> createSubject(SubjectDTO subjectDTO) {
        logger.debug("Init createSubject Business Subject: {}", subjectDTO.toString());
        Response<SubjectDTO> response = new Response<>();

        if(!programRepository.existsById(subjectDTO.getProgramId())){
            throw new ScheduleBadRequestException("bad.request.program.id", subjectDTO.getProgramId());
        }

        Subject subject = modelMapper.map(subjectDTO,Subject.class);

        if(subjectRepository.findById(subject.getSubjectCode()).isPresent()){
            throw new ScheduleBadRequestException("bad.request.resource.id", subject.getSubjectCode());
        }


        SubjectDTO subjectDTO1 = modelMapper.map(subjectRepository.save(subject),SubjectDTO.class);

        response.setStatus(200);
        response.setUserMessage("Subject created");
        response.setDeveloperMessage("Subject created");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(subjectDTO1);
        logger.debug("Finish createSubject Business");

        return response;
    }

    @Override
    public Response<SubjectDTO> getSubjectByCode(String code) {
        return null;
    }

    @Override
    public GenericPageableResponse findAllSubject(Pageable pageable) {
        Page<Subject> resourcesPage = this.subjectRepository.findAll(pageable);
        if(resourcesPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.subject.empty", "");
        return this.validatePageList(resourcesPage);

    }

    @Override
    public GenericPageableResponse findAllByProgramId(String program_id, Pageable pageable) {

        Page<Subject> subjectsByProgramIdPage = this.subjectRepository.findAllByProgramId(program_id,pageable);
        if(subjectsByProgramIdPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.subject.empty", "");
        return this.validatePageList(subjectsByProgramIdPage);
    }


    @Override
    public Response<GenericPageableResponse> findAll(final Pageable pageable) {
        Page<Subject> subjectPage = this.subjectRepository.findAll(pageable);
        if(subjectPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.subject.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Subjects found");
        response.setDeveloperMessage("Subjects found");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(this.validatePageList(subjectPage));
        logger.debug("Finish findAllSubjects Business");
        return response;
    }

    @Override
    public List<SubjectDTO> findAllByProgram(Program program) {
        List<Subject> subjects = this.subjectRepository.findAllByProgramOrderBySemester(program);
        List<SubjectDTO> subjectsDTO = new ArrayList<>();
        subjectsDTO = subjects.stream().map(x->modelMapper.map(x, SubjectDTO.class)).collect(Collectors.toList());
        return subjectsDTO;
    }

    private GenericPageableResponse validatePageList(Page<Subject> subjectsPage){
        List<SubjectDTO> subjectDTOS = subjectsPage.stream().map(x->modelMapper.map(x, SubjectDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(subjectsPage, subjectDTOS);
    }
}
