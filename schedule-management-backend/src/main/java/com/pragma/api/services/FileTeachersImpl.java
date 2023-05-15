package com.pragma.api.services;

import com.pragma.api.model.Department;
import com.pragma.api.model.Person;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import com.pragma.api.repository.IDeparmentRepository;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.util.file.FileTeachers;
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
    private IPersonRepository personRepository;

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

                Person person = new Person();
                person.setPersonCode(log.getCode_teacher().toString());
                person.setFullName(log.getName_teacher());
                person.setDepartment(department);
                person.setPersonType(PersonTypeEnumeration.TEACHER);
                personRepository.save(person);
                infoLogs.add("Teacher Created succesfully!");
            }else{
                infoLogs.add("Teacher NOT Created");
            }

        }

        //aqui persistimos los datos y podemos relizar vaidaciones sobre los campos
        return infoLogs;
    }

}
