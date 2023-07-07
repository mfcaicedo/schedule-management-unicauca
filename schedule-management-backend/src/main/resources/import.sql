--Faculty
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIET', 'FACULTAD DE INGENIERIA ELECTRONICA Y DE COMUNICACIONES');
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIC', 'FACULTAD DE INGENIERIA CIVIL');
--Department
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('1', 'Sistemas', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('2', 'Telecomunicaciones', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('3', 'Civil', 'FIC');
--Program
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIS', 'INGENIERIA DE SISTEMAS', '1','bg-orange');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color` ) VALUES ('PIET', 'INGENIERIA ELECTRONICA Y TELECOMUNICACIONES', '2','bg-pink');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIAI', 'INGENIERIA AUMOTAMICA INDISTRIAL', '1','bg-green');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('TTM', 'TECNOLOGIA EN TELEMATICA', '2','bg-purple');
--Person
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1061', 'DANIEL PAZ', '1', 'TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1062', 'CLAUDIA SOFIA IDROBO CRUZ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1063', 'MARTIN ALONSO MUÑOZ MEDINA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1064', 'JUAN CARLOS NARVAEZ NARVAEZ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1065', 'FRANCISCO FRANCO OBANDO DIAZ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1066', 'VICTOR HUGO MOSQUERA LEYTON', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1067', 'LAURA ANDREA BERMUDEZ CORDOBA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1068', 'GUSTAVO ADOLFO GOMEZ AGREDO', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1069', 'CATALINA   MUÑOZ COLLAZOS', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1070', 'MANUEL SANIN BENAVIDES PIAMBA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1071', 'KARIN   CORREA ARANA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1072', 'JESUS MAURICIO RAMIREZ VIAFARA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1073', 'MODESTO FAJARDO ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1074', 'FULVIO YESID VIVAS CANTERO', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1075', 'FABIO HERNAN REALPE MARTINEZ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1076', 'JHON EDER MASSO DAZA', '1','ADMINISTRATIVE');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1077', 'RENE FABIAN ZUÑIGA MUÑOZ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1078', 'FLOR DE MARIA HERNANDEZ PEREZ', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1079', 'JIMENA ADRIANA TIMANA PEÑA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1080', 'CARLOS ALBERTO ARDILA ALBARRACIN', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1081', 'RICARDO ANTONIO ZAMBRANO SEGURA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1082', 'ERWIN   MEZA VEGA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1083', 'FRANCISCO JAVIER OBANDO VIDAL', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1084', 'LISETH VIVIANA CAMPO ARCOS', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1085', 'GINETH MAGALY CERON RIOS', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1086', 'JUAN DAVID YIP HERRERA', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1087', 'IVAN EDUARDO HERNANDEZ DELGADO', '1','TEACHER');
INSERT INTO `person` (`personCode`, `full_name`, `department_id`,`person_type`) VALUES ('1088', 'LISETH VIVIANA CAMPO ARCOS', '1','TEACHER');

--Period
INSERT INTO `period` (`periodId`, `initDate`, `endDate`, `state`) VALUES ('2021-2', '2021-07-01 00:00:00', '2021-11-30 23:59:59','FINISHED');
INSERT INTO `period` (`periodId`, `initDate`, `endDate`, `state`) VALUES ('2022-1', '2022-02-01 00:00:00', '2022-06-30 23:59:59','FINISHED');
INSERT INTO `period` (`periodId`, `initDate`, `endDate`, `state`) VALUES ('2023-1', '2023-02-15 23:59:59', '2023-07-15 00:00:00','IN_PROGRESS');

