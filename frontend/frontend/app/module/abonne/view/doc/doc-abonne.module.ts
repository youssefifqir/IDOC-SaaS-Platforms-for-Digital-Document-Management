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

import { DocumentFieldCreateAbonneComponent } from './document-field/create/document-field-create-abonne.component';
import { DocumentFieldEditAbonneComponent } from './document-field/edit/document-field-edit-abonne.component';
import { DocumentFieldViewAbonneComponent } from './document-field/view/document-field-view-abonne.component';
import { DocumentFieldListAbonneComponent } from './document-field/list/document-field-list-abonne.component';
import { FieldCreateAbonneComponent } from './field/create/field-create-abonne.component';
import { FieldEditAbonneComponent } from './field/edit/field-edit-abonne.component';
import { FieldViewAbonneComponent } from './field/view/field-view-abonne.component';
import { FieldListAbonneComponent } from './field/list/field-list-abonne.component';
import { EntiteAdministrativeTypeCreateAbonneComponent } from './entite-administrative-type/create/entite-administrative-type-create-abonne.component';
import { EntiteAdministrativeTypeEditAbonneComponent } from './entite-administrative-type/edit/entite-administrative-type-edit-abonne.component';
import { EntiteAdministrativeTypeViewAbonneComponent } from './entite-administrative-type/view/entite-administrative-type-view-abonne.component';
import { EntiteAdministrativeTypeListAbonneComponent } from './entite-administrative-type/list/entite-administrative-type-list-abonne.component';
import { DocumentStateCreateAbonneComponent } from './document-state/create/document-state-create-abonne.component';
import { DocumentStateEditAbonneComponent } from './document-state/edit/document-state-edit-abonne.component';
import { DocumentStateViewAbonneComponent } from './document-state/view/document-state-view-abonne.component';
import { DocumentStateListAbonneComponent } from './document-state/list/document-state-list-abonne.component';
import { DocumentCreateAbonneComponent } from './document/create/document-create-abonne.component';
import { DocumentEditAbonneComponent } from './document/edit/document-edit-abonne.component';
import { DocumentViewAbonneComponent } from './document/view/document-view-abonne.component';
import { DocumentListAbonneComponent } from './document/list/document-list-abonne.component';
import { DocumentCategorieCreateAbonneComponent } from './document-categorie/create/document-categorie-create-abonne.component';
import { DocumentCategorieEditAbonneComponent } from './document-categorie/edit/document-categorie-edit-abonne.component';
import { DocumentCategorieViewAbonneComponent } from './document-categorie/view/document-categorie-view-abonne.component';
import { DocumentCategorieListAbonneComponent } from './document-categorie/list/document-categorie-list-abonne.component';
import { TagCreateAbonneComponent } from './tag/create/tag-create-abonne.component';
import { TagEditAbonneComponent } from './tag/edit/tag-edit-abonne.component';
import { TagViewAbonneComponent } from './tag/view/tag-view-abonne.component';
import { TagListAbonneComponent } from './tag/list/tag-list-abonne.component';
import { DocumentTypeCreateAbonneComponent } from './document-type/create/document-type-create-abonne.component';
import { DocumentTypeEditAbonneComponent } from './document-type/edit/document-type-edit-abonne.component';
import { DocumentTypeViewAbonneComponent } from './document-type/view/document-type-view-abonne.component';
import { DocumentTypeListAbonneComponent } from './document-type/list/document-type-list-abonne.component';
import { DocumentFieldStateCreateAbonneComponent } from './document-field-state/create/document-field-state-create-abonne.component';
import { DocumentFieldStateEditAbonneComponent } from './document-field-state/edit/document-field-state-edit-abonne.component';
import { DocumentFieldStateViewAbonneComponent } from './document-field-state/view/document-field-state-view-abonne.component';
import { DocumentFieldStateListAbonneComponent } from './document-field-state/list/document-field-state-list-abonne.component';

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
import {DocAdminModule} from "../../../admin/view/doc/doc-admin.module";



@NgModule({
  declarations: [

    DocumentFieldCreateAbonneComponent,
    DocumentFieldListAbonneComponent,
    DocumentFieldViewAbonneComponent,
    DocumentFieldEditAbonneComponent,
    FieldCreateAbonneComponent,
    FieldListAbonneComponent,
    FieldViewAbonneComponent,
    FieldEditAbonneComponent,
    EntiteAdministrativeTypeCreateAbonneComponent,
    EntiteAdministrativeTypeListAbonneComponent,
    EntiteAdministrativeTypeViewAbonneComponent,
    EntiteAdministrativeTypeEditAbonneComponent,
    DocumentStateCreateAbonneComponent,
    DocumentStateListAbonneComponent,
    DocumentStateViewAbonneComponent,
    DocumentStateEditAbonneComponent,
    DocumentCreateAbonneComponent,
    DocumentListAbonneComponent,
    DocumentViewAbonneComponent,
    DocumentEditAbonneComponent,
    DocumentCategorieCreateAbonneComponent,
    DocumentCategorieListAbonneComponent,
    DocumentCategorieViewAbonneComponent,
    DocumentCategorieEditAbonneComponent,
    TagCreateAbonneComponent,
    TagListAbonneComponent,
    TagViewAbonneComponent,
    TagEditAbonneComponent,
    DocumentTypeCreateAbonneComponent,
    DocumentTypeListAbonneComponent,
    DocumentTypeViewAbonneComponent,
    DocumentTypeEditAbonneComponent,
    DocumentFieldStateCreateAbonneComponent,
    DocumentFieldStateListAbonneComponent,
    DocumentFieldStateViewAbonneComponent,
    DocumentFieldStateEditAbonneComponent,
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
        DocAdminModule,


    ],
  exports: [
  DocumentFieldCreateAbonneComponent,
  DocumentFieldListAbonneComponent,
  DocumentFieldViewAbonneComponent,
  DocumentFieldEditAbonneComponent,
  FieldCreateAbonneComponent,
  FieldListAbonneComponent,
  FieldViewAbonneComponent,
  FieldEditAbonneComponent,
  EntiteAdministrativeTypeCreateAbonneComponent,
  EntiteAdministrativeTypeListAbonneComponent,
  EntiteAdministrativeTypeViewAbonneComponent,
  EntiteAdministrativeTypeEditAbonneComponent,
  DocumentStateCreateAbonneComponent,
  DocumentStateListAbonneComponent,
  DocumentStateViewAbonneComponent,
  DocumentStateEditAbonneComponent,
  DocumentCreateAbonneComponent,
  DocumentListAbonneComponent,
  DocumentViewAbonneComponent,
  DocumentEditAbonneComponent,
  DocumentCategorieCreateAbonneComponent,
  DocumentCategorieListAbonneComponent,
  DocumentCategorieViewAbonneComponent,
  DocumentCategorieEditAbonneComponent,
  TagCreateAbonneComponent,
  TagListAbonneComponent,
  TagViewAbonneComponent,
  TagEditAbonneComponent,
  DocumentTypeCreateAbonneComponent,
  DocumentTypeListAbonneComponent,
  DocumentTypeViewAbonneComponent,
  DocumentTypeEditAbonneComponent,
  DocumentFieldStateCreateAbonneComponent,
  DocumentFieldStateListAbonneComponent,
  DocumentFieldStateViewAbonneComponent,
  DocumentFieldStateEditAbonneComponent,
  ],
})
export class DocAbonneModule { }
