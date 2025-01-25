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

import { DocumentFieldCreateAdminComponent } from './document-field/create/document-field-create-admin.component';
import { DocumentFieldEditAdminComponent } from './document-field/edit/document-field-edit-admin.component';
import { DocumentFieldViewAdminComponent } from './document-field/view/document-field-view-admin.component';
import { DocumentFieldListAdminComponent } from './document-field/list/document-field-list-admin.component';
import { FieldCreateAdminComponent } from './field/create/field-create-admin.component';
import { FieldEditAdminComponent } from './field/edit/field-edit-admin.component';
import { FieldViewAdminComponent } from './field/view/field-view-admin.component';
import { FieldListAdminComponent } from './field/list/field-list-admin.component';
import { EntiteAdministrativeTypeCreateAdminComponent } from './entite-administrative-type/create/entite-administrative-type-create-admin.component';
import { EntiteAdministrativeTypeEditAdminComponent } from './entite-administrative-type/edit/entite-administrative-type-edit-admin.component';
import { EntiteAdministrativeTypeViewAdminComponent } from './entite-administrative-type/view/entite-administrative-type-view-admin.component';
import { EntiteAdministrativeTypeListAdminComponent } from './entite-administrative-type/list/entite-administrative-type-list-admin.component';
import { DocumentStateCreateAdminComponent } from './document-state/create/document-state-create-admin.component';
import { DocumentStateEditAdminComponent } from './document-state/edit/document-state-edit-admin.component';
import { DocumentStateViewAdminComponent } from './document-state/view/document-state-view-admin.component';
import { DocumentStateListAdminComponent } from './document-state/list/document-state-list-admin.component';
import { DocumentCreateAdminComponent } from './document/create/document-create-admin.component';
import { DocumentEditAdminComponent } from './document/edit/document-edit-admin.component';
import { DocumentViewAdminComponent } from './document/view/document-view-admin.component';
import { DocumentListAdminComponent } from './document/list/document-list-admin.component';
import { DocumentCategorieCreateAdminComponent } from './document-categorie/create/document-categorie-create-admin.component';
import { DocumentCategorieEditAdminComponent } from './document-categorie/edit/document-categorie-edit-admin.component';
import { DocumentCategorieViewAdminComponent } from './document-categorie/view/document-categorie-view-admin.component';
import { DocumentCategorieListAdminComponent } from './document-categorie/list/document-categorie-list-admin.component';
import { TagCreateAdminComponent } from './tag/create/tag-create-admin.component';
import { TagEditAdminComponent } from './tag/edit/tag-edit-admin.component';
import { TagViewAdminComponent } from './tag/view/tag-view-admin.component';
import { TagListAdminComponent } from './tag/list/tag-list-admin.component';
import { DocumentTypeCreateAdminComponent } from './document-type/create/document-type-create-admin.component';
import { DocumentTypeEditAdminComponent } from './document-type/edit/document-type-edit-admin.component';
import { DocumentTypeViewAdminComponent } from './document-type/view/document-type-view-admin.component';
import { DocumentTypeListAdminComponent } from './document-type/list/document-type-list-admin.component';
import { DocumentFieldStateCreateAdminComponent } from './document-field-state/create/document-field-state-create-admin.component';
import { DocumentFieldStateEditAdminComponent } from './document-field-state/edit/document-field-state-edit-admin.component';
import { DocumentFieldStateViewAdminComponent } from './document-field-state/view/document-field-state-view-admin.component';
import { DocumentFieldStateListAdminComponent } from './document-field-state/list/document-field-state-list-admin.component';

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

    DocumentFieldCreateAdminComponent,
    DocumentFieldListAdminComponent,
    DocumentFieldViewAdminComponent,
    DocumentFieldEditAdminComponent,
    FieldCreateAdminComponent,
    FieldListAdminComponent,
    FieldViewAdminComponent,
    FieldEditAdminComponent,
    EntiteAdministrativeTypeCreateAdminComponent,
    EntiteAdministrativeTypeListAdminComponent,
    EntiteAdministrativeTypeViewAdminComponent,
    EntiteAdministrativeTypeEditAdminComponent,
    DocumentStateCreateAdminComponent,
    DocumentStateListAdminComponent,
    DocumentStateViewAdminComponent,
    DocumentStateEditAdminComponent,
    DocumentCreateAdminComponent,
    DocumentListAdminComponent,
    DocumentViewAdminComponent,
    DocumentEditAdminComponent,
    DocumentCategorieCreateAdminComponent,
    DocumentCategorieListAdminComponent,
    DocumentCategorieViewAdminComponent,
    DocumentCategorieEditAdminComponent,
    TagCreateAdminComponent,
    TagListAdminComponent,
    TagViewAdminComponent,
    TagEditAdminComponent,
    DocumentTypeCreateAdminComponent,
    DocumentTypeListAdminComponent,
    DocumentTypeViewAdminComponent,
    DocumentTypeEditAdminComponent,
    DocumentFieldStateCreateAdminComponent,
    DocumentFieldStateListAdminComponent,
    DocumentFieldStateViewAdminComponent,
    DocumentFieldStateEditAdminComponent,
  ],
  imports: [
      FileUploadModule,
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
  DocumentFieldCreateAdminComponent,
  DocumentFieldListAdminComponent,
  DocumentFieldViewAdminComponent,
  DocumentFieldEditAdminComponent,
  FieldCreateAdminComponent,
  FieldListAdminComponent,
  FieldViewAdminComponent,
  FieldEditAdminComponent,
  EntiteAdministrativeTypeCreateAdminComponent,
  EntiteAdministrativeTypeListAdminComponent,
  EntiteAdministrativeTypeViewAdminComponent,
  EntiteAdministrativeTypeEditAdminComponent,
  DocumentStateCreateAdminComponent,
  DocumentStateListAdminComponent,
  DocumentStateViewAdminComponent,
  DocumentStateEditAdminComponent,
  DocumentCreateAdminComponent,
  DocumentListAdminComponent,
  DocumentViewAdminComponent,
  DocumentEditAdminComponent,
  DocumentCategorieCreateAdminComponent,
  DocumentCategorieListAdminComponent,
  DocumentCategorieViewAdminComponent,
  DocumentCategorieEditAdminComponent,
  TagCreateAdminComponent,
  TagListAdminComponent,
  TagViewAdminComponent,
  TagEditAdminComponent,
  DocumentTypeCreateAdminComponent,
  DocumentTypeListAdminComponent,
  DocumentTypeViewAdminComponent,
  DocumentTypeEditAdminComponent,
  DocumentFieldStateCreateAdminComponent,
  DocumentFieldStateListAdminComponent,
  DocumentFieldStateViewAdminComponent,
  DocumentFieldStateEditAdminComponent,
  ],
})
export class DocAdminModule { }
