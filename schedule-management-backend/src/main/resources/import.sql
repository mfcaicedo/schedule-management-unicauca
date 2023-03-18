--Faculty
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIET', 'FACULTAD DE INGENIERIA ELECTRONICA Y DE COMUNICACIONES');
--Department
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('1', 'Sistemas', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('2', 'Telecomunicaciones', 'FIET');
--Program
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIS', 'INGENIERIA DE SISTEMAS', '1','bg-orange');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color` ) VALUES ('PIET', 'INGENIERIA ELECTRONICA Y TELECOMUNICACIONES', '2','bg-sky');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIAI', 'INGENIERIA AUMOTAMICA INDISTRIAL', '1','bg-orange');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('TTM', 'TECNOLOGIA EN TELEMATICA', '2','bg-orange');
--Teacher
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1061', 'DANIEL PAZ', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1062', 'CLAUDIA SOFIA IDROBO CRUZ', '1', 'PIAI');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1063', 'MARTIN ALONSO MUÑOZ MEDINA', '1', 'PIAI');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1064', 'JUAN CARLOS NARVAEZ NARVAEZ', '1', 'PIAI');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1065', 'FRANCISCO FRANCO OBANDO DIAZ', '1', 'PIAI');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1066', 'VICTOR HUGO MOSQUERA LEYTON', '1', 'PIAI');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1067', 'LAURA ANDREA BERMUDEZ CORDOBA', '1', 'PIAI');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1068', 'GUSTAVO ADOLFO GOMEZ AGREDO', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1069', 'CATALINA   MUÑOZ COLLAZOS', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1070', 'MANUEL SANIN BENAVIDES PIAMBA', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1071', 'KARIN   CORREA ARANA', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1072', 'JESUS MAURICIO RAMIREZ VIAFARA', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1073', 'MODESTO FAJARDO ', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1074', 'FULVIO YESID VIVAS CANTERO', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1075', 'FABIO HERNAN REALPE MARTINEZ', '1', 'PIET');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1076', 'JHON EDER MASSO DAZA', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1077', 'RENE FABIAN ZUÑIGA MUÑOZ', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1078', 'FLOR DE MARIA HERNANDEZ PEREZ', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1079', 'JIMENA ADRIANA TIMANA PEÑA', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1080', 'CARLOS ALBERTO ARDILA ALBARRACIN', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1081', 'RICARDO ANTONIO ZAMBRANO SEGURA', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1082', 'ERWIN   MEZA VEGA', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1083', 'FRANCISCO JAVIER OBANDO VIDAL', '1', 'PIS');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1084', 'LISETH VIVIANA CAMPO ARCOS', '1', 'TTM');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1085', 'GINETH MAGALY CERON RIOS', '1', 'TTM');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1086', 'JUAN DAVID YIP HERRERA', '1', 'TTM');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1087', 'IVAN EDUARDO HERNANDEZ DELGADO', '1', 'TTM');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`, `program_id`) VALUES ('1088', 'LISETH VIVIANA CAMPO ARCOS', '1', 'TTM');





--Period
INSERT INTO `period` (`periodId`, `state`) VALUES ('2021_02', 'FINISHED');
INSERT INTO `period` (`periodId`, `state`) VALUES ('2022_01', 'FINISHED');






--Subject
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('POO', 'Programacion Orientada a Objetos', '2', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ESTR1', 'Estructura de Datos', '2',false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ESTR2', 'Estructura de Datos 2', '3', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('SISDIS', 'Sistemas Distribuidos', '4',false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CALSOFT', 'Calidad de Software', '6', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('INTRO1', 'Introduccion programacion', '1',false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD1', 'Bases de datos 1', '3', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD2', 'Bases de datos 2', '4',false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LEGIS', 'Legislacion Laboral', '7', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ARQUITE', 'Arquitectura', '5',false, '4', 'PIS');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1003A','Algebra Lineal', '1',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1004A','Cálculo Diferencial', '1',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1005A','Introducción a la Ingeniería', '1',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1006A','Lectura y Escritura', '1',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1007A','Cálculo  Integral', '2',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1008A','Circuitos  de corriente Directa', '2',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1009A','Mecánica', '2',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1010A','Programación Orientada a Objetos', '2',false, '4', 'PIET');



--Environment
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 1', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 2', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 3', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 4', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 5', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 6', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 201', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 101', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 102', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '40', 'SALON', 'Salon IPET FIET', 'Salon 102', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 110', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 202', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 203', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 204', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 220', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 222', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 231', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 331', 'FIET');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'AUDITORIO', 'Edificio IPET FIET', 'Aurditorio IPET', 'FIET');

--Resource
INSERT INTO `resource` (`id`, `name`, `resource_type`) VALUES (NULL, 'Video Beam', 'TECNOLOGICO');
INSERT INTO `resource` (`id`, `name`, `resource_type`) VALUES (NULL, 'Televisor', 'TECNOLOGICO');

--Resource environment
INSERT INTO `available_resources` (`environment_id`, `resource_id`) VALUES (1,1);

 --INSERT INTO `course` (`course_id`, `course_capacity`, `course_group`, `period_periodId`, `subject_code`, `teacher_code`, `remaining_hours`) VALUES ('1', '13', 'A', '2021_02', 'POO', '1061', '4');
 --INSERT INTO `course` (`course_id`, `course_capacity`, `course_group`, `period_periodId`, `subject_code`, `teacher_code`, `remaining_hours`) VALUES ('2', '20', 'B', '2021_02', 'ESTR1', '1062', '4');
--Resource environment

INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('2', 'ROLE_USER');

