import { INavData } from '@coreui/angular';
export const navItemsAdmin: INavData[] = [

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
