import { INavData } from '@coreui/angular';
import { IconComponent } from '@coreui/icons-angular';
import { url } from 'inspector';

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
    name: 'Profesores',
    url: '/teacher',
    iconComponent: { name: 'cil-people' },
    children: [
      {
        name: 'Cargar profesores',
        url: '/teacher/upload-teacher',
        iconComponent: { name: 'cilCloudUpload' }
      },
      {
        name: 'Ver profesores',
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
        name: 'Detail Environment',
        url: '/schedule/detail'
      },
      {
        name: 'Detail Professor',
        url: '/schedule/detailprofessor'
      }
    ]
  },
];
