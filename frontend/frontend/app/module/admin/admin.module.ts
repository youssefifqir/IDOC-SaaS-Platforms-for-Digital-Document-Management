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
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
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

import { DocAdminModule } from './view/doc/doc-admin.module';
import { DocAdminRoutingModule } from './view/doc/doc-admin-routing.module';
import { AbonneAdminModule } from './view/abonne/abonne-admin.module';
import { AbonneAdminRoutingModule } from './view/abonne/abonne-admin-routing.module';
import { PurchaseAdminModule } from './view/purchase/purchase-admin.module';
import { PurchaseAdminRoutingModule } from './view/purchase/purchase-admin-routing.module';
import { UserAdminModule } from './view/user/user-admin.module';
import { UserAdminRoutingModule } from './view/user/user-admin-routing.module';
import { ReferetielAdminModule } from './view/referetiel/referetiel-admin.module';
import { ReferetielAdminRoutingModule } from './view/referetiel/referetiel-admin-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';



@NgModule({
  declarations: [
   LoginAdminComponent,
   RegisterAdminComponent
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
  DocAdminModule,
  DocAdminRoutingModule,
  AbonneAdminModule,
  AbonneAdminRoutingModule,
  PurchaseAdminModule,
  PurchaseAdminRoutingModule,
  UserAdminModule,
  UserAdminRoutingModule,
  ReferetielAdminModule,
  ReferetielAdminRoutingModule,
  SecurityModule,
  SecurityRoutingModule
  ],
  exports: [
  LoginAdminComponent,
  RegisterAdminComponent,

    DocAdminModule,
    AbonneAdminModule,
    PurchaseAdminModule,
    UserAdminModule,
    ReferetielAdminModule,
  SecurityModule
  ],

})

export class AdminModule {


}
