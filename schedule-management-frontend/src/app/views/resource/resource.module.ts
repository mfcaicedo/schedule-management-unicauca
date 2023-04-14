import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResourceRoutingModule } from './resource-routing.module';

import { ReactiveFormsModule } from '@angular/forms';
import { ToshareModule } from 'src/app/views/toshare/toshare.module';
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

import { FormsModule } from '@angular/forms';
import { ResourcesComponent } from './resources/resources.component';
import { ResourcesAllComponent } from './resources-all/resources-all.component';
import { ResourcesFormComponent } from './resources-form/resources-form.component';
import { ResourcesCreateComponent } from './resources-create/resources-create.component';
import { ResourcesEditComponent } from './resources-edit/resources-edit.component';
import { EnvironmentsFromResourceComponent } from './environments-from-resource/environments-from-resource.component';


@NgModule({
  declarations: [
    ResourcesComponent,
    ResourcesAllComponent,
    ResourcesFormComponent,
    ResourcesCreateComponent,
    ResourcesEditComponent,
    EnvironmentsFromResourceComponent
  ],
  imports: [
    CommonModule,
    ResourceRoutingModule,
    ToshareModule,


    ReactiveFormsModule,
    FormsModule,
    ModalModule,

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
    UtilitiesModule
  ],
  exports: [
    ResourcesComponent
  ]


})
export class ResourceModule { }
