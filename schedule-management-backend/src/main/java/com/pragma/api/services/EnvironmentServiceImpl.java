package com.pragma.api.services;

import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ResourceList;
import com.pragma.api.domain.Response;
import com.pragma.api.model.EnvironmentResource;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Period;
import com.pragma.api.model.Resource;
import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.model.enums.RecurrenceEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IPeriodRepository;
import com.pragma.api.repository.IResourceRepository;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDate;

@Service
public class EnvironmentServiceImpl implements IEnvironmentService {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IEnvironmentRepository environmentRepository;
    @Autowired
    private IResourceRepository resourceRepository;
    @Autowired
    private IPeriodRepository periodRepository;

    @Override
    public Response<EnvironmentDTO> createEnvironment(EnvironmentDTO environmentDTO) {
        logger.debug("Init createEnvironment Business Environment: {}", environmentDTO.toString());
        Response<EnvironmentDTO> response = new Response<>();

        Environment environment = modelMapper.map(environmentDTO, Environment.class);

        EnvironmentDTO environmentDTO1 = modelMapper.map(this.environmentRepository.save(environment),
                EnvironmentDTO.class);

        response.setStatus(200);
        response.setUserMessage("Environment created");
        response.setDeveloperMessage("Environment created");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(environmentDTO1);
        logger.debug("Finish create Environment Business");

        return response;
    }

    @Override
    public Response<EnvironmentDTO> updateEnvironment(EnvironmentDTO environmentDTO, Integer id) {
        Response<EnvironmentDTO> response = new Response<>();
        logger.debug("Init updateEnvironment Business Environment: {}", environmentDTO.toString());

        // Busco el environment a actualizar
        Environment environment = null;
        environment = this.environmentRepository.findById(id).get();
        Environment environmentUpdate = modelMapper.map(environmentDTO, Environment.class);

        if (environment != null) {

            // actualizo el environment
            environment.setName(environmentUpdate.getName());
            environment.setLocation(environmentUpdate.getLocation());
            environment.setCapacity(environmentUpdate.getCapacity());
            environment.setEnvironmentType(environmentUpdate.getEnvironmentType());
            environment.setFaculty(environmentUpdate.getFaculty());
            environment.setAvailableResources(environmentUpdate.getAvailableResources());
            environment.setSchedules(environmentUpdate.getSchedules());
            this.environmentRepository.save(environment);
            EnvironmentDTO environmentDTO1 = modelMapper.map(environment, EnvironmentDTO.class);
            response.setStatus(200);
            response.setUserMessage("Environment actualizado");
            response.setDeveloperMessage("Environment actualizado");
            response.setMoreInfo("localhost:8080/api/subject");
            response.setErrorCode("");
            response.setData(environmentDTO1);
            logger.debug("Finish update Environment Business");

        } else {

            response.setStatus(400);
            response.setUserMessage("Environment no actualizado");
            response.setDeveloperMessage("Environment actualizado");
            response.setMoreInfo("localhost:8080/api/subject");
            response.setErrorCode("Id del environment no encontrado");
            response.setData(null);
            logger.debug("Finish update Environment Business");

        }

        Environment enviromentUpdate = modelMapper.map(environmentDTO, Environment.class);
        EnvironmentDTO environmentDTO1 = null;

        environmentDTO1 = modelMapper.map(this.environmentRepository.save(enviromentUpdate), EnvironmentDTO.class);

        return null;
    }

