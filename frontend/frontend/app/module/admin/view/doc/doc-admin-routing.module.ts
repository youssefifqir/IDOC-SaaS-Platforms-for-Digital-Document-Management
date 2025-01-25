
// const root = environment.rootAppUrl;



import {UserListComponent} from 'src/app/module/security/user/list/user-list.component';
import {ModelPermissionListComponent} from 'src/app/module/security/model-permission/list/model-permission-list.component';
import {ActionPermissionListComponent} from 'src/app/module/security/action-permission/list/action-permission-list.component';
import {ModelPermissionUserListComponent} from 'src/app/module/security/model-permission-utilisateur/list/model-permission-user-list.component';
import {RoleListComponent} from 'src/app/module/security/role/list/role-list.component';



import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';



import { DocumentFieldListAdminComponent } from './document-field/list/document-field-list-admin.component';
import { FieldListAdminComponent } from './field/list/field-list-admin.component';
import { EntiteAdministrativeTypeListAdminComponent } from './entite-administrative-type/list/entite-administrative-type-list-admin.component';
import { DocumentStateListAdminComponent } from './document-state/list/document-state-list-admin.component';
import { DocumentListAdminComponent } from './document/list/document-list-admin.component';
import { DocumentCategorieListAdminComponent } from './document-categorie/list/document-categorie-list-admin.component';
import { TagListAdminComponent } from './tag/list/tag-list-admin.component';
import { DocumentTypeListAdminComponent } from './document-type/list/document-type-list-admin.component';
import { DocumentFieldStateListAdminComponent } from './document-field-state/list/document-field-state-list-admin.component';
@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {

                            path: 'action-permission',
                            children: [
                                {
                                    path: 'list',
                                    component: ActionPermissionListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'model-permission-user',
                            children: [
                                {
                                    path: 'list',
                                    component: ModelPermissionUserListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {

                            path: 'role',
                            children: [
                                {
                                    path: 'list',
                                    component: RoleListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {

                            path: 'user',
                            children: [
                                {
                                    path: 'list',
                                    component: UserListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'model-permission',
                            children: [
                                {
                                    path: 'list',
                                    component: ModelPermissionListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },


                        {

                            path: 'document-field',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentFieldListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'field',
                            children: [
                                {
                                    path: 'list',
                                    component: FieldListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'entite-administrative-type',
                            children: [
                                {
                                    path: 'list',
                                    component: EntiteAdministrativeTypeListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-state',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentStateListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-categorie',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentCategorieListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'tag',
                            children: [
                                {
                                    path: 'list',
                                    component: TagListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-type',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentTypeListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-field-state',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentFieldStateListAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class DocAdminRoutingModule { }