--Subject
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CAL_I', 'CALCULO I', '1', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LEC', 'LECTOESCRITURA', '1', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ELE1', 'ELECTIVA1', '1', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('INT_ING', 'INTRODUCCION A LA INGENIERIA', '1', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('INT_INF', 'INTRODUCCION A LA INFORMATICA', '1', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_INT_INF', 'LABORATORIO INTRODICCION A LA INFORMATICA', '1', false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CAL_II', 'CALCULO2', '2', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ALG', 'ALGEBRA LINEAL', '2', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('MEC', 'MECANICA', '2', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ELE2', 'ELECTIVA 2', '2', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_MEC', 'LABORATORIO MECANICA', '2', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('POO', 'PROGRAMACION ORIWNTADA A OBJETOS', '2', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_POO', 'LAB ORIENTADA A OBJETOS', '2', false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CAL_III', 'Cálculo III ', '3', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ELE', 'Electromagnetismo', '3', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_ELE', 'Laboratorio de Electromagnetismo', '3', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ELE3', 'Electiva FISHI III', '3', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('EST_DAT', 'Estructura de Datos I', '3', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_EST_DAT', 'Laboratorio Estructura de Datos I', '3', false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ECU', 'Ecuaciones Diferenciales Ordinarias', '4', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('VIB', 'Vibraciones y Ondas ', '4', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('EST_DAT2', ' Estructura de Datos II', '4', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_EST_DAT2', 'Laboratorio de Estructura de Datos II ', '4', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD1', 'Bases de Datos I', '4', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_BD1', 'Laboratorio de Bases de Datos I', '4', false, '4', 'PIAI');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ANA', ' Análisis Numérico ', '5', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('TEO', ' Teoría de la Computación', '5', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ARQ', ' Arquitectura Computacional', '5', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ING_SW1', 'Ingeniería de Software I', '5', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_ING_SW1', 'Laboratorio de Ingeniería de Software', '5', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD2', 'Bases de Datos II', '5', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_BD2', 'Laboratorio de Bases de Datos II (1 c)', '5', false, '4', 'PIAI');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('EST_PRO', 'Estadística y Probabilidad ', '6', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('EST_LEN', 'Estructuras de Lenguajes', '6', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_EST_LEN', 'Laboratorio de Estructuras de Lenguajes ', '6', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ING_SW2', 'Ingeniería de Software II ', '6', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_ING_SW2', 'Laboratorio de Ingeniería de Software II', '6', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('SIS_OPE', 'Sistemas Operativos ', '6', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_SIS_OPE', 'Laboratorio de Sistemas Operativos', '6', false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('MET', 'Metodología de la Investigación ', '7', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('IA', 'Inteligencias Artificial', '7', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('DIN', 'Teoría Dinámica de Sistemas ', '7', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('DIS', 'Sistemas Distribuidos', '7', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_DIS', 'Laboratorio de Sistemas Distribuidos ', '7', false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ING_SW3', 'Ingeniería de Software III', '7', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LAB_ING_SW3', 'Laboratorio de Ingeniería de Software III', '7', false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('RED', 'Redes', '8', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('INV', 'Investigación de Operaciones', '8', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('PRO', 'Proyecto I', '8', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CAL_SW', 'Calidad de Software', '8', false, '4', 'PIAI');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('GES_EMP', 'Gestión empresarial', '9', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('GES_PRO', 'Gestión de Proyectos Informáticos', '9', false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('PRO2', 'Proyecto II', '9', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('FUND', 'Fundamentos de Economía', '9', false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LEG', 'Legislación Laboral', '10', false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('TDG', 'Trabajo de Grado', '10', false, '4', 'PIS');

-- --Grupo 1 subject
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('POO', 'Programacion Orientada a Objetos', '1', false, '2', 'PIS');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ESTR1', 'Estructura de Datos', '2',false, '4', 'PIS');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ESTR2', 'Estructura de Datos 2', '2', false, '2', 'PIS');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('SISDIS', 'Sistemas Distribuidos', '4',false, '4', 'PIS');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CALSOFT', 'Calidad de Software', '6', false, '2', 'PIS');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('INTRO1', 'Introduccion programacion', '1',false, '4', 'PIS');
--
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD1', 'Bases de datos 1', '1', false, '2', 'TTM');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD2', 'Bases de datos 2', '1',false, '4', 'TTM');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LEGIS', 'Legislacion Laboral', '2', false, '2', 'TTM');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ARQUITE', 'Arquitectura', '2',false, '4', 'TTM');
--
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1003A','Algebra Lineal', '1',false, '4', 'PIET');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1004A','Cálculo Diferencial', '1',false, '4', 'PIET');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1005A','Introducción a la Ingeniería', '2',false, '4', 'PIET');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1006A','Lectura y Escritura', '2',false, '4', 'PIET');
--
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1007A','Cálculo  Integral', '1',false, '4', '	PIAI');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1008A','Circuitos  de corriente Directa', '1',false, '4', 'PIAI');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1009A','Mecánica', '1',false, '4', 'PIAI');
-- INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1010A','Programación Orientada a Objetos', '2',false, '4', 'PIAI');




--Environment
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, NULL, 'EDIFICIO', 'no aplica', 'IPET', 'FIET',NULL);
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, NULL, 'EDIFICIO', 'no aplica', 'Geotecnia', 'FIET',NULL);
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Lab. Hidraulica', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Lab. Electromagnetismo', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'lab. Mecanica', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Lab. Fisica', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Lab Robotica', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Lab Circuitos', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 101', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 102', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 103', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '40', 'SALON', 'Salon IPET FIET', 'Salon 104', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 105', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 106', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 107', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 108', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 109', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 110', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 201', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 202', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 203', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '40', 'SALON', 'Salon IPET FIET', 'Salon 204', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 205', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 206', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 207', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 208', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 209', 'FIET','1');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 210', 'FIET','2');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES (NULL, '30', 'AUDITORIO', 'Edificio IPET FIET', 'Aurditorio IPET', 'FIET','2');

