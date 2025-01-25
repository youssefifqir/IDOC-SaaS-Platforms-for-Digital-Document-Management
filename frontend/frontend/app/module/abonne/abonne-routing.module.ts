
// const root = environment.rootAppUrl;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { LoginAbonneComponent } from './login-abonne/login-abonne.component';
import { RegisterAbonneComponent } from './register-abonne/register-abonne.component';

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
                                    component: LoginAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAbonneComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'doc',
                            loadChildren: () => import('./view/doc/doc-abonne-routing.module').then(x => x.DocAbonneRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'abonne',
                            loadChildren: () => import('./view/abonne/abonne-abonne-routing.module').then(x => x.AbonneAbonneRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'purchase',
                            loadChildren: () => import('./view/purchase/purchase-abonne-routing.module').then(x => x.PurchaseAbonneRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'user',
                            loadChildren: () => import('./view/user/user-abonne-routing.module').then(x => x.UserAbonneRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'referetiel',
                            loadChildren: () => import('./view/referetiel/referetiel-abonne-routing.module').then(x => x.ReferetielAbonneRoutingModule),
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
export class AbonneRoutingModule { }
