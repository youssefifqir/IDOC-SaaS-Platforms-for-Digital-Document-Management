import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";

import { AcessShareCreateAdminComponent } from './acess-share/create/acess-share-create-admin.component';
import { AcessShareEditAdminComponent } from './acess-share/edit/acess-share-edit-admin.component';
import { AcessShareViewAdminComponent } from './acess-share/view/acess-share-view-admin.component';
import { AcessShareListAdminComponent } from './acess-share/list/acess-share-list-admin.component';
import { AcessManagementCreateAdminComponent } from './acess-management/create/acess-management-create-admin.component';
import { AcessManagementEditAdminComponent } from './acess-management/edit/acess-management-edit-admin.component';
import { AcessManagementViewAdminComponent } from './acess-management/view/acess-management-view-admin.component';
import { AcessManagementListAdminComponent } from './acess-management/list/acess-management-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    AcessShareCreateAdminComponent,
    AcessShareListAdminComponent,
    AcessShareViewAdminComponent,
    AcessShareEditAdminComponent,
    AcessManagementCreateAdminComponent,
    AcessManagementListAdminComponent,
    AcessManagementViewAdminComponent,
    AcessManagementEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,


  ],
  exports: [
  AcessShareCreateAdminComponent,
  AcessShareListAdminComponent,
  AcessShareViewAdminComponent,
  AcessShareEditAdminComponent,
  AcessManagementCreateAdminComponent,
  AcessManagementListAdminComponent,
  AcessManagementViewAdminComponent,
  AcessManagementEditAdminComponent,
  ],
})
export class ReferetielAdminModule { }
