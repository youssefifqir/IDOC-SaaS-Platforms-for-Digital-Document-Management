import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/zynerator/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {ModelPermissionUserService} from 'src/app/zynerator/security/shared/service/ModelPermissionUser.service';
import {ModelPermissionUserDto} from 'src/app/zynerator/security/shared/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from 'src/app/zynerator/security/shared/criteria/ModelPermissionUserCriteria.model';

import {ModelPermissionDto} from 'src/app/zynerator/security/shared/model/ModelPermission.model';
import {ModelPermissionService} from 'src/app/zynerator/security/shared/service/ModelPermission.service';
import {UserDto} from 'src/app/zynerator/security/shared/model/User.model';
import {UserService} from 'src/app/zynerator/security/shared/service/User.service';
import {ActionPermissionDto} from 'src/app/zynerator/security/shared/model/ActionPermission.model';
import {ActionPermissionService} from 'src/app/zynerator/security/shared/service/ActionPermission.service';
@Component({
  selector: 'app-model-permission-user-view',
  templateUrl: './model-permission-user-view.component.html'
})
export class ModelPermissionUserViewComponent extends AbstractViewController<ModelPermissionUserDto, ModelPermissionUserCriteria, ModelPermissionUserService> implements OnInit {


    constructor(private modelPermissionUserService: ModelPermissionUserService, private modelPermissionService: ModelPermissionService, private userService: UserService, private actionPermissionService: ActionPermissionService){
        super(modelPermissionUserService);
    }

    ngOnInit(): void {
    }


    get user(): UserDto {
       return this.userService.item;
    }
    set user(value: UserDto) {
        this.userService.item = value;
    }
    get users(): Array<UserDto> {
       return this.userService.items;
    }
    set users(value: Array<UserDto>) {
        this.userService.items = value;
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
