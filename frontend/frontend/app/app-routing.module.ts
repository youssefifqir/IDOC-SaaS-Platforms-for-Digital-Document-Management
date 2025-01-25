/*import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthGuard} from "src/app/zynerator/security/guards/auth.guard";
import {AccessComponent} from "src/app/demo/components/auth/access/access.component";
import {AppLayoutComponent} from "src/app/layout/app.layout.component";

import {LoginAdminComponent} from 'src/app/module/admin/login-admin/login-admin.component';
import {RegisterAdminComponent} from 'src/app/module/admin/register-admin/register-admin.component';
import {LoginAbonneComponent} from 'src/app/module/abonne/login-abonne/login-abonne.component';
import {RegisterAbonneComponent} from 'src/app/module/abonne/register-abonne/register-abonne.component';
import {LoginUtilisateurComponent} from 'src/app/module/utilisateur/login-utilisateur/login-utilisateur.component';
import {RegisterUtilisateurComponent} from 'src/app/module/utilisateur/register-utilisateur/register-utilisateur.component';
import {LandingpageComponent} from "./landingpage/landingpage.component"; */
/*@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {path: '', component: LoginAdminComponent},
            {path: 'admin/login', component: LoginAdminComponent },
            {path: 'admin/register', component: RegisterAdminComponent },
            {path: 'abonne/login', component: LoginAbonneComponent },
            {path: 'abonne/register', component: RegisterAbonneComponent },
            {path: 'utilisateur/login', component: LoginUtilisateurComponent },
            {path: 'utilisateur/register', component: RegisterUtilisateurComponent },
            {
            path: 'app',
            component: AppLayoutComponent,
            children: [
                {
                    path: 'admin',
                    loadChildren: () => import( './module/admin/admin-routing.module').then(x => x.AdminRoutingModule),
                    canActivate: [AuthGuard],
                },
                {
                    path: 'abonne',
                    loadChildren: () => import( './module/abonne/abonne-routing.module').then(x => x.AbonneRoutingModule),
                    canActivate: [AuthGuard],
                },
                {
                    path: 'utilisateur',
                    loadChildren: () => import( './module/utilisateur/utilisateur-routing.module').then(x => x.UtilisateurRoutingModule),
                    canActivate: [AuthGuard],
                },
                    { path: 'denied', component: AccessComponent },
                ],
                canActivate: [AuthGuard]
                },
            ],
                { scrollPositionRestoration: 'enabled' }
            ),
        ],
    exports: [RouterModule],
    })
export class AppRoutingModule { } */
import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthGuard} from "src/app/zynerator/security/guards/auth.guard";
import {AccessComponent} from "src/app/demo/components/auth/access/access.component";
import {AppLayoutComponent} from "src/app/layout/app.layout.component";

import {LoginAdminComponent} from 'src/app/module/admin/login-admin/login-admin.component';
import {RegisterAdminComponent} from 'src/app/module/admin/register-admin/register-admin.component';
import {LoginAbonneComponent} from 'src/app/module/abonne/login-abonne/login-abonne.component';
import {RegisterAbonneComponent} from 'src/app/module/abonne/register-abonne/register-abonne.component';
import {LoginUtilisateurComponent} from 'src/app/module/utilisateur/login-utilisateur/login-utilisateur.component';
import {RegisterUtilisateurComponent} from 'src/app/module/utilisateur/register-utilisateur/register-utilisateur.component';
import {LandingpageComponent} from "./landingpage/landingpage.component";
@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {path: '', component: LandingpageComponent},
                {path: 'admin/login', component: LoginAdminComponent },
                {path: 'admin/register', component: RegisterAdminComponent },
                {path: 'abonne/login', component: LoginAbonneComponent },
                {path: 'abonne/register', component: RegisterAbonneComponent },
                {path: 'utilisateur/login', component: LoginUtilisateurComponent },
                {path: 'utilisateur/register', component: RegisterUtilisateurComponent },
                {
                    path: 'app',
                    component: AppLayoutComponent,
                    children: [
                        {
                            path: 'admin',
                            loadChildren: () => import( './module/admin/admin-routing.module').then(x => x.AdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'abonne',
                            loadChildren: () => import( './module/abonne/abonne-routing.module').then(x => x.AbonneRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'utilisateur',
                            loadChildren: () => import( './module/utilisateur/utilisateur-routing.module').then(x => x.UtilisateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        { path: 'denied', component: AccessComponent },
                    ],
                    canActivate: [AuthGuard]
                },
            ],
            { scrollPositionRestoration: 'enabled' }
        ),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule { }
