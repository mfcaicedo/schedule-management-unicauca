package com.pragma.api.domain;

import java.util.List;

public class ResponseFile {


    private int status;
    private String message;
    private String TypeError;
    //ERRORES DE TIPO
    private List<String> logsType;
    //ERRORES DE CAMPOS
    private List<String> logsEmptyFields;
    //ERRORES GENERALES DE LA CLASE ESPECIFICA
    private List<String> logsGeneric;




}
