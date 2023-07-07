import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxSpinnerModule } from 'ngx-spinner';
import { EnvironmentRoutingModule } from './environment-routing.module';
import { EnvironmentsComponent } from './environments/environments.component';
import { ReactiveFormsModule } from '@angular/forms';
import { IconModule } from '@coreui/icons-angular';
import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { EnvironmentFormComponent } from './environment-form/environment-form.component';
import { EnvironmentDetailComponent } from './environment-detail/environment-detail.component';
import { EnvironmentEditComponent } from './environment-edit/environment-edit.component';
import { EnvironmentUploadComponent } from './environment-upload/environment-upload.component'
import { EnvironmentDeleteComponent } from './environment-delete/environment-delete.component';
import { ResourceModule } from '../resource/resource.module';
import { ToshareModule } from 'src/app/views/toshare/toshare.module';
import { FormsModule } from '@angular/forms';
import { NgxFileDropModule } from 'ngx-file-drop';

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

@NgModule({
  declarations: [
    EnvironmentsComponent,
    EnvironmentFormComponent,
    EnvironmentDetailComponent,
    EnvironmentUploadComponent,
    EnvironmentEditComponent,
    EnvironmentDeleteComponent
  ],
  imports: [
    CommonModule,
    EnvironmentRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    FormsModule,
    ModalModule,
    IconModule,
    ResourceModule,
    ToshareModule,
    NgxFileDropModule,
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
  ],
  exports:[
    EnvironmentsComponent
  ]
})
export class EnvironmentModule { }
