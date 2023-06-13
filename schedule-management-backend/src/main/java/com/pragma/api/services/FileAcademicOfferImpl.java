package com.pragma.api.services;

import com.pragma.api.domain.AcademicOfferFileDTO;
import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.model.*;
import com.pragma.api.model.enums.PeriodStateEnumeration;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileAcademicOfferImpl implements IFileAcademicOffer {

    private ICourseRepository iCourseRepository;

    private IPersonRepository iPersonRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAcademicOfferFileRepository iAcademicOfferFileRepository;

    private ISubjectRepository iSubjectRepository;

    private IEnvironmentRepository iEnvironmentRepository;

    private IPeriodRepository iPeriodRepository;

    @Autowired
    public FileAcademicOfferImpl(ICourseRepository iCourseRepository, IPersonRepository iPersonRepository, ISubjectRepository iSubjectRepository, IEnvironmentRepository iEnvironmentRepository, IPeriodRepository iPeriodRepository,IAcademicOfferFileRepository iAcademicOfferFileRepository ) {
        this.iCourseRepository = iCourseRepository;
        this.iPersonRepository = iPersonRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iEnvironmentRepository = iEnvironmentRepository;
        this.iPeriodRepository = iPeriodRepository;
        this.iAcademicOfferFileRepository = iAcademicOfferFileRepository;
    }

    @Override
    public List<String> uploadFile(MultipartFile file) throws IOException {

        FileAcademicOffer fileAcademicOffer = new FileAcademicOffer();
        List<FileRowAcademicOffer> logs = fileAcademicOffer.getLogs(file);
        return processFile(logs);

    }

    @Override
    public Response<GenericPageableResponse> findAll(Pageable pageable) {

        Page<AcademicOfferFile> academicOfferFilePage = this.iAcademicOfferFileRepository.findAll(pageable);
        System.out.println("-----------Numero:"+academicOfferFilePage);
        for (int i = 0; i < academicOfferFilePage.getSize(); i++) {
            System.out.println("----------------ADENTRO:"+academicOfferFilePage.getContent().get(i).getNameFile());
        }
        //if (academicOfferFilePage.isEmpty()) throw new ScheduleBadRequestException("bad.request.subject.empty", "");
        //academicOfferFilePage.forEach(x -> System.out.println("nombre:"+x.getNameFile()));
        //academicOfferFilePage.map(x -> System.out.println("Mapa: "+x.getNameFile()+"---"+x.getStateFile()));
        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("File Academy Offer found");
        response.setDeveloperMessage("File Academic Offer found");
        response.setMoreInfo("localhost:8080/api/academicOffer");
        response.setErrorCode("");
        response.setData(this.validatePageList(academicOfferFilePage));
        return response;
    }

    private GenericPageableResponse validatePageList(Page<AcademicOfferFile> academicOfferFilePage) {
        List<AcademicOfferFileDTO> academicOfferFilesDTOS = academicOfferFilePage.stream()
                .map(x -> modelMapper.map(x, AcademicOfferFileDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(academicOfferFilePage, academicOfferFilesDTOS);
    }


    private List<String> processFile(List<FileRowAcademicOffer> logs) {

        List<String> infoLogs = new ArrayList<>();

        for (FileRowAcademicOffer log : logs) {

            Subject subject = null;
            Person person = null;
            CourseTeacher courseTeacher = null;
            Period period = null;

            System.out.println(log.getPersonCode());

            /*
            SE DEBE VALIDAR SOLO EL CODIGO DE LA MATERIA, YA QUE AL SER VARIOS PROFESORES
            LOS CODIGOS DEBEN SER VALIDADOS POR A PARTE
             */
//            if (iPersonRepository.existsById(log.getPersonCode()) && iSubjectRepository.existsById(log.getSubjectCode())) {
            Boolean errorSubjectCode = false;
            if (!iSubjectRepository.existsById(log.getSubjectCode())) {
                infoLogs.add("CODIGO MATERIA NO EXISTE: " + log.getSubjectCode());
                errorSubjectCode = true;
            }

            // SE SEPARAN LOS CODIGOS DE LOS PROFESORES
            String personCode;
            String[] personCodeArray = log.getPersonCode().split(",");
            Boolean errorPersonCode = false;
            for (int i = 0; i < personCodeArray.length; i++) {
                personCode = personCodeArray[i].trim();
                if (!iPersonRepository.existsById(personCode)) {
                    errorPersonCode = true;
                    infoLogs.add("CODIGO PROFESOR NO EXISTE: " + personCode);
                }
            }

            // SI ALGUNO DE LOS CODIGOS DE LOS PROFESORES O EL CODIGO DE LA MATERIA NO EXISTE NO SE CONTINUA CON EL PROCESO
            if (!errorPersonCode && !errorSubjectCode) { // Si no hay error se continua
                subject = iSubjectRepository.findById(log.getSubjectCode()).get();

                // NO SE PUEDE INSTANCIA EL TEACHER AQUI
//                    person = iPersonRepository.findById(log.getPersonCode()).get();
                period = new Period(log.getPeriod(), PeriodStateEnumeration.IN_PROGRESS);

                Course course = new Course(log.getGroup(), log.getCapacity(), log.getWeeklyOverload(), log.getDescription());
//                course.setPeriod(period);

                iPeriodRepository.save(period);

                course.setSubject(subject);

                /*
                    SE RECORREN LOS CODIGOS DE PROFESORES YA VALIDADOS,
                    INSTANCIANDO LOS OBJETOS TEACHER CON CADA UNO DE ESOS CODIGOS
                    CADA OBJETO TEACHER ES AGREGADO A LA LISTA DE PROFESORES DEL OBJETO CURSO
                 */
//                    course.setPerson(person);
                for (int i = 0; i < personCodeArray.length; i++) {
                    personCode = personCodeArray[i].trim();
                    person = iPersonRepository.findById(personCode).get();
                    //course.getAssignedPersons().add(person);
//                    course.getAssignedPersons().add(courseTeacher);
                }

                System.out.println("CURSO: " + course.toString());

                // SE REQUIERE UN FOR PARA ASIGNAR EL CURSO A LOS PROFESORES
//                    person.getCourses().add(course);
//                    subject.getCourses().add(course);
//                    period.getCourses().add(course);
//***                iCourseRepository.save(course);
//                    iPersonRepository.save(person);
//                    iSubjectRepository.save(subject);
//                    iPeriodRepository.save(period);
                infoLogs.add("CURSO REGISTRADO EN SISTEMA");
            } else {
                infoLogs.add("CURSO NO REGISTRADO EN SISTEMA");
            }
        }
        return infoLogs;
    }



}
