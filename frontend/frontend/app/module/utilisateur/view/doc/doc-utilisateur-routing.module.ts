
// const root = environment.rootAppUrl;



import {UserListComponent} from 'src/app/module/security/user/list/user-list.component';
import {ModelPermissionListComponent} from 'src/app/module/security/model-permission/list/model-permission-list.component';
import {ActionPermissionListComponent} from 'src/app/module/security/action-permission/list/action-permission-list.component';
import {ModelPermissionUserListComponent} from 'src/app/module/security/model-permission-utilisateur/list/model-permission-user-list.component';
import {RoleListComponent} from 'src/app/module/security/role/list/role-list.component';



import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';



import { DocumentFieldListUtilisateurComponent } from './document-field/list/document-field-list-utilisateur.component';
import { FieldListUtilisateurComponent } from './field/list/field-list-utilisateur.component';
import { EntiteAdministrativeTypeListUtilisateurComponent } from './entite-administrative-type/list/entite-administrative-type-list-utilisateur.component';
import { DocumentStateListUtilisateurComponent } from './document-state/list/document-state-list-utilisateur.component';
import { DocumentListUtilisateurComponent } from './document/list/document-list-utilisateur.component';
import { DocumentCategorieListUtilisateurComponent } from './document-categorie/list/document-categorie-list-utilisateur.component';
import { TagListUtilisateurComponent } from './tag/list/tag-list-utilisateur.component';
import { DocumentTypeListUtilisateurComponent } from './document-type/list/document-type-list-utilisateur.component';
import { DocumentFieldStateListUtilisateurComponent } from './document-field-state/list/document-field-state-list-utilisateur.component';
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
                                    component: DocumentFieldListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'field',
                            children: [
                                {
                                    path: 'list',
                                    component: FieldListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'entite-administrative-type',
                            children: [
                                {
                                    path: 'list',
                                    component: EntiteAdministrativeTypeListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-state',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentStateListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-categorie',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentCategorieListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'tag',
                            children: [
                                {
                                    path: 'list',
                                    component: TagListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-type',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentTypeListUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-field-state',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentFieldStateListUtilisateurComponent ,
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
export class DocUtilisateurRoutingModule { }
