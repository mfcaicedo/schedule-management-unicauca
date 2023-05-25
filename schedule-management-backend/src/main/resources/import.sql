--Faculty
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIET', 'FACULTAD DE INGENIERIA ELECTRONICA Y DE COMUNICACIONES');
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIC', 'FACULTAD DE INGENIERIA CIVIL');
--Department
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('1', 'Sistemas', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('2', 'Telecomunicaciones', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('3', 'Civil', 'FIC');
--Program
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIS', 'INGENIERIA DE SISTEMAS', '1','bg-orange');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color` ) VALUES ('PIET', 'INGENIERIA ELECTRONICA Y TELECOMUNICACIONES', '2','bg-yellow');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIAI', 'INGENIERIA AUMOTAMICA INDISTRIAL', '1','bg-blue');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('TTM', 'TECNOLOGIA EN TELEMATICA', '2','bg-gray');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIC', 'INGENIERIA CIVIL', '3','bg-pink');
--Teacher
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1061', 'DANIEL PAZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1062', 'CLAUDIA SOFIA IDROBO CRUZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1063', 'MARTIN ALONSO MUÑOZ MEDINA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1064', 'JUAN CARLOS NARVAEZ NARVAEZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1065', 'FRANCISCO FRANCO OBANDO DIAZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1066', 'VICTOR HUGO MOSQUERA LEYTON', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1067', 'LAURA ANDREA BERMUDEZ CORDOBA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1068', 'GUSTAVO ADOLFO GOMEZ AGREDO', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1069', 'CATALINA   MUÑOZ COLLAZOS', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1070', 'MANUEL SANIN BENAVIDES PIAMBA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1071', 'KARIN   CORREA ARANA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1072', 'JESUS MAURICIO RAMIREZ VIAFARA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1073', 'MODESTO FAJARDO ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1074', 'FULVIO YESID VIVAS CANTERO', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1075', 'FABIO HERNAN REALPE MARTINEZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1076', 'JHON EDER MASSO DAZA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1077', 'RENE FABIAN ZUÑIGA MUÑOZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1078', 'FLOR DE MARIA HERNANDEZ PEREZ', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1079', 'JIMENA ADRIANA TIMANA PEÑA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1080', 'CARLOS ALBERTO ARDILA ALBARRACIN', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1081', 'RICARDO ANTONIO ZAMBRANO SEGURA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1082', 'ERWIN   MEZA VEGA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1083', 'FRANCISCO JAVIER OBANDO VIDAL', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1084', 'LISETH VIVIANA CAMPO ARCOS', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1085', 'GINETH MAGALY CERON RIOS', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1086', 'JUAN DAVID YIP HERRERA', '1');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1087', 'IVAN EDUARDO HERNANDEZ DELGADO', '3');
INSERT INTO `teacher` (`teacherCode`, `full_name`, `department_id`) VALUES ('1088', 'LISETH VIVIANA CAMPO ARCOS', '1');



--Period
INSERT INTO `period` (`periodId`, `endDate`, `initDate`, `state`) VALUES ('2022-1', '2022-06-30 23:59:59', '2022-02-01 00:00:00','FINISHED');
INSERT INTO `period` (`periodId`, `endDate`, `initDate`, `state`) VALUES ('2022-2', '2022-11-30 23:59:59', '2022-07-01 00:00:00','IN_PROGRESS');






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
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1011A','ARQUITECTURA', '2',false, '4', 'PIC');

