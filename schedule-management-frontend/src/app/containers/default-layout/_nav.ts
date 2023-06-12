import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  // {
  //   name: 'Dashboard',
  //   url: '/dashboard',
  //   iconComponent: { name: 'cil-speedometer' },
  //   badge: {
  //     color: 'info',
  //     text: 'NEW'
  //   }
  // },
  // {
  //   title: true,
  //   name: 'Theme'
  // },
  {
    name: 'Oferta académica',
    url: '/schedule',
    iconComponent: { name: 'cilBook' },
    children: [
      {
        name: 'Subir oferta',
        url: '/schedule/upload-oa',
        iconComponent: { name: 'cilCloudUpload' }
      },
      {
        name: 'Descargar oferta',
        url: '/schedule/view-oa',
        iconComponent: { name: 'cilCloudDownload' }
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
    name: 'Schedule',
    url: '/schedule',
    iconComponent: { name: 'cil-puzzle' },
    children: [
      {
        name: 'Create schedule',
        url: '/schedule/create'
      },
      {
        name: 'Reserve Enviroment',
        url: '/schedule/reserve'
      },
      {
        name: 'Detail Environment',
        url: '/schedule/detail'
      },
      {
        name: 'Detail Professor',
        url: '/schedule/detailprofessor'
      }
    ]
  },
  {
    name: 'Reportes',
    url: '/Reportes',
    iconComponent: { name: 'cilPrint' },
    children: [
     /* {
        name: 'Reporte por Facultad',
        url: '/reportes/Report_faculty'
      },*/
      {
        name: 'Reporte por Programa',
        url: '/reportes/Report_program'
      },
      {
        name: 'Reporte por semestre',
        url: '/reportes/Report_semestre'
      },
      {
        name: 'Reporte por Professor',
        url: '/reportes/Report_teacher'
      },
      {
        name: 'Reporte por salon',
        url: '/reportes/salon'
      }/*, {
        name: 'Componente Pruebas',
        url: '/reportes/calendario'
      }*/
    ]
  },
];
