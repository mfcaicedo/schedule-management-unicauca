### Características

- Proyecto hecho en Java 18.
- Proyecto elaborado por estudiantes de la Universidad del Cauca
- Contiene dependencias básicas y  esenciales para la construcción de un API REST.
- Utiliza Gradle como herramienta de automatización de compilación de código.
- Construido utilizando Spring Boot.


# Gestión Horaria Unicauca

**Tabla de contenido**

[TOC]

# Propósito del proyecto

Este proyecto tiene como propósito proporcionar una aplicación basa en servicios de API REST con una arquitectura simple y unas dependencias básicas que son especialmente útiles y casi imprescindibles al momento de elaborar proyectos en Java, estableciendo un kit de herramientas básicas para crear el proeycto de gestión horaria de las facultades de la Universidad del Cauca

# Estructura del proyecto

El proyecto se compone de 8 carpetas bien diferenciadas, cada una con un propósito distinto con relación a la gestión de productos.

## Business
En esta capa se realizan las operaciones propias de la lógica asociada al negocio, como crear registros en la base de datos y consultarlos por un código.

## Domain
En esta capa se establecen clases Data Transfer Object (DTO) y clases asociadas a la estructura mediante la cual se responde al cliente una vez se ha realizado un proceso.

## Exception
Aquí se construyen aquellas clases asociadas al manejo de excepciones, definiendo una estructura personalizada para presentar esta información al usuario.

## Mapper
En esta carpeta se establece la lógica que permite mapear los campos o atributos de objetos que viajan hacia y desde el sistema. 

## Model
En esta capa se establecen las clases que representan los registros que se quieren guardar en la base de datos, especificando exactamente los campos que deben tener al momento de ser almacenados.

## Repository
En esta carpeta se determina la manera en la cual se realizan las operaciones CRUD en la base de datos.

## Rest
En este sitio se contruyen los controladores para el API REST.

## Util
En este lugar se crean aquellas clases que son útiles de manera transversal a la aplicación. Por ejemplo: Una clase para llevar a cabo validaciones.

# Dependencias

A continuación enumeramos las 3 principales dependencias que resultan sumamente útiles a cualquier programador Java.

## Lombok

Project Lombok es una biblioteca de Java que se conecta automáticamente al editor y herramientas de compilación. Esta biblioteca permite la generación de los métodos get y set en tiempo de compilación, de tal manera que con una simple anotación en la clase, el programador se ahorra la molesta tarea de difinirlos manualmente.

<https://projectlombok.org/>

## Mapstruct

"MapStruct es un generador de código que simplifica enormemente la implementación de mapeos entre tipos de bean Java basado en una convención sobre el enfoque de configuración".

<https://mapstruct.org/>

## Swagger
"Swagger es una serie de reglas, especificaciones y herramientas que nos ayudan a documentar nuestras APIs.”

<https://swagger.io/>