--Resource
INSERT INTO `resource` (`resource_id`, `name`, `resource_type`) VALUES (NULL, 'Video Beam', 'TECNOLOGICO');
INSERT INTO `resource` (`resource_id`, `name`, `resource_type`) VALUES (NULL, 'Televisor', 'TECNOLOGICO');
INSERT INTO `resource` (`resource_id`, `name`, `resource_type`) VALUES (NULL, 'Computador', 'TECNOLOGICO');

--Resource environment
--INSERT INTO `available_resources` (`environment_id`, `resource_id`) VALUES (1,1);


--resource course

INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('20', '25', 'A', 'descripcion ', '4', 'CAL_II');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('21', '25', 'A', 'descripcion ', '4', 'ALG');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('22', '25', 'A', 'descripcion ', '4', 'MEC');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('23', '25', 'A', 'descripcion ', '4', 'ELE2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('24', '25', 'A', 'descripcion ', '4', 'LAB_MEC');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('25', '25', 'A', 'descripcion ', '4', 'POO');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('26', '25', 'A', 'descripcion ', '4', 'LAB_POO');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('11', '25', 'A', 'descripcion ', '4', 'CAL_I');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('12', '25', 'A', 'descripcion ', '4', 'LEC');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('13', '25', 'A', 'descripcion ', '4', 'ELE1');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('14', '25', 'A', 'descripcion ', '4', 'INT_ING');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('15', '25', 'A', 'descripcion ', '4', 'INT_INF');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('16', '25', 'A', 'descripcion ', '4', 'LAB_INT_INF');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('31', '25', 'A', 'descripcion ', '4', 'CAL_III');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('32', '25', 'A', 'descripcion ', '4', 'ELE');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('33', '25', 'A', 'descripcion ', '4', 'LAB_ELE');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('34', '25', 'A', 'descripcion ', '4', 'ELE3');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('35', '25', 'A', 'descripcion ', '4', 'EST_DAT');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('36', '25', 'A', 'descripcion ', '4', 'LAB_EST_DAT');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('41', '25', 'A', 'descripcion ', '4', 'ECU');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('42', '25', 'A', 'descripcion ', '4', 'VIB');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('43', '25', 'A', 'descripcion ', '4', 'EST_DAT2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('44', '25', 'A', 'descripcion ', '4', 'LAB_EST_DAT2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('45', '25', 'A', 'descripcion ', '4', 'BD1');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('46', '25', 'A', 'descripcion ', '4', 'LAB_BD1');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('51', '25', 'A', 'descripcion ', '4', 'ANA');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('52', '25', 'A', 'descripcion ', '4', 'TEO');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('53', '25', 'A', 'descripcion ', '4', 'ARQ');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('54', '25', 'A', 'descripcion ', '4', 'ING_SW1');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('55', '25', 'A', 'descripcion ', '4', 'LAB_ING_SW1');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('56', '25', 'A', 'descripcion ', '4', 'BD2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('57', '25', 'A', 'descripcion ', '4', 'LAB_BD2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('61', '25', 'A', 'descripcion ', '4', 'EST_PRO');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('62', '25', 'A', 'descripcion ', '4', 'EST_LEN');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('63', '25', 'A', 'descripcion ', '4', 'LAB_EST_LEN');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('64', '25', 'A', 'descripcion ', '4', 'ING_SW2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('65', '25', 'A', 'descripcion ', '4', 'LAB_ING_SW2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('66', '25', 'A', 'descripcion ', '4', 'SIS_OPE');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('67', '25', 'A', 'descripcion ', '4', 'LAB_SIS_OPE');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('71', '25', 'A', 'descripcion ', '4', 'MET');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('72', '25', 'A', 'descripcion ', '4', 'IA');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('73', '25', 'A', 'descripcion ', '4', 'DIN');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('74', '25', 'A', 'descripcion ', '4', 'DIS');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('75', '25', 'A', 'descripcion ', '4', 'LAB_DIS');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('76', '25', 'A', 'descripcion ', '4', 'ING_SW3');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('77', '25', 'A', 'descripcion ', '4', 'LAB_ING_SW3');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('81', '25', 'A', 'descripcion ', '4', 'RED');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('82', '25', 'A', 'descripcion ', '4', 'INV');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('83', '25', 'A', 'descripcion ', '4', 'PRO');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('84', '25', 'A', 'descripcion ', '4', 'CAL_SW');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('91', '25', 'A', 'descripcion ', '4', 'GES_EMP');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('92', '25', 'A', 'descripcion ', '4', 'GES_PRO');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('93', '25', 'A', 'descripcion ', '4', 'PRO2');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('94', '25', 'A', 'descripcion ', '4', 'FUND');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('101', '25', 'A', 'descripcion ', '4', 'LEG');
INSERT INTO course (course_id, course_capacity,course_group,type_environment_required,remaining_hours,subject_code) VALUES ('102', '25', 'A', 'descripcion ', '4', 'TDG');

--Curso de grupo 1
--resource course sistemas
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('1', '25', 'B','4','LABORATORIO','POO');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('2', '13', 'A','4','LABORATORIO','POO');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('3', '20', 'B','4','asd','ESTR1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('4', '20', 'B','4','asd','ESTR2');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('5', '20', 'B','4','asd','SISDIS');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('6', '20', 'B','4','asd','CALSOFT');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('103', '13', 'A','4','SALON','INTRO1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('104', '13', 'B','4','SALON','INTRO1');

--resource course electronica
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('105', '20', 'B','4','LABORATORIO','1003A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('106', '20', 'A','4','AUDITORIO','1003A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('107', '20', 'C','4','AUDITORIO','1003A');

--resource course automatica
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('108', '20', 'B','4','asd','1009A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('109', '20', 'A','4','asd','1009A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('110', '20', 'C','4','asd','1009A');

--resource course telematica
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('7', '20', 'B','4','asd','BD1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('8', '20', 'C','4','asd','BD1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('9', '20', 'A','4','asd','BD1');


--Resource environment
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('1', 'ROLE_SCHEDULE_MANAGER');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('2', 'ROLE_ACADEMIC_MANAGER');

INSERT INTO `event` (`event_id`, `description`, `event_manager_name`, `event_name`, `event_type`, `person_code`) VALUES (NULL, 'charla iaa', 'Mr.Masso', 'Charla Inteligencia', 'OTROS', '1062');


--horario
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`) VALUES (NULL, 'JUEVES', '2023-06-15', '09:00:00', 1, '2023-06-15', '07:00:00', NULL, 1, 1);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`) VALUES (NULL, 'JUEVES', '2023-06-15', '11:00:00', 0, '2023-06-15', '09:00:00', 1, 1, NULL);

--resource event

INSERT INTO `event` (`event_id`, `description`, `event_manager_name`, `event_name`, `event_type`, `person_code`)VALUES (NULL, 'conferencia', 'Mr.Masso', 'conferenciado', 'CONFERENCIA',  '1062');
--resource Schedule
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'LUNES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:19:18', '3', '8', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'MARTES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:19:18', '6', '8', '2');

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'MIERCOLES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:00:18', '3', '6', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'JUEVES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:00:18', '4', '6', '2');


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'LUNES', '2023-05-18 11:19:18', '13:19:18', b'0', '2023-05-16 10:19:18', '05:19:18', '3', '12', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'VIERNES', '2023-05-20 11:19:18', '11:19:18', b'1', '2023-05-18 10:19:18', '04:19:18', '5', '10', '2');

---schedule civil
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'LUNES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:19:18', '3', '14', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'MARTES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:19:18', '4', '14', '2');

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'MIERCOLES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:00:18', '5', '14', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'JUEVES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:00:18', '6', '14', '2');


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'LUNES', '2023-05-18 11:19:18', '13:19:18', b'0', '2023-05-16 10:19:18', '05:19:18', '7', '14', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES (NULL, 'VIERNES', '2023-05-20 11:19:18', '11:19:18', b'1', '2023-05-18 10:19:18', '04:19:18', '7', '14', '2');

INSERT INTO `event` (`event_id`, `description`, `event_manager_name`, `event_name`, `event_type`, `program_id`, `person_code`)VALUES ('2', 'conferencia', 'conferencia', 'conferenciado', 'CONFERENCIA', 'PIS', '1061');
--resource Schedule SALON 101(COD:9)
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '11', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '20', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '31', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '41', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '51', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '61', '9', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '12', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '21', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '32', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '42', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '52', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '62', '9', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '13', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '22', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '33', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '43', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '53', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '63', '9', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '14', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '23', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '34', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '44', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '54', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '64', '9', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '15', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '24', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '35', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '45', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '55', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '65', '9', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '16', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '25', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '36', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '46', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '56', '9', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '66', '9', NULL);


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '26', '13', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '57', '13', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '67', '13', NULL);

