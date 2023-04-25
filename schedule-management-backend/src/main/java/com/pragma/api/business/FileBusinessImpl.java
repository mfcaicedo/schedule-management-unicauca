package com.pragma.api.business;

import com.pragma.api.model.Course;
import com.pragma.api.model.Period;
import com.pragma.api.model.Subject;
import com.pragma.api.model.Teacher;
import com.pragma.api.model.enums.PeriodStateEnumeration;
import com.pragma.api.repository.*;
import com.pragma.api.util.file.templateclasses.FileRow;
import com.pragma.api.util.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileBusinessImpl implements IFileBusiness {

    private ICourseRepository iCourseRepository;

    private ITeacherRepository iTeacherRepository;

    private ISubjectRepository iSubjectRepository;

    private IEnvironmentRepository iEnvironmentRepository;

    private IPeriodRepository iPeriodRepository;

    @Autowired
    public FileBusinessImpl(ICourseRepository iCourseRepository, ITeacherRepository iTeacherRepository, ISubjectRepository iSubjectRepository, IEnvironmentRepository iEnvironmentRepository, IPeriodRepository iPeriodRepository) {
        this.iCourseRepository = iCourseRepository;
        this.iTeacherRepository = iTeacherRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iEnvironmentRepository = iEnvironmentRepository;
        this.iPeriodRepository = iPeriodRepository;
    }

    @Override
    public List<String> uploadFile(MultipartFile file) throws IOException {

        List<FileRow> logs = FileUtils.getLogs(file);
        return processFile(logs);

    }

    private List<String> processFile(List<FileRow> logs) {

        List<String> infoLogs = new ArrayList<>();

        for (FileRow log : logs) {

            Subject subject = null;
            Teacher teacher = null;
            Period period = null;

            System.out.println(log.getTeacherCode());

            /*
            SE DEBE VALIDAR SOLO EL CODIGO DE LA MATERIA, YA QUE AL SER VARIOS PROFESORES
            LOS CODIGOS DEBEN SER VALIDADOS POR A PARTE
             */
//            if (iTeacherRepository.existsById(log.getTeacherCode()) && iSubjectRepository.existsById(log.getSubjectCode())) {
            Boolean errorSubjectCode = false;
            if (iSubjectRepository.existsById(log.getSubjectCode())) {
                infoLogs.add("CODIGO MATERIA NO EXISTE: " + log.getSubjectCode());
                errorSubjectCode = true;
            }

            // SE SEPARAN LOS CODIGOS DE LOS PROFESORES
            String teacherCode;
            String[] teacherCodeArray = log.getTeacherCode().split(",");
            Boolean errorTeacherCode = false;
            for (int i = 0; i < teacherCodeArray.length; i++) {
                teacherCode = teacherCodeArray[i].trim();
                if (!iTeacherRepository.existsById(teacherCode)) {
                    errorTeacherCode = true;
                    infoLogs.add("CODIGO PROFESOR NO EXISTE: " + teacherCode);
                }
            }

            // SI ALGUNO DE LOS CODIGOS DE LOS PROFESORES O EL CODIGO DE LA MATERIA NO EXISTE NO SE CONTINUA CON EL PROCESO
            if (!errorTeacherCode && !errorSubjectCode) { // Si no hay error se continua
                subject = iSubjectRepository.findById(log.getSubjectCode()).get();

                // NO SE PUEDE INSTANCIA EL TEACHER AQUI
//                    teacher = iTeacherRepository.findById(log.getTeacherCode()).get();
                period = new Period(log.getPeriod(), PeriodStateEnumeration.IN_PROGRESS);

                Course course = new Course(log.getGroup(), log.getCapacity(), log.getWeeklyOverload(), log.getDescription());
                course.setPeriod(period);

                iPeriodRepository.save(period);

                course.setSubject(subject);

                /*
                    SE RECORREN LOS CODIGOS DE PROFESORES YA VALIDADOS,
                    INSTANCIANDO LOS OBJETOS TEACHER CON CADA UNO DE ESOS CODIGOS
                    CADA OBJETO TEACHER ES AGREGADO A LA LISTA DE PROFESORES DEL OBJETO CURSO
                 */
//                    course.setTeacher(teacher);
                for (int i = 0; i < teacherCodeArray.length; i++) {
                    teacherCode = teacherCodeArray[i].trim();
                    teacher = iTeacherRepository.findById(teacherCode).get();
                    course.getProfesoresAsignados().add(teacher);
                }

                System.out.println("CURSO: " + course.toString());

                // SE REQUIERE UN FOR PARA ASIGNAR EL CURSO A LOS PROFESORES
//                    teacher.getCourses().add(course);
//                    subject.getCourses().add(course);
//                    period.getCourses().add(course);
//***                iCourseRepository.save(course);
//                    iTeacherRepository.save(teacher);
//                    iSubjectRepository.save(subject);
//                    iPeriodRepository.save(period);
                infoLogs.add("Course Created succesfully!");
            }
        }
        return infoLogs;
    }
}
