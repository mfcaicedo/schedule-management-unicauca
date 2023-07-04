import { INavData } from '@coreui/angular';
import {AuthorityMap} from 'src/app/models/AuthorityMap.model';

export const navItems: INavData[] = [

  {
    name: 'Oferta académica',
    url: '/schedule/oa',
    iconComponent: { name: 'cilBook' },

    children: [
      {
        name: 'Subir oferta',
        url: '/schedule/oa/upload-oa',
        iconComponent: { name: 'cilCloudUpload' },

      },
      {
        name: 'Ver archivos',
        url: '/schedule/oa/view-files-oa',
        iconComponent: { name: 'cil-file' }
      }
    ]
  },
  {
    name: 'Ambientes',
    url: '/environment',
    iconComponent: { name: 'cilBuilding' },
    children: [
      {
        name: 'Crear ambiente',
        url: '/environment/create',
        iconComponent: { name: 'cilPlus' }
      },
      {
        name: 'Cargar ambientes',
        url: '/environment/upload-env',
        iconComponent: { name: 'cilCloudUpload' }
      },
      {
        name: 'Ver ambientes',
        url: '/environment/all',
        iconComponent: { name: 'cilMagnifyingGlass' }
      },
    ]
  },
  {
    name: 'Recursos',
    url: '/resource',
    iconComponent: { name: 'cilDevices' },
    children: [
      {
        name: 'Crear recurso',
        url: '/resource/create',
        iconComponent: { name: 'cilPlus' }
      },
      {
        name: 'Ver recursos',
        url: '/resource/all',
        iconComponent: { name: 'cilMagnifyingGlass' }
        // iconComponent: { name: 'cilMediaPlay' }
      },
    ]
  },
  {
    name: 'Docentes',
    url: '/teacher',
    iconComponent: { name: 'cil-people' },
    children: [
      {
        name: 'Cargar docentes',
        url: '/teacher/upload-teacher',
        iconComponent: { name: 'cilCloudUpload' }
      },
      {
        name: 'Ver docentes',
        url: '/teacher/all',
        iconComponent: { name: 'cilMagnifyingGlass' }
      },
    ]
  },
  {
    name: 'Asignaturas',
    url: '/subject',
    iconComponent: { name: 'cilNotes' },
    children: [
      {
        name: 'Cargar asignaturas',
        url: '/subject/upload-sub',
        iconComponent: { name: 'cilCloudUpload' }
      },
      {
        name: 'Ver asignaturas',
        url: '/subject/all',
        iconComponent: { name: 'cilMagnifyingGlass' }
      },
    ]
  },
  {
    name: 'Horarios',
    url: '/schedule',
    iconComponent: { name: 'cil-puzzle' },
    children: [
      {
        name: 'Crear horario',
        url: '/schedule/create'
      },
      {
        name: 'Reservar ambientes',
        url: '/schedule/reserve'
      },
      {
        name: 'Consultar Reservas',
        url: '/schedule/consultreserve'
      },
      {
        name: 'Ver ambientes',
        url: '/schedule/detail'
      },
      {
        name: 'Ver docentes',
        url: '/schedule/detailprofessor'
      }
    ]
  },
  {
    name: 'Reportes',
    url: '/reporte',
    iconComponent: { name: 'cilPrint' },
    children: [
     /* {
        name: 'Reporte por Facultad',

        url: '/reportes/Report_faculty'
      },*/

      {
        name: 'Reporte por Programa',
        url: '/reportes/Programa'
      },
      {
        name: 'Reporte por Semestre',
        url: '/reportes/Semestre'
      },
      {
        name: 'Reporte por Docente',
        url: '/reportes/Docente'
      },
      {
        name: 'Reporte por Ambiente',
        url: '/reportes/Ambiente'
      }/*, {
        name: 'Componente Pruebas',
        url: '/reportes/calendario'
      }*/
    ]
  },
];

export const authorityMapping: AuthorityMap[] = [
  { url: '/schedule/oa', authority: ['ROLE_ACADEMIC_MANAGER' ]},
  { url: '/schedule/oa/upload-oa', authority: ['ROLE_ACADEMIC_MANAGER' ] },
  { url: '/schedule/oa/view-files-oa', authority: ['ROLE_ACADEMIC_MANAGER'] },
  // { url: '/environment', authority: ['ROLE_SCHEDULE_MANAGER' ] },
  { url: '/environment/create', authority: ['ROLE_SCHEDULE_MANAGER' ] },
  { url: '/environment/upload-env', authority: ['ROLE_SCHEDULE_MANAGER' ] },
  { url: '/environment/all', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER' ] },
  // { url: '/resource', authority: ['ROLE_SCHEDULE_MANAGER' ] },
  { url: '/resource/create', authority: ['ROLE_SCHEDULE_MANAGER' ] },
  { url: '/resource/all', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER' ] },
  // { url: '/teacher', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER' ] },
  { url: '/teacher/upload-teacher', authority: ['ROLE_ACADEMIC_MANAGER' ] },
  { url: '/teacher/all', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER' ] },
  { url: '/subject', authority: ['ROLE_ACADEMIC_MANAGER' ] },
  { url: '/subject/upload-sub', authority: ['ROLE_ACADEMIC_MANAGER' ] },
  { url: '/subject/all', authority: ['ROLE_ACADEMIC_MANAGER' ,'ROLE_SCHEDULE_MANAGER'] },
  { url: '/schedule', authority: ['ROLE_SCHEDULE_MANAGER'] },
  { url: '/schedule/create', authority: ['ROLE_SCHEDULE_MANAGER'] },
  { url: '/schedule/reserve', authority: ['ROLE_SCHEDULE_MANAGER'] },
  { url: '/schedule/detail', authority: ['ROLE_ACADEMIC_MANAGER' ,'ROLE_SCHEDULE_MANAGER'] },
  { url: '/schedule/detailprofessor', authority: ['ROLE_ACADEMIC_MANAGER' ,'ROLE_SCHEDULE_MANAGER'] },
  { url: '/schedule/all', authority: ['ROLE_SCHEDULE_MANAGER'] },
  { url: '/reportes', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER'] },
  { url: '/reportes/Programa', authority: ['ROLE_SCHEDULE_MANAGER'] },
  { url: '/reportes/Semestre', authority: ['ROLE_ACADEMIC_MANAGER'] },
  { url: '/reportes/Docente', authority: ['ROLE_ACADEMIC_MANAGER'] },
  { url: '/reportes/Ambiente', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER'] },
  { url: '/reportes/all', authority: ['ROLE_ACADEMIC_MANAGER','ROLE_SCHEDULE_MANAGER'] },
];


