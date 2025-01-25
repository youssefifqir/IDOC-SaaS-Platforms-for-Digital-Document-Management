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

import { DocumentFieldCreateUtilisateurComponent } from './document-field/create/document-field-create-utilisateur.component';
import { DocumentFieldEditUtilisateurComponent } from './document-field/edit/document-field-edit-utilisateur.component';
import { DocumentFieldViewUtilisateurComponent } from './document-field/view/document-field-view-utilisateur.component';
import { DocumentFieldListUtilisateurComponent } from './document-field/list/document-field-list-utilisateur.component';
import { FieldCreateUtilisateurComponent } from './field/create/field-create-utilisateur.component';
import { FieldEditUtilisateurComponent } from './field/edit/field-edit-utilisateur.component';
import { FieldViewUtilisateurComponent } from './field/view/field-view-utilisateur.component';
import { FieldListUtilisateurComponent } from './field/list/field-list-utilisateur.component';
import { EntiteAdministrativeTypeCreateUtilisateurComponent } from './entite-administrative-type/create/entite-administrative-type-create-utilisateur.component';
import { EntiteAdministrativeTypeEditUtilisateurComponent } from './entite-administrative-type/edit/entite-administrative-type-edit-utilisateur.component';
import { EntiteAdministrativeTypeViewUtilisateurComponent } from './entite-administrative-type/view/entite-administrative-type-view-utilisateur.component';
import { EntiteAdministrativeTypeListUtilisateurComponent } from './entite-administrative-type/list/entite-administrative-type-list-utilisateur.component';
import { DocumentStateCreateUtilisateurComponent } from './document-state/create/document-state-create-utilisateur.component';
import { DocumentStateEditUtilisateurComponent } from './document-state/edit/document-state-edit-utilisateur.component';
import { DocumentStateViewUtilisateurComponent } from './document-state/view/document-state-view-utilisateur.component';
import { DocumentStateListUtilisateurComponent } from './document-state/list/document-state-list-utilisateur.component';
import { DocumentCreateUtilisateurComponent } from './document/create/document-create-utilisateur.component';
import { DocumentEditUtilisateurComponent } from './document/edit/document-edit-utilisateur.component';
import { DocumentViewUtilisateurComponent } from './document/view/document-view-utilisateur.component';
import { DocumentListUtilisateurComponent } from './document/list/document-list-utilisateur.component';
import { DocumentCategorieCreateUtilisateurComponent } from './document-categorie/create/document-categorie-create-utilisateur.component';
import { DocumentCategorieEditUtilisateurComponent } from './document-categorie/edit/document-categorie-edit-utilisateur.component';
import { DocumentCategorieViewUtilisateurComponent } from './document-categorie/view/document-categorie-view-utilisateur.component';
import { DocumentCategorieListUtilisateurComponent } from './document-categorie/list/document-categorie-list-utilisateur.component';
import { TagCreateUtilisateurComponent } from './tag/create/tag-create-utilisateur.component';
import { TagEditUtilisateurComponent } from './tag/edit/tag-edit-utilisateur.component';
import { TagViewUtilisateurComponent } from './tag/view/tag-view-utilisateur.component';
import { TagListUtilisateurComponent } from './tag/list/tag-list-utilisateur.component';
import { DocumentTypeCreateUtilisateurComponent } from './document-type/create/document-type-create-utilisateur.component';
import { DocumentTypeEditUtilisateurComponent } from './document-type/edit/document-type-edit-utilisateur.component';
import { DocumentTypeViewUtilisateurComponent } from './document-type/view/document-type-view-utilisateur.component';
import { DocumentTypeListUtilisateurComponent } from './document-type/list/document-type-list-utilisateur.component';
import { DocumentFieldStateCreateUtilisateurComponent } from './document-field-state/create/document-field-state-create-utilisateur.component';
import { DocumentFieldStateEditUtilisateurComponent } from './document-field-state/edit/document-field-state-edit-utilisateur.component';
import { DocumentFieldStateViewUtilisateurComponent } from './document-field-state/view/document-field-state-view-utilisateur.component';
import { DocumentFieldStateListUtilisateurComponent } from './document-field-state/list/document-field-state-list-utilisateur.component';

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

    DocumentFieldCreateUtilisateurComponent,
    DocumentFieldListUtilisateurComponent,
    DocumentFieldViewUtilisateurComponent,
    DocumentFieldEditUtilisateurComponent,
    FieldCreateUtilisateurComponent,
    FieldListUtilisateurComponent,
    FieldViewUtilisateurComponent,
    FieldEditUtilisateurComponent,
    EntiteAdministrativeTypeCreateUtilisateurComponent,
    EntiteAdministrativeTypeListUtilisateurComponent,
    EntiteAdministrativeTypeViewUtilisateurComponent,
    EntiteAdministrativeTypeEditUtilisateurComponent,
    DocumentStateCreateUtilisateurComponent,
    DocumentStateListUtilisateurComponent,
    DocumentStateViewUtilisateurComponent,
    DocumentStateEditUtilisateurComponent,
    DocumentCreateUtilisateurComponent,
    DocumentListUtilisateurComponent,
    DocumentViewUtilisateurComponent,
    DocumentEditUtilisateurComponent,
    DocumentCategorieCreateUtilisateurComponent,
    DocumentCategorieListUtilisateurComponent,
    DocumentCategorieViewUtilisateurComponent,
    DocumentCategorieEditUtilisateurComponent,
    TagCreateUtilisateurComponent,
    TagListUtilisateurComponent,
    TagViewUtilisateurComponent,
    TagEditUtilisateurComponent,
    DocumentTypeCreateUtilisateurComponent,
    DocumentTypeListUtilisateurComponent,
    DocumentTypeViewUtilisateurComponent,
    DocumentTypeEditUtilisateurComponent,
    DocumentFieldStateCreateUtilisateurComponent,
    DocumentFieldStateListUtilisateurComponent,
    DocumentFieldStateViewUtilisateurComponent,
    DocumentFieldStateEditUtilisateurComponent,
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
  DocumentFieldCreateUtilisateurComponent,
  DocumentFieldListUtilisateurComponent,
  DocumentFieldViewUtilisateurComponent,
  DocumentFieldEditUtilisateurComponent,
  FieldCreateUtilisateurComponent,
  FieldListUtilisateurComponent,
  FieldViewUtilisateurComponent,
  FieldEditUtilisateurComponent,
  EntiteAdministrativeTypeCreateUtilisateurComponent,
  EntiteAdministrativeTypeListUtilisateurComponent,
  EntiteAdministrativeTypeViewUtilisateurComponent,
  EntiteAdministrativeTypeEditUtilisateurComponent,
  DocumentStateCreateUtilisateurComponent,
  DocumentStateListUtilisateurComponent,
  DocumentStateViewUtilisateurComponent,
  DocumentStateEditUtilisateurComponent,
  DocumentCreateUtilisateurComponent,
  DocumentListUtilisateurComponent,
  DocumentViewUtilisateurComponent,
  DocumentEditUtilisateurComponent,
  DocumentCategorieCreateUtilisateurComponent,
  DocumentCategorieListUtilisateurComponent,
  DocumentCategorieViewUtilisateurComponent,
  DocumentCategorieEditUtilisateurComponent,
  TagCreateUtilisateurComponent,
  TagListUtilisateurComponent,
  TagViewUtilisateurComponent,
  TagEditUtilisateurComponent,
  DocumentTypeCreateUtilisateurComponent,
  DocumentTypeListUtilisateurComponent,
  DocumentTypeViewUtilisateurComponent,
  DocumentTypeEditUtilisateurComponent,
  DocumentFieldStateCreateUtilisateurComponent,
  DocumentFieldStateListUtilisateurComponent,
  DocumentFieldStateViewUtilisateurComponent,
  DocumentFieldStateEditUtilisateurComponent,
  ],
})
export class DocUtilisateurModule { }
