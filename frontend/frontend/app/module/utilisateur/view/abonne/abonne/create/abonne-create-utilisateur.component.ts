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



import {AbonneUtilisateurService} from 'src/app/shared/service/utilisateur/abonne/AbonneUtilisateur.service';
import {AbonneDto} from 'src/app/shared/model/abonne/Abonne.model';
import {AbonneCriteria} from 'src/app/shared/criteria/abonne/AbonneCriteria.model';
import {EntiteAdministrativeDto} from 'src/app/shared/model/user/EntiteAdministrative.model';
import {EntiteAdministrativeUtilisateurService} from 'src/app/shared/service/utilisateur/user/EntiteAdministrativeUtilisateur.service';
@Component({
  selector: 'app-abonne-create-utilisateur',
  templateUrl: './abonne-create-utilisateur.component.html'
})
export class AbonneCreateUtilisateurComponent  implements OnInit {

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



    private _validEntiteAdministrativeCode = true;
    private _validEntiteAdministrativeLibelle = true;

	constructor(private service: AbonneUtilisateurService , private entiteAdministrativeService: EntiteAdministrativeUtilisateurService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.entiteAdministrativeService.findAll().subscribe((data) => this.entiteAdministratives = data);
    }


    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new AbonneDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }




    get entiteAdministrative(): EntiteAdministrativeDto {
        return this.entiteAdministrativeService.item;
    }
    set entiteAdministrative(value: EntiteAdministrativeDto) {
        this.entiteAdministrativeService.item = value;
    }
    get entiteAdministratives(): Array<EntiteAdministrativeDto> {
        return this.entiteAdministrativeService.items;
    }
    set entiteAdministratives(value: Array<EntiteAdministrativeDto>) {
        this.entiteAdministrativeService.items = value;
    }
    get createEntiteAdministrativeDialog(): boolean {
        return this.entiteAdministrativeService.createDialog;
    }
    set createEntiteAdministrativeDialog(value: boolean) {
        this.entiteAdministrativeService.createDialog= value;
    }




    get validEntiteAdministrativeCode(): boolean {
        return this._validEntiteAdministrativeCode;
    }
    set validEntiteAdministrativeCode(value: boolean) {
        this._validEntiteAdministrativeCode = value;
    }
    get validEntiteAdministrativeLibelle(): boolean {
        return this._validEntiteAdministrativeLibelle;
    }
    set validEntiteAdministrativeLibelle(value: boolean) {
        this._validEntiteAdministrativeLibelle = value;
    }


    get items(): Array<AbonneDto> {
        return this.service.items;
    }

    set items(value: Array<AbonneDto>) {
        this.service.items = value;
    }

    get item(): AbonneDto {
        return this.service.item;
    }

    set item(value: AbonneDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): AbonneCriteria {
        return this.service.criteria;
    }

    set criteria(value: AbonneCriteria) {
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
