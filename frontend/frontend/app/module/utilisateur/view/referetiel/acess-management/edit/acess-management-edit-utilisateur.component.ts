import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {AcessManagementUtilisateurService} from 'src/app/shared/service/utilisateur/referetiel/AcessManagementUtilisateur.service';
import {AcessManagementDto} from 'src/app/shared/model/referetiel/AcessManagement.model';
import {AcessManagementCriteria} from 'src/app/shared/criteria/referetiel/AcessManagementCriteria.model';



@Component({
  selector: 'app-acess-management-edit-utilisateur',
  templateUrl: './acess-management-edit-utilisateur.component.html'
})
export class AcessManagementEditUtilisateurComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;



    private _validAcessManagementCode = true;
    private _validAcessManagementLibelle = true;




    constructor(private service: AcessManagementUtilisateurService , @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
    }

    public prepareEdit() {
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new AcessManagementDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validAcessManagementCode = value;
        this.validAcessManagementLibelle = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateAcessManagementCode();
        this.validateAcessManagementLibelle();
    }

    public validateAcessManagementCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validAcessManagementCode = false;
        } else {
            this.validAcessManagementCode = true;
        }
    }

    public validateAcessManagementLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
            this.errorMessages.push('Libelle non valide');
            this.validAcessManagementLibelle = false;
        } else {
            this.validAcessManagementLibelle = true;
        }
    }







    get validAcessManagementCode(): boolean {
        return this._validAcessManagementCode;
    }
    set validAcessManagementCode(value: boolean) {
        this._validAcessManagementCode = value;
    }
    get validAcessManagementLibelle(): boolean {
        return this._validAcessManagementLibelle;
    }
    set validAcessManagementLibelle(value: boolean) {
        this._validAcessManagementLibelle = value;
    }


	get items(): Array<AcessManagementDto> {
        return this.service.items;
    }

    set items(value: Array<AcessManagementDto>) {
        this.service.items = value;
    }

    get item(): AcessManagementDto {
        return this.service.item;
    }

    set item(value: AcessManagementDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): AcessManagementCriteria {
        return this.service.criteria;
    }

    set criteria(value: AcessManagementCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }


}
