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

import { UtilisateurCreateUtilisateurComponent } from './utilisateur/create/utilisateur-create-utilisateur.component';
import { UtilisateurEditUtilisateurComponent } from './utilisateur/edit/utilisateur-edit-utilisateur.component';
import { UtilisateurViewUtilisateurComponent } from './utilisateur/view/utilisateur-view-utilisateur.component';
import { UtilisateurListUtilisateurComponent } from './utilisateur/list/utilisateur-list-utilisateur.component';
import { EntiteAdministrativeCreateUtilisateurComponent } from './entite-administrative/create/entite-administrative-create-utilisateur.component';
import { EntiteAdministrativeEditUtilisateurComponent } from './entite-administrative/edit/entite-administrative-edit-utilisateur.component';
import { EntiteAdministrativeViewUtilisateurComponent } from './entite-administrative/view/entite-administrative-view-utilisateur.component';
import { EntiteAdministrativeListUtilisateurComponent } from './entite-administrative/list/entite-administrative-list-utilisateur.component';
import { GroupeCreateUtilisateurComponent } from './groupe/create/groupe-create-utilisateur.component';
import { GroupeEditUtilisateurComponent } from './groupe/edit/groupe-edit-utilisateur.component';
import { GroupeViewUtilisateurComponent } from './groupe/view/groupe-view-utilisateur.component';
import { GroupeListUtilisateurComponent } from './groupe/list/groupe-list-utilisateur.component';

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

    UtilisateurCreateUtilisateurComponent,
    UtilisateurListUtilisateurComponent,
    UtilisateurViewUtilisateurComponent,
    UtilisateurEditUtilisateurComponent,
    EntiteAdministrativeCreateUtilisateurComponent,
    EntiteAdministrativeListUtilisateurComponent,
    EntiteAdministrativeViewUtilisateurComponent,
    EntiteAdministrativeEditUtilisateurComponent,
    GroupeCreateUtilisateurComponent,
    GroupeListUtilisateurComponent,
    GroupeViewUtilisateurComponent,
    GroupeEditUtilisateurComponent,
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
  UtilisateurCreateUtilisateurComponent,
  UtilisateurListUtilisateurComponent,
  UtilisateurViewUtilisateurComponent,
  UtilisateurEditUtilisateurComponent,
  EntiteAdministrativeCreateUtilisateurComponent,
  EntiteAdministrativeListUtilisateurComponent,
  EntiteAdministrativeViewUtilisateurComponent,
  EntiteAdministrativeEditUtilisateurComponent,
  GroupeCreateUtilisateurComponent,
  GroupeListUtilisateurComponent,
  GroupeViewUtilisateurComponent,
  GroupeEditUtilisateurComponent,
  ],
})
export class UserUtilisateurModule { }
