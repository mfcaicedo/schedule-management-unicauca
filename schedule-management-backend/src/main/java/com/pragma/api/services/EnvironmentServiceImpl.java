package com.pragma.api.services;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.*;
import com.pragma.api.model.*;
import com.pragma.api.repository.*;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private IFacultyRepository facultyRepository;
    @Autowired
    private IEnvironmentResourceRepository environmentResourceRepository;

    @Override
    public Response<EnvironmentDTO> createEnvironment(EnvironmentDTO environmentDTO) {
        logger.debug("Init createEnvironment Business Environment: {}", environmentDTO.toString());
        Response<EnvironmentDTO> response = new Response<>();
        System.out.println("Init createEnvironment Business Environment: " + environmentDTO.toString());
        // Busco el faculty
        Faculty faculty = null;
        faculty = this.facultyRepository.findByFacultyId(environmentDTO.getFacultyId());
        Environment environment = modelMapper.map(environmentDTO, Environment.class);
        environment.setFaculty(faculty);

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
        logger.debug("Miremos esto: {}", environmentDTO.toString());
        System.out.println("Miremos esto: " + environmentDTO.toString());
        // Busco el environment a actualizar
        Environment environment = null;
        environment = this.environmentRepository.findById(id).get();
        Environment environmentUpdate = modelMapper.map(environmentDTO, Environment.class);
        Faculty faculty = null;
        faculty = this.facultyRepository.findByFacultyId(environmentDTO.getFacultyId());

        if (environment != null) {

            // actualizo el environment
            environment.setName(environmentUpdate.getName());
            environment.setLocation(environmentUpdate.getLocation());
            environment.setCapacity(environmentUpdate.getCapacity());
            environment.setEnvironmentType(environmentUpdate.getEnvironmentType());
            environment.setFaculty(faculty);
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

        /*Environment enviromentUpdate = modelMapper.map(environmentDTO, Environment.class);
        EnvironmentDTO environmentDTO1 = null;

        environmentDTO1 = modelMapper.map(this.environmentRepository.save(enviromentUpdate), EnvironmentDTO.class);
*/
        return response;
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

        //environmentPage.getTotalElements();

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
        System.out.println("Miremos esto: " + resourceList.getResourceList());
        for (int resourceId : resourceList.getResourceList()) {
            if (!this.resourceRepository.existsById(resourceId))
                throw new ScheduleBadRequestException("bad.request.resource.id", Integer.toString(environmentId));

            Resource resourceToAdd = this.resourceRepository.findById(resourceId).get();
//            environment.getAvailableResources().add(resourceToAdd);
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
    public Response<Boolean> addResourceToEnvironmentForm(List<Integer> resourceList, Integer environmentId) {
        if (!this.environmentRepository.existsById(environmentId)){
            Response<Boolean> response = new Response<>();
            response.setStatus(400);
            response.setUserMessage("Environment not found");
            response.setDeveloperMessage("Environment not found");
            response.setMoreInfo("localhost:8080/api/subject");
            response.setErrorCode("Id del environment no encontrado");
            response.setData(false);
            return response;
        }
        Environment environment = this.environmentRepository.findById(environmentId).get();
        if(environment.getAvailableResources().size() > 0){
            for (EnvironmentResource environmentResource : environment.getAvailableResources()) {
                for (int i = 0; i < resourceList.size(); i++) {
                    if (resourceList.get(i) == environmentResource.getResource().getId()){
                        resourceList.remove(i);
                        break;
                    }
                }
            }
        }
        System.out.println("Miremos esto: " + resourceList.get(0));
        for (int resourceId : resourceList) {
            //busco el recurso
            Resource resourceToAdd = this.resourceRepository.findById(resourceId).get();
            //guardo los recursos en el ambiente
            EnvironmentResource environmentResource = new EnvironmentResource();
            environmentResource.setEnvironment(environment);
            environmentResource.setResource(resourceToAdd);
            environmentResource.setResourceQuantity(1);
            this.environmentResourceRepository.save(environmentResource);
        }

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
//            environment.getAvailableResources().add(resourceToAdd);
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


    ////Metodo para consultar todos los edificio, trayendolos por id de facultad
    @Override
    public Response<List<EnvironmentDTO>> findAllBuildings(String facultyId) {

        //Acordarse de cambiar el mensaje de la excepcion porque necesitamos uno de ambiente
        if(!this.environmentRepository.existsBy()) throw  new ScheduleBadRequestException("bad.request.event.event_name","");

        List<Environment> buildings = this.environmentRepository.findAllBuildings(facultyId);
        List<EnvironmentDTO> EnvironmentDTOlist = modelMapper.map(buildings,new TypeToken<List<EnvironmentDTO>>() {}.getType());
        Response<List<EnvironmentDTO>> response = new Response<>();
        if(EnvironmentDTOlist.size()>0){
        response.setStatus(200);
        response.setUserMessage("List of buildings Finded successfully");
        response.setDeveloperMessage("List of buildings Finded successfully");
        response.setMoreInfo("localhost:8081/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(EnvironmentDTOlist);
        }else{
            response.setStatus(500);
			response.setUserMessage("Data Not Found");
			response.setDeveloperMessage("Data Not Found");
			response.setMoreInfo("localhost:8081/api/environment(toDO)");
			response.setErrorCode(" No data found"); 
        }
        return response;
    }


    //Metodo  para listar los ambientes por id facultad, edificio, mostrando el tipo que deberia ser pasado
    @Override
    public Response<List<EnvironmentDTO>> findAllEnvironmentByIdFacultyAndBuilding(String facultyId) {
        //Acordarse de cambiar el mensaje de la excepcion porque necesitamos uno de ambiente
        //if(!this.environmentRepository.existsBy()) throw  new ScheduleBadRequestException("bad.request.event.event_name","");

        List<Object[]> environmentsForType = this.environmentRepository.findEnvironmentDataByFacultyId(facultyId);

        List<EnvironmentDTO> EnvironmentDTOList = new ArrayList<>();
        for (Object[] environment : environmentsForType) {
        Integer environmentId = (Integer) environment[0];
        String environmentName = (String) environment[1];
        String environmentTypeString = (String) environment[2];
        EnvironmentTypeEnumeration environmentType = EnvironmentTypeEnumeration.valueOf(environmentTypeString);
        EnvironmentDTO environmentDTO = new EnvironmentDTO(environmentId, environmentName, environmentType);
        EnvironmentDTOList.add(environmentDTO);
        }


        Response<List<EnvironmentDTO>> response = new Response<>();
        if(EnvironmentDTOList.size()>0){
        response.setStatus(200);
        response.setUserMessage("List of Environments Finded successfully");
        response.setDeveloperMessage("List of Environments Finded successfully");
        response.setMoreInfo("localhost:8081/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(EnvironmentDTOList);
        }else{
            response.setStatus(500);
			response.setUserMessage("Data Not Found");
			response.setDeveloperMessage("Data Not Found");
			response.setMoreInfo("localhost:8081/api/environment(toDO)");
			response.setErrorCode(" No data found"); 
        }
        return response;
    }

    //Busqueda para encontrar ambientes por tipo e id del padre
    @Override
    public Response<List<EnvironmentDTO>> findAllByTypeAndParentId(EnvironmentTypeEnumeration environmentType, Integer parentId) {
        String environmentTypeString = environmentType.toString();
        List<Environment> environmentPage = this.environmentRepository.findAllByTypeAndParentId(parentId, environmentTypeString);
        if(environmentPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.empty", "");

        Response<List<EnvironmentDTO>> response = new Response<>();

        List<EnvironmentDTO> EnvironmentDTOlist = modelMapper.map(environmentPage,new TypeToken<List<EnvironmentDTO>>() {}.getType());

        if(EnvironmentDTOlist.size()>0){
        response.setStatus(200);
        response.setUserMessage("List of buildings Finded successfully");
        response.setDeveloperMessage("List of buildings Finded successfully");
        response.setMoreInfo("localhost:8081/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(EnvironmentDTOlist);
        }else{
            response.setStatus(500);
			response.setUserMessage("Data Not Found");
			response.setDeveloperMessage("Data Not Found");
			response.setMoreInfo("localhost:8081/api/environment(toDO)");
			response.setErrorCode(" No data found"); 
        }
        return response;
    }
    @Override
    public Response<GenericPageableResponse> findAllByAvailabilityAndDayRecurrence(Date starting_date,
            LocalTime starting_time, LocalTime ending_time, Pageable pageable) {

        Response<GenericPageableResponse> response = new Response<>();
        response=this.ValidacionesReserva(starting_date,starting_time,ending_time);  
        if(response.getStatus()!=200){
            return response;
        }    
        Page<Environment> enviromentList = this.environmentRepository
                .findAllByStartingDateAndAvailabilityAnd1DayRecurrence(starting_date, starting_time, ending_time,
                        pageable);
        // List<EnvironmentDTO> enviromentDTOlist = modelMapper.map(enviromentList, new
        // org.modelmapper.TypeToken<List<EnvironmentDTO>>() {
        // }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Enviroments Finded successfully");
        response.setDeveloperMessage("List of Availability Enviroments Finded successfully");
        response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(this.validatePageList(enviromentList));
        return response;
    }

    // validar que el numero de semanas y el dia no lleguen en nullo FALTAAAAAA
    @Override
    public Response<GenericPageableResponse> findAllByAvailabilityAndWeekRecurrence(Date starting_date,
            LocalTime starting_time, LocalTime ending_time, DaysEnumeration day,
            Integer weeks, Pageable pageable) {

        Response<GenericPageableResponse> response = new Response<>();
        response=this.ValidacionesReserva(starting_date,starting_time,ending_time);  
        if(response.getStatus()!=200){
            return response;
        } 
        //validar que el numero de semanas que el usuario digite sea menor o igual al numero de semanas restantes del periodo
        Period actualPeriod = this.periodRepository.GetActivePeriod();
        int aux=this.cantidadSemanasRestantes(starting_date, actualPeriod.getEndDate());
        if(weeks > aux){
            response.setStatus(500);
            response.setUserMessage("Error Obteniendo la lista, la cantidad de semanas digitadas excede la fecha de fin de semestre");
            response.setDeveloperMessage("Error Obteniendo la lista, la cantidad de semanas digitadas excede la fecha de fin de semestre");
            response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
            response.setErrorCode("Error Obteniendo la lista, la cantidad de semanas digitadas excede la fecha de fin de semestre");
            return response;

        }

        LocalDate StartingDate = starting_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate NewDate = StartingDate.plusDays(7 * weeks);// multiplicar por la cantidad de la semanas
        Date EndingDate = Date.from(NewDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Page<Environment> enviromentList = this.environmentRepository
                .findAllByStartingDateAndAvailabilityAndWeekSemesterRecurrence(starting_date, EndingDate, starting_time,
                        ending_time, day.toString(), pageable);
        // List<EnvironmentDTO> enviromentDTOlist = modelMapper.map(enviromentList, new
        // org.modelmapper.TypeToken<List<EnvironmentDTO>>() {
        // }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Enviroments Finded successfully");
        response.setDeveloperMessage("List of Availability Enviroments Finded successfully");
        response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(this.validatePageList(enviromentList));
        return response;
    }

    @Override
     public Response<GenericPageableResponse> findAllByAvailabilityAndSemesterRecurrence(LocalTime starting_time,
            LocalTime ending_time, DaysEnumeration day, Pageable pageable) {

        Response<GenericPageableResponse> response = new Response<>();
        
        Period actualPeriod = this.periodRepository.GetActivePeriod();
        response=this.ValidacionesReserva(actualPeriod.getInitDate(),starting_time,ending_time);  
        if(response.getStatus()!=200){
            return response;
        } 
        Page<Environment> enviromentList = this.environmentRepository
                .findAllByStartingDateAndAvailabilityAndWeekSemesterRecurrence(actualPeriod.getInitDate(),
                        actualPeriod.getEndDate(),
                        starting_time, ending_time, day.toString(), pageable);
        // List<EnvironmentDTO> enviromentDTOlist = modelMapper.map(enviromentList, new
        // org.modelmapper.TypeToken<List<EnvironmentDTO>>() {
        // }.getType());
        response.setStatus(200);
        response.setUserMessage("List of Availability Enviroments Finded successfully");
        response.setDeveloperMessage("List of Availability Enviroments Finded successfully");
        response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
        response.setErrorCode("");
        response.setData(this.validatePageList(enviromentList));
        return response;

    }

    public Response<GenericPageableResponse> ValidacionesReserva(Date starting_date,LocalTime starting_time,
            LocalTime ending_time) {
        Response<GenericPageableResponse> response = new Response<>();
        // validar que la hora de inicio sea menor a la hora de fin
        if (starting_time.isAfter(ending_time) || starting_time.equals(ending_time)) {
            response.setStatus(500);
            response.setUserMessage("Error Obteniendo la lista, hora inicio es mayor o igual a hora final");
            response.setDeveloperMessage("Error Obteniendo la lista, hora inicio es mayor o igual a hora final");
            response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
            response.setErrorCode("Error Obteniendo la lista, hora inicio es mayor o igual a hora final");
            return response;
        }
        // validar que la fecha de inicio sea mayor o igual a la fecha actual
        //Calendar calendar = Calendar.getInstance();
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        // Restar un día
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        // Obtener la nueva fecha
        Date fechaNueva = calendar.getTime();
        if (starting_date.before(fechaNueva)) {
            response.setStatus(500);
            response.setUserMessage("Error Obteniendo la lista, fecha inicio es menor a la fecha actual");
            response.setDeveloperMessage("Error Obteniendo la lista, fecha inicio es menor a la fecha actual");
            response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
            response.setErrorCode("Error Obteniendo la lista, fecha inicio es menor a la fecha actual");
            return response;
        }
        // validar que la fecha de inicio sea menor o igual a la fecha de fin de periodo
        Period actualPeriod = this.periodRepository.GetActivePeriod();
        if (starting_date.after(actualPeriod.getEndDate())) {
            response.setStatus(500);
            response.setUserMessage("Error Obteniendo la lista, fecha inicio es mayor a la fecha de fin de periodo");
            response.setDeveloperMessage(
                    "Error Obteniendo la lista, fecha inicio es mayor a la fecha de fin de periodo");
            response.setMoreInfo("localhost:8080/api/enviroment(toDO)");
            response.setErrorCode("Error Obteniendo la lista, fecha inicio es mayor a la fecha de fin de periodo");
            return response;
        }
        response.setStatus(200);
        return response;

    }

    public int cantidadSemanasRestantes(Date starting_time,Date fechaFinSemestre){
        // Convertir a LocalDate
        LocalDate localDate1 = starting_time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = fechaFinSemestre.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calcular la diferencia en días
        long diferenciaEnDias = ChronoUnit.DAYS.between(localDate1, localDate2);

        // Calcular la diferencia en semanas
        long diferenciaEnSemanas = diferenciaEnDias / 7;

        return (int)diferenciaEnSemanas;

    }




}
