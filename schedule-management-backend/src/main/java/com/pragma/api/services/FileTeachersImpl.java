package com.pragma.api.services;

import com.pragma.api.model.Department;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Faculty;
import com.pragma.api.model.Teacher;
import com.pragma.api.repository.IDeparmentRepository;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.repository.ITeacherRepository;
import com.pragma.api.util.file.FileTeachers;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileTeachersImpl implements IFileTeachersService {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IDeparmentRepository deparmentRepository;

    @Override
    public List<String> uploadFile(MultipartFile file) throws IOException {
        FileTeachers fileTeachers = new FileTeachers();
        List<FileRowTeacher> logs = fileTeachers.getLogs(file);
        return processFile(logs);
    }

    @Override
    public List<String> processFile(List<FileRowTeacher> logs) {
        List<String> infoLogs = new ArrayList<>();

        for(FileRowTeacher log:logs){
            System.out.println("NOMBRE PROFESOR" + log.getName_teacher());
            //System.out.println("CODIGO PROFESOR" + log.getCode_teacher());
            System.out.println(log.getCode_teacher());
            System.out.println(log.getName_department());

            //buscamos el id del departamento
            Department department =  deparmentRepository.findDepartmentByDepartmentName(log.getName_department().trim());
            if(department != null){

                Teacher teacher = new Teacher();
                teacher.setTeacherCode(log.getCode_teacher().toString());
                teacher.setFullName(log.getName_teacher());
                teacher.setDepartment(department);
                teacherRepository.save(teacher);
                infoLogs.add("Teacher Created succesfully!");
            }else{
                infoLogs.add("Teacher NOT Created");
            }

        }

        //aqui persistimos los datos y podemos relizar vaidaciones sobre los campos
        return infoLogs;
    }

}