    @Override
    public Response<EnvironmentDTO> getEnvironmentByCode(Integer code) {

        if (!this.environmentRepository.existsById(code))
            throw new ScheduleBadRequestException("bad.request.environment.id", Integer.toString(code));

        Environment environment = this.environmentRepository.findById(code).get();

        EnvironmentDTO environmentDTO1 = modelMapper.map(environment, EnvironmentDTO.class);

        Response<EnvironmentDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Resource added successfully");
        response.setDeveloperMessage("Resource added successfully");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(environmentDTO1);

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAll(Pageable pageable) {
        Page<Environment> environmentPage = this.environmentRepository.findAll(pageable);
        if (environmentPage.isEmpty())
            throw new ScheduleBadRequestException("bad.request.subject.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    @Override
    public Environment findById(Integer id) {
        Environment environment = this.environmentRepository.findById(id)
                .orElseThrow(() -> new ScheduleBadRequestException("bad.request.environment.id", id.toString()));
        return environment;
    }

    @Override
    public List<EnvironmentTypeEnumeration> findAllTypesEnvironment() {

        List<EnvironmentTypeEnumeration> enums = new ArrayList<EnvironmentTypeEnumeration>();

        enums.add(EnvironmentTypeEnumeration.AUDITORIO);
        enums.add(EnvironmentTypeEnumeration.LABORATORIO);
        enums.add(EnvironmentTypeEnumeration.SALON);
        enums.add(EnvironmentTypeEnumeration.EDIFICIO);

        return enums;
    }

    @Override
    public void deleteById(Integer environmentId) {
        this.environmentRepository.deleteById(environmentId);
    }

    @Override
    public Response<Boolean> addResourceToEnvironment(ResourceList resourceList, Integer environmentId) {
        if (!this.environmentRepository.existsById(environmentId))
            throw new ScheduleBadRequestException("bad.request.environment.id", Integer.toString(environmentId));
        Environment environment = this.environmentRepository.findById(environmentId).get();
        for (int resourceId : resourceList.getResourceList()) {
            if (!this.resourceRepository.existsById(resourceId))
                throw new ScheduleBadRequestException("bad.request.resource.id", Integer.toString(environmentId));

            Resource resourceToAdd = this.resourceRepository.findById(resourceId).get();
            environment.getAvailableResources().add(resourceToAdd);
        }


        this.environmentRepository.save(environment);
        Response<Boolean> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Resource added successfully");
        response.setDeveloperMessage("Resource added successfully");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(true);

        return response;
    }

    @Override
    public Response<Boolean> updateResourceToEnvironment(ResourceList resourceList, Integer environmentId) {
        if (!this.environmentRepository.existsById(environmentId))
            throw new ScheduleBadRequestException("bad.request.environment.id", Integer.toString(environmentId));
        Environment environment = this.environmentRepository.findById(environmentId).get();
        environment.getAvailableResources().clear();
        for (int resourceId : resourceList.getResourceList()) {
            if (!this.resourceRepository.existsById(resourceId))
                throw new ScheduleBadRequestException("bad.request.resource.id", Integer.toString(environmentId));

            Resource resourceToAdd = this.resourceRepository.findById(resourceId).get();
            environment.getAvailableResources().add(resourceToAdd);
        }

        this.environmentRepository.save(environment);

        Response<Boolean> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Resource added successfully");
        response.setDeveloperMessage("Resource added successfully");
        response.setMoreInfo("localhost:8080/api/subject");
        response.setErrorCode("");
        response.setData(true);

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByResourceId(Pageable pageable, Integer resourceId) {
        Page<Environment> environmentPage = this.environmentRepository.findAllByAvailableResourcesId(resourceId,
                pageable);
        if (environmentPage.isEmpty())
            throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByFacultyId(Pageable pageable, String facultyId) {

        Page<Environment> environmentPage = this.environmentRepository.findAllByFacultyFacultyId(facultyId, pageable);
        if (environmentPage.isEmpty())
            throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByEnvironmentType(Pageable pageable,
            EnvironmentTypeEnumeration environmentType) {
        Page<Environment> environmentPage = this.environmentRepository.findAllByEnvironmentType(environmentType,
                pageable);
        if (environmentPage.isEmpty())
            throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Environments found");
        response.setDeveloperMessage("Environments found");
        response.setMoreInfo("localhost:8080/api/environment");
        response.setErrorCode("");
        response.setData(this.validatePageList(environmentPage));

        return response;
    }

    private GenericPageableResponse validatePageList(Page<Environment> environmentsPage) {
        List<EnvironmentDTO> environmentDTOS = environmentsPage.stream()
                .map(x -> modelMapper.map(x, EnvironmentDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(environmentsPage, environmentDTOS);
    }

    @Override
    public Response<List<EnvironmentDTO>> findAllByAvailabilityAndDayRecurrence(Date starting_date,
            LocalTime starting_time, LocalTime ending_time) {

        Response<List<EnvironmentDTO>> response = new Response<>();
        List<Environment> enviromentList = this.environmentRepository
                .findAllByStartingDateAndAvailabilityAnd1DayRecurrence(starting_date, starting_time, ending_time);
        List<EnvironmentDTO> enviromentDTOlist = modelMapper.map(enviromentList, new TypeToken<List<EnvironmentDTO>>() {
        }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Enviroments Finded successfully");
        response.setDeveloperMessage("List of Availability Enviroments Finded successfully");
        response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(enviromentDTOlist);
        return response;
    }

    // validar que el numero de semanas y el dia no lleguen en nullo FALTAAAAAA
    @Override
    public Response<List<EnvironmentDTO>> findAllByAvailabilityAndWeekRecurrence(Date starting_date,
            LocalTime starting_time, LocalTime ending_time,DaysEnumeration day,
            Integer weeks) {

        Response<List<EnvironmentDTO>> response = new Response<>();

        LocalDate StartingDate = starting_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate NewDate = StartingDate.plusDays(7 * weeks);// multiplicar por la cantidad de la semanas
        Date EndingDate = Date.from(NewDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Environment> enviromentList = this.environmentRepository
                .findAllByStartingDateAndAvailabilityAndWeekSemesterRecurrence(starting_date, EndingDate, starting_time,
                        ending_time, day.toString());
        List<EnvironmentDTO> enviromentDTOlist = modelMapper.map(enviromentList, new TypeToken<List<EnvironmentDTO>>() {
        }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Enviroments Finded successfully");
        response.setDeveloperMessage("List of Availability Enviroments Finded successfully");
        response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(enviromentDTOlist);
        return response;
    }

    @Override
    public Response<List<EnvironmentDTO>> findAllByAvailabilityAndSemesterRecurrence(LocalTime starting_time,
            LocalTime ending_time, DaysEnumeration day) {

        Response<List<EnvironmentDTO>> response = new Response<>();
        Period actualPeriod = this.periodRepository.GetActivePeriod();
        List<Environment> enviromentList = this.environmentRepository
                .findAllByStartingDateAndAvailabilityAndWeekSemesterRecurrence(actualPeriod.getInitDate(),actualPeriod.getEndDate(),
                starting_time,ending_time,day.toString());
        List<EnvironmentDTO> enviromentDTOlist = modelMapper.map(enviromentList, new TypeToken<List<EnvironmentDTO>>() {
        }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Enviroments Finded successfully");
        response.setDeveloperMessage("List of Availability Enviroments Finded successfully");
        response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(enviromentDTOlist);
        return response;

    }

}
