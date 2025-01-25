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

import { UtilisateurCreateAdminComponent } from './utilisateur/create/utilisateur-create-admin.component';
import { UtilisateurEditAdminComponent } from './utilisateur/edit/utilisateur-edit-admin.component';
import { UtilisateurViewAdminComponent } from './utilisateur/view/utilisateur-view-admin.component';
import { UtilisateurListAdminComponent } from './utilisateur/list/utilisateur-list-admin.component';
import { EntiteAdministrativeCreateAdminComponent } from './entite-administrative/create/entite-administrative-create-admin.component';
import { EntiteAdministrativeEditAdminComponent } from './entite-administrative/edit/entite-administrative-edit-admin.component';
import { EntiteAdministrativeViewAdminComponent } from './entite-administrative/view/entite-administrative-view-admin.component';
import { EntiteAdministrativeListAdminComponent } from './entite-administrative/list/entite-administrative-list-admin.component';
import { GroupeCreateAdminComponent } from './groupe/create/groupe-create-admin.component';
import { GroupeEditAdminComponent } from './groupe/edit/groupe-edit-admin.component';
import { GroupeViewAdminComponent } from './groupe/view/groupe-view-admin.component';
import { GroupeListAdminComponent } from './groupe/list/groupe-list-admin.component';

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

    UtilisateurCreateAdminComponent,
    UtilisateurListAdminComponent,
    UtilisateurViewAdminComponent,
    UtilisateurEditAdminComponent,
    EntiteAdministrativeCreateAdminComponent,
    EntiteAdministrativeListAdminComponent,
    EntiteAdministrativeViewAdminComponent,
    EntiteAdministrativeEditAdminComponent,
    GroupeCreateAdminComponent,
    GroupeListAdminComponent,
    GroupeViewAdminComponent,
    GroupeEditAdminComponent,
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
  UtilisateurCreateAdminComponent,
  UtilisateurListAdminComponent,
  UtilisateurViewAdminComponent,
  UtilisateurEditAdminComponent,
  EntiteAdministrativeCreateAdminComponent,
  EntiteAdministrativeListAdminComponent,
  EntiteAdministrativeViewAdminComponent,
  EntiteAdministrativeEditAdminComponent,
  GroupeCreateAdminComponent,
  GroupeListAdminComponent,
  GroupeViewAdminComponent,
  GroupeEditAdminComponent,
  ],
})
export class UserAdminModule { }
