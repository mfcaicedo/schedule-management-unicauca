package com.pragma.api.business;

import com.pragma.api.model.Course;
import com.pragma.api.model.Period;
import com.pragma.api.model.Subject;
import com.pragma.api.model.Teacher;
import com.pragma.api.model.enums.PeriodStateEnumeration;
import com.pragma.api.repository.*;
import com.pragma.api.util.FileRow;
import com.pragma.api.util.FileUtils;
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

    private List<String> processFile(List<FileRow> logs){

        List<String> infoLogs = new ArrayList<>();

        for(FileRow log:logs){

            Subject subject = null;
            Teacher teacher = null;
            Period period = null;

            System.out.println(log.getTeacherCode());

            if(iTeacherRepository.existsById(log.getTeacherCode()) && iSubjectRepository.existsById(log.getSubjectCode())){
                subject = iSubjectRepository.findById(log.getSubjectCode()).get();
                teacher = iTeacherRepository.findById(log.getTeacherCode()).get();
                period = new Period(log.getPeriod(), PeriodStateEnumeration.IN_PROGRESS);

                Course course = new Course(log.getGroup(),log.getCapacity(),log.getWeeklyOverload(), log.getDescription());

                iPeriodRepository.save(period);

                course.setSubject(subject);
                course.setTeacher(teacher);
                course.setPeriod(period);
                teacher.getCourses().add(course);
                subject.getCourses().add(course);
                period.getCourses().add(course);

                iCourseRepository.save(course);
                iTeacherRepository.save(teacher);
                iSubjectRepository.save(subject);
                iPeriodRepository.save(period);


                infoLogs.add("Course Created succesfully!");
            }else{
                infoLogs.add("Course NOT Created");
            }



        }


        return infoLogs;
    }
}