--Environment
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('4', '30', 'SALON', 'FIET', 'Salon 231', 'FIET', '4');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('5', NULL, 'EDIFICIO', 'no aplica', 'IPET', 'FIET', NULL);
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('6', '30', 'LABORATORIO', 'IPET', 'Sala 4', 'FIET', '5');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('7', '30', 'EDIFICIO', 'IPET', 'Sala 322', 'FIET', '5');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('8', '25', 'SALON', 'IPET', 'Salon 210', 'FIET', '5');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('9', NULL, 'EDIFICIO', 'no aplica', 'Geotecnia', 'FIET', NULL);
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('10', '30', 'LABORATORIO', 'Geotecnia', 'Sala 4', 'FIET', '9');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('12', '25', 'SALON', 'Geotecnia', 'Salon 210', 'FIET', '9');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('11', '30', 'EDIFICIO', 'Geotecnia', 'Sala 322', 'FIET', '9');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('13', NULL, 'EDIFICIO', 'no aplica', 'EFIC', 'FIC', NULL);
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('14', '30', 'SALON', 'FIC', 'Salon 322', 'FIC', '13');
INSERT INTO `environment` (`id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`, `parent_id`) VALUES ('15', '30', 'LABORATORIO', 'FIC', 'Sala 003', 'FIC', '13');
;

--Resource
INSERT INTO `resource` (`id`, `name`, `resource_type`) VALUES (NULL, 'Video Beam', 'TECNOLOGICO');
INSERT INTO `resource` (`id`, `name`, `resource_type`) VALUES (NULL, 'Televisor', 'TECNOLOGICO');

--Resource environment
INSERT INTO `available_resources` (`environment_id`, `resource_id`) VALUES (1,1);


--resource course
INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('2', '25', 'sistemas', 'pruebita ', '4', '2022-1', 'POO');
--INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('2', '13', 'A', 'dad','4','2022_1', 'PO');
INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('3', '20', 'B', 'asd','4','2022-2', 'ESTR1');
INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('4', '20', 'B', 'dsfaf','4','2022-2', '1009A');
INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('5', '20', 'B', 'sdfa','4','2022-2', '1003A');
INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('6', '20', 'B', 'sadfa','4','2022-2', '1004A');
INSERT INTO course (course_id, course_capacity,course_group,description,remaining_hours, period_periodId, subject_code) VALUES ('7', '20', 'A', 'Padf','4','2022-2', '1011A');

--Resource environment
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('2', 'ROLE_USER');


--resource event
INSERT INTO `event` (`event_id`, `description`, `event_manager_name`, `event_name`, `event_type`, `program_id`, `teacher_code`)VALUES ('2', 'conferencia', 'conferencia', 'conferenciado', 'CONFERENCIA', 'PIS', '1061');
--resource Schedule
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('1', 'LUNES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:19:18', '3', '8', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('2', 'MARTES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:19:18', '6', '8', '2');

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('3', 'MIERCOLES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:00:18', '3', '6', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('4', 'JUEVES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:00:18', '4', '6', '2');


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('5', 'LUNES', '2023-05-18 11:19:18', '13:19:18', b'0', '2023-05-16 10:19:18', '05:19:18', '3', '12', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('6', 'VIERNES', '2023-05-20 11:19:18', '11:19:18', b'1', '2023-05-18 10:19:18', '04:19:18', '5', '10', '2');

---schedule civil
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('7', 'LUNES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:19:18', '3', '14', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('8', 'MARTES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:19:18', '4', '14', '2');

INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('9', 'MIERCOLES', '2023-05-18 11:19:18', '11:19:18', b'0', '2023-05-16 10:19:18', '06:00:18', '5', '14', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('10', 'JUEVES', '2023-05-20 11:19:18', '8:19:18', b'1', '2023-05-18 10:19:18', '07:00:18', '6', '14', '2');


INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('11', 'LUNES', '2023-05-18 11:19:18', '13:19:18', b'0', '2023-05-16 10:19:18', '05:19:18', '7', '14', '2');
INSERT INTO `schedule` (`id`, `day`, `ending_Date`, `ending_time`, `is_reserve`, `starting_Date`, `starting_time`, `course_id`, `environment_id`, `event_id`)VALUES ('12', 'VIERNES', '2023-05-20 11:19:18', '11:19:18', b'1', '2023-05-18 10:19:18', '04:19:18', '7', '14', '2');
