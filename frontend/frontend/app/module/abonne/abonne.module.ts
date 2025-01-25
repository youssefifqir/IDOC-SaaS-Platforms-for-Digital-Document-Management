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
import { LoginAbonneComponent } from './login-abonne/login-abonne.component';
import { RegisterAbonneComponent } from './register-abonne/register-abonne.component';
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

import { DocAbonneModule } from './view/doc/doc-abonne.module';
import { DocAbonneRoutingModule } from './view/doc/doc-abonne-routing.module';
import { AbonneAbonneModule } from './view/abonne/abonne-abonne.module';
import { AbonneAbonneRoutingModule } from './view/abonne/abonne-abonne-routing.module';
import { PurchaseAbonneModule } from './view/purchase/purchase-abonne.module';
import { PurchaseAbonneRoutingModule } from './view/purchase/purchase-abonne-routing.module';
import { UserAbonneModule } from './view/user/user-abonne.module';
import { UserAbonneRoutingModule } from './view/user/user-abonne-routing.module';
import { ReferetielAbonneModule } from './view/referetiel/referetiel-abonne.module';
import { ReferetielAbonneRoutingModule } from './view/referetiel/referetiel-abonne-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginAbonneComponent,
   RegisterAbonneComponent
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
  DocAbonneModule,
  DocAbonneRoutingModule,
  AbonneAbonneModule,
  AbonneAbonneRoutingModule,
  PurchaseAbonneModule,
  PurchaseAbonneRoutingModule,
  UserAbonneModule,
  UserAbonneRoutingModule,
  ReferetielAbonneModule,
  ReferetielAbonneRoutingModule,
  SecurityModule,
  SecurityRoutingModule
  ],
  exports: [
  LoginAbonneComponent,
  RegisterAbonneComponent,

    DocAbonneModule,
    AbonneAbonneModule,
    PurchaseAbonneModule,
    UserAbonneModule,
    ReferetielAbonneModule,
  SecurityModule
  ],

})
export class AbonneModule { }
