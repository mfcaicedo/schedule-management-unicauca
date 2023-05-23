import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxSpinnerModule } from 'ngx-spinner';

import { TeacherRoutingModule } from './teacher-routing.module';
import { TeachersComponent } from './teachers/teacher-detail.component';

import { ReactiveFormsModule } from '@angular/forms';
// CoreUI Modules
import {
  AccordionModule,
  BadgeModule,
  BreadcrumbModule,
  ButtonModule,
  CardModule,
  CarouselModule,
  CollapseModule,
  DropdownModule,
  FormModule,
  GridModule,
  ListGroupModule,
  NavModule,
  PaginationModule,
  PlaceholderModule,
  PopoverModule,
  ProgressModule,
  SharedModule,
  SpinnerModule,
  TableModule,
  TabsModule,
  TooltipModule,
  UtilitiesModule,
  ModalModule

} from '@coreui/angular';

import { IconModule } from '@coreui/icons-angular';
// import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { TeacherUploadComponent } from './teacher-upload/teacher-upload.component'

import { FormsModule } from '@angular/forms';

import { ResourceModule } from '../resource/resource.module';
import { ToshareModule } from 'src/app/views/toshare/toshare.module';

@NgModule({
  declarations: [
    TeachersComponent,
    TeacherUploadComponent
  ],

  imports: [
    CommonModule,
    TeacherRoutingModule,

    ReactiveFormsModule,
    FormsModule,
    ModalModule,
    IconModule,
    ResourceModule,
    ToshareModule,


    AccordionModule,
    BadgeModule,
    BreadcrumbModule,
    ButtonModule,
    CardModule,
    CarouselModule,
    CollapseModule,
    DropdownModule,
    FormModule,
    GridModule,
    ListGroupModule,
    NavModule,
    PaginationModule,
    PlaceholderModule,
    PopoverModule,
    ProgressModule,

    SpinnerModule,
    TableModule,
    TabsModule,
    TooltipModule,
    UtilitiesModule,
    NgxSpinnerModule,
  ]
})
export class TeacherModule { }
