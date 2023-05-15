package com.pragma.api.services;

import com.pragma.api.domain.PersonDTO;
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
    private IPersonService iPersonService;

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
        List<String> infoErrores = new ArrayList<>();

        int contOk = 0;
        int contError = 0;

        for(FileRowTeacher log:logs){

            int rowNum = log.getRowNum() + 1;
            infoErrores.clear();


            System.out.println("NOMBRE PROFESOR" + log.getName_teacher());
            //System.out.println("CODIGO PROFESOR" + log.getCode_teacher());
            System.out.println(log.getCode_teacher());
            System.out.println(log.getName_department());


            //buscamos si existe la persona ya creada
            PersonDTO personDTO = this.iPersonService.findByCode(String.valueOf(log.getCode_teacher()));

            //verificamos que el profesor no se encuentre registrado
            if(personDTO == null){

                //buscamos el id del departamento
                Department department =  deparmentRepository.findDepartmentByDepartmentName(log.getName_department().trim());
                Boolean errorDepartamento = false;

                if(department != null){

                    Person person = new Person();
                    person.setPersonCode(log.getCode_teacher().toString());
                    person.setFullName(log.getName_teacher());
                    person.setDepartment(department);
                    person.setPersonType(PersonTypeEnumeration.TEACHER);
                    personRepository.save(person);
                    infoLogs.add("Teacher Created succesfully!");
                }else{
                    errorDepartamento = true;
                    infoErrores.add(" - EL DEPARTAMENTO ASIGNADO AL PROFESOR NO SE ENCUENTRA REGISTRADO: " + log.getName_department().trim());
                    infoLogs.add("Teacher NOT Created");
                }


                if (!errorDepartamento) {
                    infoLogs.add("[FILA " + rowNum + "] LISTA PARA SER REGISTRADA");
                    contOk++;
                } else {
                    infoLogs.add("[FILA " + rowNum + "] CONTIENE ERRORES:");
                    for (String infoError : infoErrores) {
                        infoLogs.add(infoError);
                    }
                    contError++;
                }

            }

        }

        if (contError > 0) {
            infoLogs.add(0, "ESTADO ARCHIVO: Error");
        } else {
            infoLogs.add(0, "ESTADO ARCHIVO: Success");
        }
        infoLogs.add(1, "FILAS CON ERRORES: " + contError);
        infoLogs.add(2, "FILAS CORRECTAS: " + contOk);
        return infoLogs;
    }

}
