package com.pragma.api.services;

import com.pragma.api.domain.*;
import com.pragma.api.model.*;
import com.pragma.api.model.enums.PeriodStateEnumeration;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import com.pragma.api.repository.*;
import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import com.pragma.api.util.file.FileAcademicOffer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseFile uploadFile(MultipartFile file) throws IOException {
        FileAcademicOffer fileAcademicOffer = new FileAcademicOffer();
        ResponseFile responseFile = new ResponseFile();
        fileAcademicOffer.getPrueba(responseFile);
        System.out.println("oyeeeee " + responseFile.getLogsGeneric().get(0));
        List<FileRowAcademicOffer> logs = fileAcademicOffer.getLogs(file, responseFile);
        logs.forEach(log -> {
            System.out.println("-----------------------------------");
            System.out.println("nombre: " + log.getNameFile());
            System.out.println("codigo materia: " + log.getSubjectCode());
            System.out.println("codigo profesor: " + log.getPersonCode());
            System.out.println("perido: " + log.getPeriod());
            System.out.println("horas restantes: " + log.getWeeklyOverload());
            System.out.println("En bloque: " + log.getInBlock());
            System.out.println("Estado archivo: " + log.getStateFile());
            System.out.println("Grupo: " + log.getGroup());
            System.out.println("tipo ambiente requerido: " + log.getTypeEnvironmentRequired());
            System.out.println("id programa: " + log.getProgramId());
            System.out.println("capacidad: " + log.getCapacity());
            System.out.println("fecha registro: " + log.getDateRegisterFile());
            System.out.println("id de curso: " + log.getCourseId());
            System.out.println("-----------------------------------");


        });
        return processFile(logs);
//        return null;
    }

    private ResponseFile processFile(List<FileRowAcademicOffer> data) {
        boolean bandera = false;
        //TODO data procesada del archivo excel
        //capturar estado del registro y errores
        ResponseFile infoLogs = new ResponseFile();
        //ACADEMIC OFFER FILE
        AcademicOfferFile academicOfferFile = new AcademicOfferFile();
        //Sacar toda la información correspondiente a AcademicOfferFile
        academicOfferFile.setStateFile(data.get(0).getStateFile());
        if (!data.get(0).getNameFile().isEmpty()) academicOfferFile.setNameFile(data.get(0).getNameFile());
        academicOfferFile.setDateRegisterFile(data.get(0).getDateRegisterFile());
        if (!data.get(0).getPeriod().isEmpty()) {
            //Busco el periodo
            Optional<Period> period = iPeriodRepository.findById(data.get(0).getPeriod());
            if (period.isPresent()) {
                academicOfferFile.setPeriod(period.get()); //posible error
            } else {
                infoLogs.addLogsGeneric("El periodo académico no existe");
            }
        } else {
            infoLogs.addLogsEmptyFields("[Fila: 5 - Columna: B]  El código del periodo está vacío");
        }
        if (!data.get(0).getProgramId().isEmpty()) {
            Program program = modelMapper.map(iProgramService.findByProgramId(data.get(0).getProgramId()), Program.class);
            if (program != null) {
                academicOfferFile.setProgram(program);
            } else {
                infoLogs.addLogsGeneric("El programa no existe");
            }
        } else {
            infoLogs.addLogsEmptyFields("[Fila: 3 - Columna: B]  El código de la materia está vacío");
        }

        //eliminar el la primera posición de la lista
        data.remove(0);
        int auxIndice = 11;
        //recorremos para sacar la información de los cursos y para courseTeacher
        for (FileRowAcademicOffer value : data) {
            Course course = new Course();

            //COURSE
            if (value.getCapacity() != 0) {
                course.setCourseCapacity(value.getCapacity());
            } else {
                infoLogs.addLogsEmptyFields("[Fila: " + auxIndice + " - Columna: F]  La capacidad del curso está vacío");
            }

            if (!value.getGroup().isEmpty()) {
                course.setCourseGroup(value.getGroup());
            } else {
                infoLogs.addLogsEmptyFields("[Fila: " + auxIndice + " - Columna: E]  El grupo del curso está vacío");
            }

            if (value.getWeeklyOverload() != 0) {
                course.setRemainingHours(value.getWeeklyOverload());
            } else {
                infoLogs.addLogsEmptyFields("[Fila: " + auxIndice + " - Columna: D]  Las horas restantes del curso están vacías");
            }

            if (!value.getTypeEnvironmentRequired().isEmpty()) {
                course.setTypeEnvironmentRequired(value.getTypeEnvironmentRequired());
            } else {
                infoLogs.addLogsEmptyFields("[Fila: " + auxIndice + " - Columna: G]  El tipo de ambiente requerido del curso está vacío");
            }

            if (!value.getSubjectCode().isEmpty()) {
                System.out.println("codigo materia: " + value.getSubjectCode());
                Response<SubjectDTO> prueba = iSubjectService.getSubjectByCode(value.getSubjectCode());
                System.out.println("prueba: " + prueba.getData().getSubjectCode());
                Subject subject = modelMapper.map(iSubjectService.getSubjectByCode(value.getSubjectCode()).getData(), Subject.class);
                if (subject != null) {
                    course.setSubject(subject);

                } else {
                    infoLogs.addLogsGeneric("La materia no existe");
                }

            } else {
                infoLogs.addLogsEmptyFields("[Fila: " + auxIndice + " - Columna: B]  El código de la materia está vacío");
            }

            //COURSE TEACHER
            List<CourseTeacher> lstCourseTeacher = new ArrayList<>();
            int contAux = 1;
            if (value.getPersonCode().size() != 0) {
                for (String personCode : value.getPersonCode()) {

                    Person person = modelMapper.map(iPersonService.findByCode(personCode), Person.class);
                    if (person != null) {
                        CourseTeacher courseTeacher = new CourseTeacher();
                        courseTeacher.setPerson(person);
                        courseTeacher.setTeacherCategory(contAux == 1 ?
                                TeacherCategoryEnumeration.PRIMARY : contAux == 2 ?
                                TeacherCategoryEnumeration.SECONDARY : TeacherCategoryEnumeration.OTHER);

                        contAux++;
                        lstCourseTeacher.add(courseTeacher);
                    } else {
                        infoLogs.addLogsGeneric("El profesor no existe");
                    }
                }

            }
            //TODO guardar curso, cursoTeacher y academicOfferFile
            //ANTES DE GUARDAR VALIDAMOS QUE NO HAY ERRORES
            System.out.println("lista de errores genericos: " + infoLogs.getLogsGeneric().size());
            System.out.println("lista de errores vacios: " + infoLogs.getLogsEmptyFields().size());
            System.out.println("lista de errores tipo: " + infoLogs.getLogsType().size());
            if (infoLogs.getLogsEmptyFields().size() == 0 && infoLogs.getLogsGeneric().size() == 0 &&
                    infoLogs.getLogsType().size() == 0) {

                Response<CourseDTO> courseResponse = iCourseService.createCourse(modelMapper.map(course, CourseDTO.class));
                for (CourseTeacher courseTeacher : lstCourseTeacher) {
                    System.out.println("entro a guardar COURSE TEACHER");
                    courseTeacher.setCourse(modelMapper.map(courseResponse.getData(), Course.class));
                    iCourseTeacherService.save(modelMapper.map(courseTeacher, CourseTeacherDTO.class));
                }
                auxIndice++;
                bandera=true;
            }else {
                System.out.println("entro al else de guardar informacion");
            }

        }

        if(bandera){
            System.out.println("entro a guardar FILE ACADEMIC OFFER");
            iAcademicOfferFileRepository.save(academicOfferFile);
        }

        return infoLogs;

    }

}
