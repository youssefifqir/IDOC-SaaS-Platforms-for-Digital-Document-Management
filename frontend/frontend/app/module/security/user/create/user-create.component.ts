import {Component, OnInit, Input} from '@angular/core';

import { AbstractCreateController } from 'src/app/zynerator/controller/AbstractCreateController';

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
    selector: 'app-user-create',
    templateUrl: './user-create.component.html'
})
export class UserCreateComponent extends AbstractCreateController<UserDto, UserCriteria, UserService>  implements OnInit {

    private _modelPermissionUsersElement = new ModelPermissionUserDto();
    private _modelPermissionUsersElements = new Array<ModelPermissionUserDto>();
    private _modelSecurityPermissionUsersElements = new Array<ModelPermissionUserDto>();
    private _roleUsersElement = new RoleUserDto();
    private _roleUsers: Array<RoleUserDto> = [];
    private _globalValue = true ;



    constructor(private userservice: UserService , private roleUserService: RoleUserService, private modelPermissionService: ModelPermissionService, private roleservice: RoleService, private modelPermissionUserService: ModelPermissionUserService, private actionPermissionService: ActionPermissionService) {
        super(userservice);
    }

    ngOnInit(): void {
        this.modelPermissionUsersElement.actionPermission = new ActionPermissionDto();
        this.modelPermissionUsersElement.modelPermission = new ModelPermissionDto();
        this.roleService.findAll().subscribe(data => this.prepareRoleUsers(data));
        this.roleUsersElement.role = new RoleDto();
        this.roleService.findAll().subscribe((data) => this.roles = data);
        this.modelPermissionUserService.initModelPermissionUser().subscribe(data => this.modelPermissionUsersElements = data);
        this.modelPermissionUserService.initSecurityModelPermissionUser().subscribe(data => this.modelSecurityPermissionUsersElements= data);

    }
    editPermission(modelPermissionUsers: Array<ModelPermissionUserDto>) {
        modelPermissionUsers.forEach(e => {
            e.value = this.globalValue;
            e.modelPermission.globalValue = this.globalValue;
        });
    }


    editModelPermission(dto: ModelPermissionUserDto,modelPermissionUsers: Array<ModelPermissionUserDto>) {
        modelPermissionUsers.forEach(e => {
            if (e.modelPermission.reference === dto.modelPermission.reference) {
                e.value = dto.modelPermission.globalValue;
            }
        });
    }

    override save() {
        this.modelPermissionUsersElements.forEach(e => this.item.modelPermissionUsers.push(e));
        this.modelSecurityPermissionUsersElements.forEach(e => this.item.modelPermissionUsers.push(e));
        super.save();
    }

    preparePermissionUsers(): Array<ModelPermissionUserDto>{
        this.modelPermissionUsersElements = new Array();
        this.actionPermissionService.findAll().subscribe(data => this.actionPermissions = data );
        this.modelPermissionService.findAll().subscribe(data => this.modelPermissions = data );
        this.modelPermissions.forEach( model => {
            this.actionPermissions.forEach(action => {
                const modelPermissionUser = new ModelPermissionUserDto();
                modelPermissionUser.actionPermission = action;
                modelPermissionUser.modelPermission = model;
                modelPermissionUser.value = true;
                this.modelPermissionUsersElements.push(modelPermissionUser);
            });
        });
        return this.modelPermissionUsersElements;
    }
    prepareRoleUsers(roles: Array<RoleDto>): void{
        if( roles != null){
            roles.forEach(e => {
                const roleUser = new RoleUserDto();
                roleUser.role = e;
                this.roleUsers.push(roleUser);
            });
        }
    }

    validateModelPermissionUsers(){
        this.errorMessages = new Array();
    }


    public override setValidation(value: boolean){
    }

    public addModelPermissionUsers() {
        if( this.item.modelPermissionUsers == null )
            this.item.modelPermissionUsers = new Array<ModelPermissionUserDto>();
        this.validateModelPermissionUsers();
        if (this.errorMessages.length === 0) {
            this.item.modelPermissionUsers.push({... this.modelPermissionUsersElement});
            this.modelPermissionUsersElement = new ModelPermissionUserDto();
        }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
    }


    public deletemodelPermissionUsers(p: ModelPermissionUserDto) {
        this.item.modelPermissionUsers.forEach((element, index) => {
            if (element === p) { this.item.modelPermissionUsers.splice(index, 1); }
        });
    }

    public editmodelPermissionUsers(p: ModelPermissionUserDto) {
        this.modelPermissionUsersElement = {... p};
        this.activeTab = 0;
    }


    public override validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateModelPermission(modelPermission: string) {
        const isPermistted = await this.roleService.isPermitted('ModelPermission', 'add');
        if(isPermistted) {
            this.modelPermission = new ModelPermissionDto();
            this.createModelPermissionDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateRole(role: string) {
        const isPermistted = await this.roleService.isPermitted('Role', 'add');
        if(isPermistted) {
            this.role = new RoleDto();
            this.createRoleDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateActionPermission(actionPermission: string) {
        const isPermistted = await this.roleService.isPermitted('ActionPermission', 'add');
        if(isPermistted) {
            this.actionPermission = new ActionPermissionDto();
            this.createActionPermissionDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createModelPermissionDialog(): boolean {
        return this.modelPermissionService.createDialog;
    }
    set createModelPermissionDialog(value: boolean) {
        this.modelPermissionService.createDialog= value;
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
    get createRoleDialog(): boolean {
        return this.roleService.createDialog;
    }
    set createRoleDialog(value: boolean) {
        this.roleService.createDialog= value;
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
    get createActionPermissionDialog(): boolean {
        return this.actionPermissionService.createDialog;
    }
    set createActionPermissionDialog(value: boolean) {
        this.actionPermissionService.createDialog= value;
    }

    get roleUsers(): Array<RoleUserDto> {
        if( this._roleUsers == null )
            this._roleUsers = new Array();
        return this._roleUsers;
    }

    set roleUsers(value: Array<RoleUserDto>) {
        this._roleUsers = value;
    }




    get modelPermissionUsersElement(): ModelPermissionUserDto {
        if( this._modelPermissionUsersElement == null )
            this._modelPermissionUsersElement = new ModelPermissionUserDto();
        return this._modelPermissionUsersElement;
    }

    set modelPermissionUsersElement(value: ModelPermissionUserDto) {
        this._modelPermissionUsersElement = value;
    }
    get roleUsersElement(): RoleUserDto {
        if( this._roleUsersElement == null )
            this._roleUsersElement = new RoleUserDto();
        return this._roleUsersElement;
    }

    set roleUsersElement(value: RoleUserDto) {
        this._roleUsersElement = value;
    }
    get globalValue(): boolean {
        return this._globalValue;
    }

    set globalValue(value: boolean) {
        this._globalValue = value;
    }


    get modelPermissionUsersElements(): ModelPermissionUserDto[] {
        return this._modelPermissionUsersElements;
    }

    set modelPermissionUsersElements(value: ModelPermissionUserDto[]) {
        this._modelPermissionUsersElements = value;
    }


    get modelSecurityPermissionUsersElements(): ModelPermissionUserDto[] {
        return this._modelSecurityPermissionUsersElements;
    }

    set modelSecurityPermissionUsersElements(value: ModelPermissionUserDto[]) {
        this._modelSecurityPermissionUsersElements = value;
    }
}