--resource Schedule SALON 102(COD:10)
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '11', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '41', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '51', '10', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '12', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '32', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '52', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '62', '10', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '33', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '43', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '53', '10', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '14', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '23', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '44', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '54', '10', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '35', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '45', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '55', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '65', '10', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '16', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '25', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '46', '10', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '56', '10', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '57', '13', NULL);

--resource Schedule SALON 103(COD:11)
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '71', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '82', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '73', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'LUNES', '2023-05-18 11:00:18', '20:00:18', b'0', '2023-05-16 10:19:18', '19:00:18', '84', '11', NULL);


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '75', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '76', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '77', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MARTES', '2023-05-18 11:00:18', '20:00:18', b'0', '2023-05-16 10:19:18', '19:00:18', '72', '11', NULL);


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '74', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '13:00:18', b'0', '2023-05-16 10:19:18', '11:00:18', '81', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '83', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'MIERCOLES', '2023-05-18 11:00:18', '20:00:18', b'0', '2023-05-16 10:19:18', '19:00:18', '91', '11', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '101', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '92', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'JUEVES', '2023-05-18 11:00:18', '19:00:18', b'0', '2023-05-16 10:19:18', '17:00:18', '102', '11', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '93', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '15:00:18', b'0', '2023-05-16 10:19:18', '13:00:18', '94', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'VIERNES', '2023-05-18 11:00:18', '17:00:18', b'0', '2023-05-16 10:19:18', '15:00:18', '55', '11', NULL);

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '09:00:18', b'0', '2023-05-16 10:19:18', '07:00:18', '93', '11', NULL);
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES  (NULL, 'SABADO', '2023-05-18 11:00:18', '11:00:18', b'0', '2023-05-16 10:19:18', '09:00:18', '94', '11', NULL);

INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('1', '1', '22', '1061');

INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('2', '1', '20', '1061');

INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:18.000000', 'oferta_academica_PIS.xls', '0', '2023-1', 'PIS');
INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:17.000000', 'oferta_academica_PIET.xls', '1', '2023-1', 'PIET');
INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:16.000000', 'oferta_academica_PIAI.xls', '2', '2023-1', 'PIAI');
