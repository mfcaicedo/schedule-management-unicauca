import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReportesRoutingModule } from './reportes-routing.module';

import { ReactiveFormsModule } from '@angular/forms';


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
  ModalModule,
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
} from '@coreui/angular';

import { FormsModule } from '@angular/forms';
import { FullCalendarModule } from '@fullcalendar/angular';
import { RoomComponent } from './room/room.component';


@NgModule({
    declarations: [
        RoomComponent
    ],
    imports: [
        CommonModule,
        ReportesRoutingModule,
    
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
        UtilitiesModule,
        FullCalendarModule
      ],
      exports: [
        RoomComponent
      ]

})
export class ReportesModule { }