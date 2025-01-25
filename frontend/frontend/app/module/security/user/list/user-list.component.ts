import {Component, OnInit} from '@angular/core';
import {UserService} from 'src/app/zynerator/security/shared/service/User.service';
import {UserDto} from 'src/app/zynerator/security/shared/model/User.model';
import {UserCriteria} from 'src/app/zynerator/security/shared/criteria/UserCriteria.model';
import {AbstractListController} from 'src/app/zynerator/controller/AbstractListController';
import {environment} from 'src/environments/environment';
import {RoleUserService} from 'src/app/zynerator/security/shared/service/RoleUser.service';
import {ModelPermissionService} from 'src/app/zynerator/security/shared/service/ModelPermission.service';
import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {ModelPermissionUserService} from 'src/app/zynerator/security/shared/service/ModelPermissionUser.service';
import {ActionPermissionService} from 'src/app/zynerator/security/shared/service/ActionPermission.service';


@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html'
})
export class UserListComponent extends AbstractListController<UserDto, UserCriteria, UserService> implements OnInit {

    override fileName = 'User';

    yesOrNoCredentialsNonExpired: any[] = [];
    yesOrNoEnabled: any[] = [];
    yesOrNoAccountNonExpired: any[] = [];
    yesOrNoAccountNonLocked: any[] = [];
    yesOrNoPasswordChanged: any[] = [];


    constructor(private userservice: UserService) {
        super(userservice);
    }

     ngOnInit(): void {
         this.activateSecurityConstraint('User').subscribe(() => {
             if (this.listActionIsValid) {
                 this.findPaginatedByCriteria();
                 this.initExport();
                 this.initCol();
                 this.yesOrNoCredentialsNonExpired = [{label: 'CredentialsNonExpired', value: null}, {
                     label: 'Oui',
                     value: 1
                 }, {
                     label: 'Non',
                     value: 0
                 }];
                 this.yesOrNoEnabled = [{label: 'Enabled', value: null}, {label: 'Oui', value: 1}, {label: 'Non', value: 0}];
                 this.yesOrNoAccountNonExpired = [{label: 'AccountNonExpired', value: null}, {
                     label: 'Oui',
                     value: 1
                 }, {label: 'Non', value: 0}];
                 this.yesOrNoAccountNonLocked = [{label: 'AccountNonLocked', value: null}, {
                     label: 'Oui',
                     value: 1
                 }, {label: 'Non', value: 0}];
                 this.yesOrNoPasswordChanged = [{label: 'PasswordChanged', value: null}, {
                     label: 'Oui',
                     value: 1
                 }, {label: 'Non', value: 0}];
             }
         });

    }


    public override initCol() {
        this.cols = [
            {field: 'credentialsNonExpired', header: 'Credentials non expired'},
            {field: 'enabled', header: 'Enabled'},
            {field: 'email', header: 'Email'},
            {field: 'accountNonExpired', header: 'Account non expired'},
            {field: 'accountNonLocked', header: 'Account non locked'},
            {field: 'username', header: 'Username'},
            {field: 'password', header: 'Password'},
            {field: 'passwordChanged', header: 'Password changed'},
        ];
    }


    public override initDuplicate(res: UserDto) {
        if (res.modelPermissionUsers != null) {
            res.modelPermissionUsers.forEach(d => {
                d.user = null;
                d.id = null;
            });
        }
        if (res.roleUsers != null) {
            res.roleUsers.forEach(d => {
                d.user = null;
                d.id = null;
            });
        }
    }


    public override prepareColumnExport(): void {
        this.exportData = this.items.map(e => {
            return {
                'Credentials non expired': e.credentialsNonExpired ? 'Vrai' : 'Faux',
                'Enabled': e.enabled ? 'Vrai' : 'Faux',
                'Email': e.email,
                'Account non expired': e.accountNonExpired ? 'Vrai' : 'Faux',
                'Account non locked': e.accountNonLocked ? 'Vrai' : 'Faux',
                'Username': e.username,
                'Password': e.password,
                'Password changed': e.passwordChanged ? 'Vrai' : 'Faux',
            };
        });

        this.criteriaData = [{
            'Credentials non expired': this.criteria.credentialsNonExpired ? (this.criteria.credentialsNonExpired ? environment.trueValue : environment.falseValue) : environment.emptyForExport,
            'Enabled': this.criteria.enabled ? (this.criteria.enabled ? environment.trueValue : environment.falseValue) : environment.emptyForExport,
            'Email': this.criteria.email ? this.criteria.email : environment.emptyForExport,
            'Account non expired': this.criteria.accountNonExpired ? (this.criteria.accountNonExpired ? environment.trueValue : environment.falseValue) : environment.emptyForExport,
            'Account non locked': this.criteria.accountNonLocked ? (this.criteria.accountNonLocked ? environment.trueValue : environment.falseValue) : environment.emptyForExport,
            'Username': this.criteria.username ? this.criteria.username : environment.emptyForExport,
            'Password': this.criteria.password ? this.criteria.password : environment.emptyForExport,
            'Password changed': this.criteria.passwordChanged ? (this.criteria.passwordChanged ? environment.trueValue : environment.falseValue) : environment.emptyForExport,
        }];
    }
}
