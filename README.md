# Programación y asignación de horarios de clases - Universidad del Cauca

#### Login: 
- Usuario: admin - Password: admin
#### Nomenclatura ramas
- grupo-[1 - 4]-[nombrerama]
## Tecnologías: 
- Angular - FrontEnd 
- Spring Boot - BackEnd 
- MySql - Base de datos relacional

## Requisitos funcionales 
### A. Información básica de la infraestructura física
Se debe permitir al administrador/secretario académico/gestor de horarios de cada una
facultades de la Universidad del Cauca, el ingreso de la información relacionada con la
infraestructura física (salones, salas, laboratorios y auditorios) ubicada en cada uno de los
edificios pertenecientes a una de las facultades. Esta infraestructura servirán como ambientes
de aprendizaje, por tanto, es necesario registrar: numero, ubicación, capacidad máxima y
recursos adicionales (televisor, proyector, tablero inteligente, instrumentación, etc.). Esta
información se debe permitir ingresar ya sea mediante un archivo o de forma manual.
### B. Información de asignaturas o materias por programas
Se le debe permitir a los coordinadores de uno de los programa académicos ingresar la
información de cada una de las materias pertenecientes a los programas. De las asignaturas
es necesario registrar: código, nombre, semestre, intensidad horaria, jornada y plan
académico. Así como también, los requisitos básicos y necesarios para llevar a cabo su
orientación. Es decir, si esta debe ser orientada en una sala y/o laboratorio y/o salón, junto al
número de horas requeridas y máximo por cada sesión de clase. Esta información se debe
permitir ingresar ya sea mediante un archivo o de forma manual.
### C. Información docentes
Se le debe permitir a los jefes de los departamentos el ingreso de todos los docentes adscritos
a su unidad académica, para lo cual es necesario: identificación, nombre completo, tipo de vinculación (docente nombrado tiempo completo/medio tiempo, ocasional tiempo
completo/medio tiempo, hora cátedra y becario) y horas asignadas. Estas dos últimas
característica pueden variar en cada periodo académico. Esta información se debe permitir
ingresar ya sea mediante un archivo o de forma manual.
### D. Oferta académica
Se le debe permitir a los coordinadores de los programas generar la oferta académica inicial
de las materias a ofrecer en cada uno de los periodos, respecto a: grupos de una materia
(A,B,C, etc.) y el cupo máximo a ofrecer en cada uno de ellos. A los jefes del departamento,
se les debe permitir realizar la asignación de los docentes que orientaran las materias. Tenga
presente que una materia puede ser orientada por varios profesores. Se deberá garantizar que
la asignación de materias a los docentes no sobrepase el número de horas asignadas en un
periodo académico. Finalmente, se le deberá permitir a los jefe del departamento generar
archivo con toda la oferta académica de forma definitiva y enviarla al secretario/gestor de
horarios para realizar la programación.
### E. Programación de horarios
Se le debe permitir al secretario académico la asignación de horarios y ambientes de
aprendizaje respecto a la oferta académica de diferentes programas.
Para esta asignación, se deberán considerar los siguientes requisitos y restricciones básicas:
1. La programación de la oferta académica se iniciará respecto al envío realizado por
cada uno de los jefes de departamento.
2. Cada uno de los programas deberá tener un color asignado para distinguirlos en las
diferentes programaciones de horario.
3. La programación de las asignaturas se deberá iniciar por el semestre más bajo hasta
el más alto. Cumpliendo con todo lo ofertado en cada semestre.
No se debe superar el máximo de horas permitidas para una materia al realizar la
programación. Así como también, no podrán existir la programación de materias de
una sola hora.
5. Un profesor solo podrá orientar una asignatura en una determinada franja horaria del
día.
6. No pueden existir cruces de materias respecto al mismo semestre en una misma franja
horaria.
7. La intensidad horaria de las materias se debe cumplir. Esta no puede ser menor o
mayor a las fijadas por los coordinadores respecto a los planes académicos.
8. En un ambiente de aprendizaje (salón/sala/laboratorio/auditorio) no se podrán asignar
más de un curso en la misma franja horaria.
9. Una asignatura no podrá ser programada en un salón que no cumpla con el cupo
máximo asignado en la oferta académica. Además, de los requisitos adicionales que
esta requiera para su realización.
10. Las modificaciones de horarios de asignaturas o adiciones de nuevas materias se
deberá realizar una vez se termine la programación de la oferta inicial.
### F. Reservas de ambientes de aprendizaje
Se le deberá permitir al secretario académico/gestor de horarios generar reservas de un
ambiente de aprendizaje a docentes o funcionarios de la universidad. Esta reservas podrán
ser para eventos y/o clases de pertenecientes a otros programas académicos. Las reservas,
solo se podrán realizar una vez se termine la programación de la oferta académica de los
diferentes programas. Las reservas podrán ser realizadas por un día, semana o semestre.
Finalmente, hay ambientes de aprendizaje que se podrán reservar para las diferentes
reuniones de los departamentos y comités de los programas académicos. Este tipo de reserva
se podrá realizar antes de la programación de la oferta académica.
### G. Generación de reportes
Se le deberá permitir al secretario académico/gestor de horarios generar un archivo con la
programación y asignación de los horarios realizados (por programa académico y/o facultad
a nivel general).
Se le deberá permitir al coordinador del programa/jefe del departamento generar un reporte
de la programación de los horarios realizada por el secretario académico en el periodo
académico actual (por semestre/ por profesor).
### H. Gestión y Autenticación de usuarios del sistema
Se deberá permitir al administrador dar de alta los diferentes roles que interactúan en el
sistema. Se deberá permitir el proceso de autenticación de los usuarios y recuperación de
credenciales.

## Requisitos no funcionales

1. **Escalabilidad:** Capacidad de garantizar una creciente carga de trabajo, respecto al
número de conexiones o usuarios. Capacidad de almacenamiento de los datos.
2. **Seguridad:** Importante considerar mecanismos de autenticación. Autorización o
derechos de los usuarios en el sistema. Integridad de los datos para que los datos no
sean alterados.
3. **Modificabilidad:** El sistema puede cambiar así que debería poderse adaptar a nuevos
requerimientos funcionales y no funcionales.
4. **Usabilidad:** Garantizar facilidad de uso y aprendizaje a los diferentes roles
(Administrador, secretario académico, coordinador del programa y jefe del
departamento).
5. **Portabilidad:** Se deberá garantizar que la aplicación funcione en diferentes
plataformas.
