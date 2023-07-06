--Faculty
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIET', 'FACULTAD DE INGENIERIA ELECTRONICA Y DE COMUNICACIONES');
INSERT INTO `faculty` (`faculty_id`, `faculty_name`) VALUES ('FIC', 'FACULTAD DE INGENIERIA CIVIL');
--Department
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('1', 'Sistemas', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('2', 'Telecomunicaciones', 'FIET');
INSERT INTO `department` (`department_id`, `department_name`, `faculty_id`) VALUES ('3', 'Civil', 'FIC');
--Program
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color`) VALUES ('PIS', 'INGENIERIA DE SISTEMAS', '1','bg-orange');
INSERT INTO `program` (`program_id`, `name`, `department_id`, `color` ) VALUES ('PIET', 'INGENIERIA ELECTRONICA Y TELECOMUNICACIONES', '2','bg-sky');
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
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('POO', 'Programacion Orientada a Objetos', '1', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ESTR1', 'Estructura de Datos', '2',false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ESTR2', 'Estructura de Datos 2', '2', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('SISDIS', 'Sistemas Distribuidos', '4',false, '4', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('CALSOFT', 'Calidad de Software', '6', false, '2', 'PIS');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('INTRO1', 'Introduccion programacion', '1',false, '4', 'PIS');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD1', 'Bases de datos 1', '1', false, '2', 'TTM');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('BD2', 'Bases de datos 2', '1',false, '4', 'TTM');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('LEGIS', 'Legislacion Laboral', '2', false, '2', 'TTM');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('ARQUITE', 'Arquitectura', '2',false, '4', 'TTM');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1003A','Algebra Lineal', '1',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1004A','Cálculo Diferencial', '1',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1005A','Introducción a la Ingeniería', '2',false, '4', 'PIET');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1006A','Lectura y Escritura', '2',false, '4', 'PIET');

INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1007A','Cálculo  Integral', '1',false, '4', '	PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1008A','Circuitos  de corriente Directa', '1',false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1009A','Mecánica', '1',false, '4', 'PIAI');
INSERT INTO `subject` (`subject_code`, `name`, `semester`, `time_block`, `weekly_overload`, `program_id`) VALUES ('1010A','Programación Orientada a Objetos', '2',false, '4', 'PIAI');



--Environment
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 1', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 2', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 4', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 3', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 5', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'LABORATORIO', 'Edificio IPET FIET', 'Sala 6', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 201', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 101', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 102', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '40', 'SALON', 'Salon IPET FIET', 'Salon 102', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 110', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 202', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 203', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 204', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 220', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '35', 'SALON', 'Salon IPET FIET', 'Salon 222', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 231', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'SALON', 'Salon IPET FIET', 'Salon 331', 'FIET');
INSERT INTO `environment` (`environment_id`, `capacity`, `environmentType`, `location`, `name`, `faculty_id`) VALUES (NULL, '30', 'AUDITORIO', 'Edificio IPET FIET', 'Aurditorio IPET', 'FIET');

--Resource
INSERT INTO `resource` (`resource_id`, `name`, `resource_type`) VALUES (NULL, 'Video Beam', 'TECNOLOGICO');
INSERT INTO `resource` (`resource_id`, `name`, `resource_type`) VALUES (NULL, 'Televisor', 'TECNOLOGICO');
INSERT INTO `resource` (`resource_id`, `name`, `resource_type`) VALUES (NULL, 'Computador', 'TECNOLOGICO');

--Resource environment
--INSERT INTO `available_resources` (`environment_id`, `resource_id`) VALUES (1,1);


--resource course sistemas
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('1', '25', 'B', ,"4",'LABORATORIO ','POO');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('2', '13', 'A',"4",'LABORATORIO','POO');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('3', '20', 'B',"4",'asd','ESTR1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('4', '20', 'B',"4",'asd','ESTR2');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('5', '20', 'B',"4",'asd','SISDIS');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('6', '20', 'B',"4",'asd','CALSOFT');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('10', '13', 'A',"4",'SALON','INTRO1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('11', '13', 'B',"4",'SALON','INTRO1');
--resource course electronica
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('13', '20', 'B',"4",'LABORATORIO','1003A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('14', '20', 'A',"4",'AUDITORIO','1003A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('15', '20', 'C',"4",'AUDITORIO','1003A');
--resource course automatica
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('16', '20', 'B',"4",'asd','1009A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('17', '20', 'A',"4",'asd','1009A');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('18', '20', 'C',"4",'asd','1009A');
--resource course telematica
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('7', '20', 'B',"4",'asd','BD1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('8', '20', 'C',"4",'asd','BD1');
INSERT INTO course (course_id, course_capacity,course_group,remaining_hours,type_environment_required, subject_code) VALUES ('9', '20', 'A',"4",'asd','BD1');

--Resourse course-teacher
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('1', '0', '2', '1061');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('2', '0', '3', '1062');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('3', '0', '4', '1063');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('4', '0', '5', '1064');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('5', '0', '6', '1065');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('6', '0', '7', '1066');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('7', '0', '8', '1067');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('8', '0', '9', '1068');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('9', '0', '10', '1069');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('10', '0', '11', '1070');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('11', '0', '12', '1071');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('12', '0', '13', '1072');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('13', '0', '14', '1073');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('14', '0', '15', '1074');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('15', '0', '16', '1075');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('16', '0', '17', '1076');
INSERT INTO `course_teacher` (`course_teacher_id`, `teacher_category`, `course_id`, `personCode`) VALUES ('17', '0', '18', '1077');
--Resource environment
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES ('2', 'ROLE_USER');

--eventos
INSERT INTO `event` (`event_id`, `description`, `event_manager_name`, `event_name`, `event_type`, `person_code`) VALUES (NULL, 'charla iaa', 'Mr.Masso', 'Charla Inteligencia', 'OTROS', '1062');




--resource event
INSERT INTO `event` (`event_id`, `description`, `event_manager_name`, `event_name`, `event_type`, `program_id`, `teacher_code`)VALUES ('2', 'conferencia', 'conferencia', 'conferenciado', 'CONFERENCIA', 'PIS', '1061');
--resource Schedule

INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:18.000000', 'oferta_academica_PIS.xls', '0', '2023-1', 'PIS');
INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:17.000000', 'oferta_academica_PIET.xls', '1', '2023-1', 'PIET');
INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:16.000000', 'oferta_academica_PIAI.xls', '2', '2023-1', 'PIAI');

INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:18.000000', 'oferta_academica_PIS.xls', '0', '2023-1', 'PIS');
INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:17.000000', 'oferta_academica_PIET.xls', '1', '2023-1', 'PIET');
INSERT INTO `academic_offer_file` (`academic_offer_file_id`, `date_register_file`, `name_file`, `state_file`, `periodId`, `program_id`) VALUES (NULL, '2023-06-12 22:02:16.000000', 'oferta_academica_PIAI.xls', '2', '2023-1', 'PIAI');
