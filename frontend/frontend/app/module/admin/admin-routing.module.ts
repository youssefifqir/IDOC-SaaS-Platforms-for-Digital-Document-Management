
// const root = environment.rootAppUrl;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';

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
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'doc',
                            loadChildren: () => import('./view/doc/doc-admin-routing.module').then(x => x.DocAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'abonne',
                            loadChildren: () => import('./view/abonne/abonne-admin-routing.module').then(x => x.AbonneAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'purchase',
                            loadChildren: () => import('./view/purchase/purchase-admin-routing.module').then(x => x.PurchaseAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'user',
                            loadChildren: () => import('./view/user/user-admin-routing.module').then(x => x.UserAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'referetiel',
                            loadChildren: () => import('./view/referetiel/referetiel-admin-routing.module').then(x => x.ReferetielAdminRoutingModule),
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
export class AdminRoutingModule { }
