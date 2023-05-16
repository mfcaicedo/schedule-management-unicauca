import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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
        UtilitiesModule
      ],
      exports: [
        RoomComponent
      ]

})
export class ReportesModule { }