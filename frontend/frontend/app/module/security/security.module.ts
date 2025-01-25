import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {DialogModule} from 'primeng/dialog';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import {MultiSelectModule} from 'primeng/multiselect';


import {PasswordModule} from 'primeng/password';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import {SplitButtonModule} from 'primeng/splitbutton';
import {MessageModule} from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {ModelPermissionEditComponent} from './model-permission/edit/model-permission-edit.component';
import {UserCreateComponent} from './user/create/user-create.component';
import {UserListComponent} from './user/list/user-list.component';
import {UserViewComponent} from './user/view/user-view.component';
import {UserEditComponent} from './user/edit/user-edit.component';
import {ModelPermissionCreateComponent} from './model-permission/create/model-permission-create.component';
import {ModelPermissionListComponent} from './model-permission/list/model-permission-list.component';
import {ModelPermissionViewComponent} from './model-permission/view/model-permission-view.component';
import {RoleEditComponent} from './role/edit/role-edit.component';
import {ActionPermissionCreateComponent} from './action-permission/create/action-permission-create.component';
import {ActionPermissionListComponent} from './action-permission/list/action-permission-list.component';
import {ActionPermissionViewComponent} from './action-permission/view/action-permission-view.component';
import {ActionPermissionEditComponent} from './action-permission/edit/action-permission-edit.component';
import {ModelPermissionUserCreateComponent} from './model-permission-utilisateur/create/model-permission-user-create.component';
import {ModelPermissionUserListComponent} from './model-permission-utilisateur/list/model-permission-user-list.component';
import {ModelPermissionUserViewComponent} from './model-permission-utilisateur/view/model-permission-user-view.component';
import {ModelPermissionUserEditComponent} from './model-permission-utilisateur/edit/model-permission-user-edit.component';
import {RoleCreateComponent} from './role/create/role-create.component';
import {RoleListComponent} from './role/list/role-list.component';
import {RoleViewComponent} from './role/view/role-view.component';
import {PaginatorModule} from 'primeng/paginator';
import {TranslateModule} from '@ngx-translate/core';
import {CardModule} from 'primeng/card';


@NgModule({
    declarations: [
        UserCreateComponent,
        UserListComponent,
        UserViewComponent,
        UserEditComponent,
        ModelPermissionCreateComponent,
        ModelPermissionListComponent,
        ModelPermissionViewComponent,
        ModelPermissionEditComponent,
        ActionPermissionCreateComponent,
        ActionPermissionListComponent,
        ActionPermissionViewComponent,
        ActionPermissionEditComponent,
        ModelPermissionUserCreateComponent,
        ModelPermissionUserListComponent,
        ModelPermissionUserViewComponent,
        ModelPermissionUserEditComponent,
        RoleCreateComponent,
        RoleListComponent,
        RoleViewComponent,
        RoleEditComponent,
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
        CardModule,

    ],
    exports: [
        UserCreateComponent,
        UserListComponent,
        UserViewComponent,
        UserEditComponent,
        ModelPermissionCreateComponent,
        ModelPermissionListComponent,
        ModelPermissionViewComponent,
        ModelPermissionEditComponent,
        ActionPermissionCreateComponent,
        ActionPermissionListComponent,
        ActionPermissionViewComponent,
        ActionPermissionEditComponent,
        ModelPermissionUserCreateComponent,
        ModelPermissionUserListComponent,
        ModelPermissionUserViewComponent,
        ModelPermissionUserEditComponent,
        RoleCreateComponent,
        RoleListComponent,
        RoleViewComponent,
        RoleEditComponent,
    ],

})
export class SecurityModule {
}
