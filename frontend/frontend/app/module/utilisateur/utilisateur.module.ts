import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { LoginUtilisateurComponent } from './login-utilisateur/login-utilisateur.component';
import { RegisterUtilisateurComponent } from './register-utilisateur/register-utilisateur.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { DocUtilisateurModule } from './view/doc/doc-utilisateur.module';
import { DocUtilisateurRoutingModule } from './view/doc/doc-utilisateur-routing.module';
import { AbonneUtilisateurModule } from './view/abonne/abonne-utilisateur.module';
import { AbonneUtilisateurRoutingModule } from './view/abonne/abonne-utilisateur-routing.module';
import { PurchaseUtilisateurModule } from './view/purchase/purchase-utilisateur.module';
import { PurchaseUtilisateurRoutingModule } from './view/purchase/purchase-utilisateur-routing.module';
import { UserUtilisateurModule } from './view/user/user-utilisateur.module';
import { UserUtilisateurRoutingModule } from './view/user/user-utilisateur-routing.module';
import { ReferetielUtilisateurModule } from './view/referetiel/referetiel-utilisateur.module';
import { ReferetielUtilisateurRoutingModule } from './view/referetiel/referetiel-utilisateur-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginUtilisateurComponent,
   RegisterUtilisateurComponent
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
  DocUtilisateurModule,
  DocUtilisateurRoutingModule,
  AbonneUtilisateurModule,
  AbonneUtilisateurRoutingModule,
  PurchaseUtilisateurModule,
  PurchaseUtilisateurRoutingModule,
  UserUtilisateurModule,
  UserUtilisateurRoutingModule,
  ReferetielUtilisateurModule,
  ReferetielUtilisateurRoutingModule,
  SecurityModule,
  SecurityRoutingModule
  ],
  exports: [
  LoginUtilisateurComponent,
  RegisterUtilisateurComponent,

    DocUtilisateurModule,
    AbonneUtilisateurModule,
    PurchaseUtilisateurModule,
    UserUtilisateurModule,
    ReferetielUtilisateurModule,
  SecurityModule
  ],

})
export class UtilisateurModule { }
