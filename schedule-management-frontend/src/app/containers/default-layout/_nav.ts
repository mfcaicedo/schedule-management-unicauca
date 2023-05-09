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
    name:'Ambientes',
    url:'/environment',
    iconComponent:{name:'cilBuilding'},
    children:[
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
];
