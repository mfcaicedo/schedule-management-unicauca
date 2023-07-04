import { INavData } from '@coreui/angular';


export const navItemsManager: INavData[] = [


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
    url: '/reporte',
    iconComponent: { name: 'cilPrint' },
    children: [

      {
        name: 'Reporte por Programa',
        url: '/reportes/Programa'
      },

      {
        name: 'Reporte por Docente',
        url: '/reportes/Docente'
      },
      {
        name: 'Reporte por Ambiente',
        url: '/reportes/Ambiente'
      }
    ]
  },
];

