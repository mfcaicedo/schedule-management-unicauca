package com.pragma.api.services;

import com.pragma.api.domain.*;
import com.pragma.api.model.*;
import com.pragma.api.model.enums.PeriodStateEnumeration;
import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import com.pragma.api.model.enums.StatusFileEnumeration;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import com.pragma.api.repository.*;
import com.pragma.api.util.PageableUtils;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import com.pragma.api.util.file.FileAcademicOffer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.ModuleElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileAcademicOfferImpl implements IFileAcademicOffer {
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private IPersonService iPersonService;
    @Autowired
    private ISubjectService iSubjectService;
    @Autowired
    private ICourseTeacherService iCourseTeacherService;
    @Autowired
    private IPeriodRepository iPeriodRepository;
    @Autowired
    private IProgramService iProgramService;

    @Autowired
    private IAcademicOfferFileRepository iAcademicOfferFileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response<GenericPageableResponse> findAll(Pageable pageable) {

        Page<AcademicOfferFile> academicOfferFilePage = this.iAcademicOfferFileRepository.findAll(pageable);
        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("File Academy Offer found");
        response.setDeveloperMessage("File Academic Offer found");
        response.setMoreInfo("localhost:8080/api/academicOffer");
        response.setErrorCode("");
        response.setData(this.validatePageList(academicOfferFilePage));
        return response;
    }

    @Override
    public Response<AcademicOfferFileDTO> updateStateFile(Integer id, String stateFile) {
        AcademicOfferFile academicOfferFile = this.iAcademicOfferFileRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        AcademicOfferFileDTO academicOfferFileDTO = new AcademicOfferFileDTO();
        Response response = new Response();
        //modifico el estado del archivo
        StateAcOfferFileEnumeration stateAcOfferFileEnumeration = null;
        if(stateFile.equals("Sin iniciar")){
            stateAcOfferFileEnumeration = StateAcOfferFileEnumeration.SIN_INICIAR;
        }else if(stateFile.equals("En proceso")) {
            stateAcOfferFileEnumeration = StateAcOfferFileEnumeration.EN_PROCESO;
        }else if(stateFile.equals("Finalizado")) {
            stateAcOfferFileEnumeration = StateAcOfferFileEnumeration.FINALIZADO;
        }else {
            //no actualizo el estado del archivo
            academicOfferFileDTO = modelMapper.map(academicOfferFile, AcademicOfferFileDTO.class);
            response.setData(academicOfferFileDTO);
            response.setStatus(200);
            return response;
        }
        //actualizo el estado del archivo
        academicOfferFile.setStateFile(stateAcOfferFileEnumeration);
        //guardo el archivo en la base de datos
        iAcademicOfferFileRepository.save(academicOfferFile);
        response.setData(academicOfferFile);
        response.setStatus(200);
        return response;
    }

    @Override
    public Response<List<AcademicOfferFileDTO>> findAllByStatefile(String stateFile) {
        StateAcOfferFileEnumeration stateAcOfferFileEnumeration = null;
        Response<List<AcademicOfferFileDTO>> response = new Response();

        if(stateFile.equals("Sin iniciar")){
            stateAcOfferFileEnumeration = StateAcOfferFileEnumeration.SIN_INICIAR;
        }else if(stateFile.equals("En proceso")) {
            stateAcOfferFileEnumeration = StateAcOfferFileEnumeration.EN_PROCESO;
        }else if(stateFile.equals("Finalizado")) {
            stateAcOfferFileEnumeration = StateAcOfferFileEnumeration.FINALIZADO;
        }else {
            //no hago la consulta y devuelvo un error
            response.setStatus(400);
            response.setErrorCode("400");
            response.setDeveloperMessage("Estado de archivo no valido");
            response.setUserMessage("Estado de archivo no valido");
            response.setMoreInfo("localhost:8080/api/academicOffer");
            response.setData(null);
            return response;
        }
        List<AcademicOfferFileDTO> academicOfferFileDTOS = new ArrayList<>();
        List<AcademicOfferFile> academicOfferFiles = iAcademicOfferFileRepository.findAllByStateFile(stateAcOfferFileEnumeration);
        academicOfferFileDTOS = academicOfferFiles.stream().map(x -> modelMapper.map(x, AcademicOfferFileDTO.class)).collect(Collectors.toList());
        response.setStatus(200);
        response.setErrorCode("");
        response.setDeveloperMessage("Exito");
        response.setUserMessage("Exito");
        response.setMoreInfo("localhost:8080/api/academicOffer");
        response.setData(academicOfferFileDTOS);
        return response;
    }

    private GenericPageableResponse validatePageList(Page<AcademicOfferFile> academicOfferFilePage) {
        List<AcademicOfferFileDTO> academicOfferFilesDTOS = academicOfferFilePage.stream()
                .map(x -> modelMapper.map(x, AcademicOfferFileDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(academicOfferFilePage, academicOfferFilesDTOS);
    }

    @Override
    public ResponseFile uploadFile(MultipartFile file) throws IOException {
        FileAcademicOffer fileAcademicOffer = new FileAcademicOffer();
        ResponseFile responseFile = new ResponseFile();
        List<FileRowAcademicOffer> logs = fileAcademicOffer.getLogs(file, responseFile);

        if (logs.isEmpty()) {
            return responseFile;
        }else {
            return processFile(logs, responseFile);
        }
    }

    private ResponseFile processFile(List<FileRowAcademicOffer> data, ResponseFile responseFile) {
        boolean bandera = false;
        // TODO data procesada del archivo excel
        // ACADEMIC OFFER FILE
        AcademicOfferFile academicOfferFile = new AcademicOfferFile();
        // Sacar toda la información correspondiente a AcademicOfferFile
        academicOfferFile.setStateFile(data.get(0).getStateFile());
        if (!data.get(0).getNameFile().isEmpty())
            academicOfferFile.setNameFile(data.get(0).getNameFile());
        academicOfferFile.setDateRegisterFile(data.get(0).getDateRegisterFile());
        // Periodo correspondiente del archivo
        if (data.get(0).getPeriod() != null && !data.get(0).getPeriod().isEmpty()) {
            // Busco el periodo
            Optional<Period> period = iPeriodRepository.findById(data.get(0).getPeriod());
            if (period.isPresent()) {
                academicOfferFile.setPeriod(period.get());
            } else {
                responseFile.addLogsGeneric("El periodo académico no existe, verifique si lo escribió " +
                        "correctamente: Ej: 2023-1");
            }
        }
        // Programa correspondiente del archivo
        if (data.get(0).getProgramId() != null && !data.get(0).getProgramId().isEmpty()) {
            ProgramDTO programDTO = iProgramService.findByProgramId(data.get(0).getProgramId());
            if (programDTO != null) {
                Program program = modelMapper.map(iProgramService.findByProgramId(data.get(0).getProgramId()),
                        Program.class);
                academicOfferFile.setProgram(program);
            } else {
                responseFile.addLogsGeneric("El programa no existe");
            }
        }

        //Validar si hay un archivo para un mismo programa, en el mismo periodo y que su estado no sea finalizado
        AcademicOfferFile academicOfferFileAux = iAcademicOfferFileRepository.findByProgram_ProgramIdAndPeriod_PeriodIdAndStateFileNot(
                academicOfferFile.getProgram().getProgramId(), academicOfferFile.getPeriod().getPeriodId(),
                StateAcOfferFileEnumeration.FINALIZADO);

        if(academicOfferFileAux != null){
            responseFile.addLogsGeneric("Lo sentimos, no podemos procesar el archivo porque " +
                    "ya existe un archivo perteneciente al programa " +
                    academicOfferFile.getProgram().getProgramId() +
                    " del periodo " + academicOfferFile.getPeriod().getPeriodId() +
                    " y su estado no es Finalizado");
            responseFile.setStatusFile(StatusFileEnumeration.ERROR);
            return responseFile;
        }else{
            System.out.println("llega la que sabemos");
            //TODO Guardamos los cursos del archivo nuevo
            //1. Buscamos un archivo en finalizado para el archivo a subir
//            List<AcademicOfferFile> academicOfferFileSearch = iAcademicOfferFileRepository.findByProgram_ProgramIdAndPeriod_PeriodIdAndStateFile(
            List<AcademicOfferFile> academicOfferFileSearchList = iAcademicOfferFileRepository.findAllByProgram_ProgramIdAndPeriod_PeriodIdAndStateFileOrderByDateRegisterFileDesc(
                    academicOfferFile.getProgram().getProgramId(), academicOfferFile.getPeriod().getPeriodId(),
                    StateAcOfferFileEnumeration.FINALIZADO);
            AcademicOfferFile academicOfferFileSearch = null;
            if(academicOfferFileSearchList.size() > 0){
                academicOfferFileSearch = academicOfferFileSearchList.get(0);
            }
            academicOfferFileSearchList.forEach(x-> System.out.println("Archivo: " + x.getNameFile()));

            System.out.println("holiii " + academicOfferFileSearch.getProgram().getName());
            if(academicOfferFileSearch != null){
                System.out.println("estamos melos");
                //2. Validamos que no hayan cursos repetidos pertenecientes al mismo programa del archivo a subir
                //2.1 Recupero los cursos pertenecientes al programa del archivo a subir
                List<Course> courses = iCourseService.finAllByProgram_ProgramaId(academicOfferFileSearch.getProgram().getProgramId());
                courses.forEach(x-> System.out.println("Curso: " + x.getId() + x.getSubject().getProgram().getName()));
                //2.2 Comparar los cursos del archivo a subir con los cursos del archivo finalizado encontrado que
                //pertenezcan al mismo programa
                boolean flag = false;
                for (Course course: courses){
                    for (FileRowAcademicOffer value : data) {
                        if(course.getSubject().getSubjectCode().equals(value.getSubjectCode()) &&
                        course.getCourseGroup().equals(value.getGroup())
                        ){
                            responseFile.addLogsGeneric("Ya existe un curso: "+ value.getSubjectCode()+" con el mismo grupo: " +
                                    value.getGroup() + ". Verifique el archivo");
                            responseFile.setStatusFile(StatusFileEnumeration.ERROR);
                            flag = true;
                        }
                    }
                }
                //Si hay un curso repetido o más, no se puede procesar el archivo
                if(flag) return responseFile;

            }
        }

        // eliminar el la primera posición de la lista
        data.remove(0);
        int auxIndice = 11;
        // recorremos para sacar la información de los cursos y para courseTeacher
        for (FileRowAcademicOffer value : data) {
            Course course = new Course();

            // COURSE
            if (value.getCapacity() != null && value.getCapacity() != 0) {
                course.setCourseCapacity(value.getCapacity());
            }

            if (value.getGroup() != null && !value.getGroup().isEmpty()) {
                course.setCourseGroup(value.getGroup());
            }

            if (value.getWeeklyOverload() != null && value.getWeeklyOverload() != 0) {
                course.setRemainingHours(value.getWeeklyOverload());
            }

            if (value.getTypeEnvironmentRequired() != null && !value.getTypeEnvironmentRequired().isEmpty()) {
                course.setTypeEnvironmentRequired(value.getTypeEnvironmentRequired());
            }

            if (value.getSubjectCode() != null && !value.getSubjectCode().isEmpty()) {
                System.out.println("codigo materia: " + value.getSubjectCode());
                Response<SubjectDTO> subjectDTO = iSubjectService.getSubjectByCode(value.getSubjectCode());
                System.out.println("subjectDTO: " + subjectDTO.getData());
                if (subjectDTO.getData() != null) {
                    Subject subjectFinal = modelMapper.map(subjectDTO.getData(), Subject.class);
                    course.setSubject(subjectFinal);
                } else {
                    responseFile.addLogsGeneric("La materia no existe");
                }

            } else {
                responseFile.addLogsEmptyFields(
                        "[Fila: " + auxIndice + " - Columna: B]  El código de la materia está vacío");
            }

            // COURSE TEACHER
            List<CourseTeacher> lstCourseTeacher = new ArrayList<>();
            int contAux = 1;
            if (value.getPersonCode().size() != 0) {
                System.out.println("LLEGA AL FOR DE PERSONAS " + value.getPersonCode().size());
                for (String personCode : value.getPersonCode()) {
                    System.out.println("codigo persona: " + personCode);

                    Person person = modelMapper.map(iPersonService.findByCode(personCode), Person.class);
                    if (person != null) {
                        CourseTeacher courseTeacher = new CourseTeacher();
                        courseTeacher.setPerson(person);
                        courseTeacher.setTeacherCategory(
                                contAux == 1 ?
                                TeacherCategoryEnumeration.PRIMARY : contAux == 2 ?
                                TeacherCategoryEnumeration.SECONDARY : TeacherCategoryEnumeration.OTHER
                        );
                        contAux++;
                        lstCourseTeacher.add(courseTeacher);
                    } else {
                        responseFile.addLogsGeneric("El profesor con código: " + personCode + " no existe");
                    }
                }

            }
            // TODO guardar curso, cursoTeacher y academicOfferFile
            // ANTES DE GUARDAR VALIDAMOS QUE NO HAYAN ERRORES
            System.out.println("lista de errores genericos: " + responseFile.getLogsGeneric().size());
            System.out.println("lista de errores vacios: " + responseFile.getLogsEmptyFields().size());
            System.out.println("lista de errores tipo: " + responseFile.getLogsType().size());
            if (responseFile.getLogsEmptyFields().size() == 0 && responseFile.getLogsGeneric().size() == 0 &&
                    responseFile.getLogsType().size() == 0) {

                Response<CourseDTO> courseResponse = iCourseService
                        .createCourse(modelMapper.map(course, CourseDTO.class));
                for (CourseTeacher courseTeacher : lstCourseTeacher) {
                    System.out.println("entro a guardar COURSE TEACHER");
                    courseTeacher.setCourse(modelMapper.map(courseResponse.getData(), Course.class));
                    iCourseTeacherService.save(modelMapper.map(courseTeacher, CourseTeacherDTO.class));
                }
                auxIndice++;
                bandera = true;
            } else {
                System.out.println("entro al else de guardar informacion");
                responseFile.setStatusFile(StatusFileEnumeration.ERROR);
            }

        }

        if (bandera)
            iAcademicOfferFileRepository.save(academicOfferFile);

        return responseFile;

    }

}