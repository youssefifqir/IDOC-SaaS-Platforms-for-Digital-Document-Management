
// const root = environment.rootAppUrl;



import {UserListComponent} from 'src/app/module/security/user/list/user-list.component';
import {ModelPermissionListComponent} from 'src/app/module/security/model-permission/list/model-permission-list.component';
import {ActionPermissionListComponent} from 'src/app/module/security/action-permission/list/action-permission-list.component';
import {ModelPermissionUserListComponent} from 'src/app/module/security/model-permission-utilisateur/list/model-permission-user-list.component';
import {RoleListComponent} from 'src/app/module/security/role/list/role-list.component';



import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';



import { DocumentFieldListAbonneComponent } from './document-field/list/document-field-list-abonne.component';
import { FieldListAbonneComponent } from './field/list/field-list-abonne.component';
import { EntiteAdministrativeTypeListAbonneComponent } from './entite-administrative-type/list/entite-administrative-type-list-abonne.component';
import { DocumentStateListAbonneComponent } from './document-state/list/document-state-list-abonne.component';
import { DocumentListAbonneComponent } from './document/list/document-list-abonne.component';
import { DocumentCategorieListAbonneComponent } from './document-categorie/list/document-categorie-list-abonne.component';
import { TagListAbonneComponent } from './tag/list/tag-list-abonne.component';
import { DocumentTypeListAbonneComponent } from './document-type/list/document-type-list-abonne.component';
import { DocumentFieldStateListAbonneComponent } from './document-field-state/list/document-field-state-list-abonne.component';
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
                                    component: DocumentFieldListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'field',
                            children: [
                                {
                                    path: 'list',
                                    component: FieldListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'entite-administrative-type',
                            children: [
                                {
                                    path: 'list',
                                    component: EntiteAdministrativeTypeListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-state',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentStateListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-categorie',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentCategorieListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'tag',
                            children: [
                                {
                                    path: 'list',
                                    component: TagListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-type',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentTypeListAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'document-field-state',
                            children: [
                                {
                                    path: 'list',
                                    component: DocumentFieldStateListAbonneComponent ,
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
export class DocAbonneRoutingModule { }
