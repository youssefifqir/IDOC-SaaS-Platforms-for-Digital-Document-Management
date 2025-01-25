
// const root = environment.rootAppUrl;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { LoginUtilisateurComponent } from './login-utilisateur/login-utilisateur.component';
import { RegisterUtilisateurComponent } from './register-utilisateur/register-utilisateur.component';

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterUtilisateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'doc',
                            loadChildren: () => import('./view/doc/doc-utilisateur-routing.module').then(x => x.DocUtilisateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'abonne',
                            loadChildren: () => import('./view/abonne/abonne-utilisateur-routing.module').then(x => x.AbonneUtilisateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'purchase',
                            loadChildren: () => import('./view/purchase/purchase-utilisateur-routing.module').then(x => x.PurchaseUtilisateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'user',
                            loadChildren: () => import('./view/user/user-utilisateur-routing.module').then(x => x.UserUtilisateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'referetiel',
                            loadChildren: () => import('./view/referetiel/referetiel-utilisateur-routing.module').then(x => x.ReferetielUtilisateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class UtilisateurRoutingModule { }
