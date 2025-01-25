import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/zynerator/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {UserService} from 'src/app/zynerator/security/shared/service/User.service';
import {UserDto} from 'src/app/zynerator/security/shared/model/User.model';
import {UserCriteria} from 'src/app/zynerator/security/shared/criteria/UserCriteria.model';

import {RoleUserDto} from 'src/app/zynerator/security/shared/model/RoleUser.model';
import {RoleUserService} from 'src/app/zynerator/security/shared/service/RoleUser.service';
import {ModelPermissionDto} from 'src/app/zynerator/security/shared/model/ModelPermission.model';
import {ModelPermissionService} from 'src/app/zynerator/security/shared/service/ModelPermission.service';
import {RoleDto} from 'src/app/zynerator/security/shared/model/Role.model';
import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {ModelPermissionUserDto} from 'src/app/zynerator/security/shared/model/ModelPermissionUser.model';
import {ModelPermissionUserService} from 'src/app/zynerator/security/shared/service/ModelPermissionUser.service';
import {ActionPermissionDto} from 'src/app/zynerator/security/shared/model/ActionPermission.model';
import {ActionPermissionService} from 'src/app/zynerator/security/shared/service/ActionPermission.service';
@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html'
})
export class UserViewComponent extends AbstractViewController<UserDto, UserCriteria, UserService> implements OnInit {

    modelPermissionUsers = new ModelPermissionUserDto();
    modelPermissionUserss: Array<ModelPermissionUserDto> = [];
    roleUsers = new RoleUserDto();
    roleUserss: Array<RoleUserDto> = [];

    constructor(private userservice: UserService, private roleUserService: RoleUserService, private modelPermissionService: ModelPermissionService, private roleservice: RoleService, private modelPermissionUserService: ModelPermissionUserService, private actionPermissionService: ActionPermissionService){
        super(userservice);
    }

    ngOnInit(): void {
    }


    get modelPermission(): ModelPermissionDto {
       return this.modelPermissionService.item;
    }
    set modelPermission(value: ModelPermissionDto) {
        this.modelPermissionService.item = value;
    }
    get modelPermissions(): Array<ModelPermissionDto> {
       return this.modelPermissionService.items;
    }
    set modelPermissions(value: Array<ModelPermissionDto>) {
        this.modelPermissionService.items = value;
    }
    get role(): RoleDto {
       return this.roleService.item;
    }
    set role(value: RoleDto) {
        this.roleService.item = value;
    }
    get roles(): Array<RoleDto> {
       return this.roleService.items;
    }
    set roles(value: Array<RoleDto>) {
        this.roleService.items = value;
    }
    get actionPermission(): ActionPermissionDto {
       return this.actionPermissionService.item;
    }
    set actionPermission(value: ActionPermissionDto) {
        this.actionPermissionService.item = value;
    }
    get actionPermissions(): Array<ActionPermissionDto> {
       return this.actionPermissionService.items;
    }
    set actionPermissions(value: Array<ActionPermissionDto>) {
        this.actionPermissionService.items = value;
    }


}
