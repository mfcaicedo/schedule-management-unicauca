import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnvironmentRoutingModule } from './environment-routing.module';
import { EnvironmentsComponent } from './environments/environments.component';
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

  SpinnerModule,
  TableModule,
  TabsModule,
  TooltipModule,
  UtilitiesModule,
  ModalModule

} from '@coreui/angular';

import { IconModule } from '@coreui/icons-angular';
import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { EnvironmentFormComponent } from './environment-form/environment-form.component';
import { EnvironmentDetailComponent } from './environment-detail/environment-detail.component';
import { EnvironmentEditComponent } from './environment-edit/environment-edit.component';
import { FormsModule } from '@angular/forms';

import { ResourceModule } from '../resource/resource.module';
import { ToshareModule } from 'src/app/views/toshare/toshare.module';

@NgModule({
  declarations: [
    EnvironmentsComponent,
    EnvironmentFormComponent,
    EnvironmentDetailComponent,
    EnvironmentEditComponent


  ],
  imports: [
    CommonModule,
    EnvironmentRoutingModule,

    ReactiveFormsModule,
    FormsModule,
    ModalModule,

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
    UtilitiesModule
  ]
})
export class EnvironmentModule { }
