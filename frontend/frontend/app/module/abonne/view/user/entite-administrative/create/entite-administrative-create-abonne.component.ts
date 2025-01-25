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



import {EntiteAdministrativeAbonneService} from 'src/app/shared/service/abonne/user/EntiteAdministrativeAbonne.service';
import {EntiteAdministrativeDto} from 'src/app/shared/model/user/EntiteAdministrative.model';
import {EntiteAdministrativeCriteria} from 'src/app/shared/criteria/user/EntiteAdministrativeCriteria.model';
import {AbonneDto} from 'src/app/shared/model/abonne/Abonne.model';
import {AbonneAbonneService} from 'src/app/shared/service/abonne/abonne/AbonneAbonne.service';
import {EntiteAdministrativeTypeDto} from 'src/app/shared/model/doc/EntiteAdministrativeType.model';
import {EntiteAdministrativeTypeAbonneService} from 'src/app/shared/service/abonne/doc/EntiteAdministrativeTypeAbonne.service';
import {UtilisateurDto} from 'src/app/shared/model/user/Utilisateur.model';
import {UtilisateurAbonneService} from 'src/app/shared/service/abonne/user/UtilisateurAbonne.service';
@Component({
  selector: 'app-entite-administrative-create-abonne',
  templateUrl: './entite-administrative-create-abonne.component.html'
})
export class EntiteAdministrativeCreateAbonneComponent  implements OnInit {

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
    private _validEntiteAdministrativeTypeCode = true;
    private _validEntiteAdministrativeTypeLibelle = true;

	constructor(private service: EntiteAdministrativeAbonneService , private abonneService: AbonneAbonneService, private entiteAdministrativeTypeService: EntiteAdministrativeTypeAbonneService, private utilisateurService: UtilisateurAbonneService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.utilisateurService.findAll().subscribe((data) => this.chefs = data);
        this.entiteAdministrativeTypeService.findAll().subscribe((data) => this.entiteAdministrativeTypes = data);
        this.abonneService.findAll().subscribe((data) => this.abonnes = data);
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
                this.item = new EntiteAdministrativeDto();
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
        this.validEntiteAdministrativeCode = value;
        this.validEntiteAdministrativeLibelle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateEntiteAdministrativeCode();
        this.validateEntiteAdministrativeLibelle();
    }

    public validateEntiteAdministrativeCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validEntiteAdministrativeCode = false;
        } else {
            this.validEntiteAdministrativeCode = true;
        }
    }
    public validateEntiteAdministrativeLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validEntiteAdministrativeLibelle = false;
        } else {
            this.validEntiteAdministrativeLibelle = true;
        }
    }


    public async openCreateChef(chef: string) {
    const isPermistted = await this.roleService.isPermitted('Utilisateur', 'add');
    if(isPermistted) {
         this.chef = new UtilisateurDto();
         this.createChefDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get entiteAdministrativeType(): EntiteAdministrativeTypeDto {
        return this.entiteAdministrativeTypeService.item;
    }
    set entiteAdministrativeType(value: EntiteAdministrativeTypeDto) {
        this.entiteAdministrativeTypeService.item = value;
    }
    get entiteAdministrativeTypes(): Array<EntiteAdministrativeTypeDto> {
        return this.entiteAdministrativeTypeService.items;
    }
    set entiteAdministrativeTypes(value: Array<EntiteAdministrativeTypeDto>) {
        this.entiteAdministrativeTypeService.items = value;
    }
    get createEntiteAdministrativeTypeDialog(): boolean {
        return this.entiteAdministrativeTypeService.createDialog;
    }
    set createEntiteAdministrativeTypeDialog(value: boolean) {
        this.entiteAdministrativeTypeService.createDialog= value;
    }
    get abonne(): AbonneDto {
        return this.abonneService.item;
    }
    set abonne(value: AbonneDto) {
        this.abonneService.item = value;
    }
    get abonnes(): Array<AbonneDto> {
        return this.abonneService.items;
    }
    set abonnes(value: Array<AbonneDto>) {
        this.abonneService.items = value;
    }
    get createAbonneDialog(): boolean {
        return this.abonneService.createDialog;
    }
    set createAbonneDialog(value: boolean) {
        this.abonneService.createDialog= value;
    }
    get chef(): UtilisateurDto {
        return this.utilisateurService.item;
    }
    set chef(value: UtilisateurDto) {
        this.utilisateurService.item = value;
    }
    get chefs(): Array<UtilisateurDto> {
        return this.utilisateurService.items;
    }
    set chefs(value: Array<UtilisateurDto>) {
        this.utilisateurService.items = value;
    }
    get createChefDialog(): boolean {
        return this.utilisateurService.createDialog;
    }
    set createChefDialog(value: boolean) {
        this.utilisateurService.createDialog= value;
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

    get validEntiteAdministrativeTypeCode(): boolean {
        return this._validEntiteAdministrativeTypeCode;
    }
    set validEntiteAdministrativeTypeCode(value: boolean) {
        this._validEntiteAdministrativeTypeCode = value;
    }
    get validEntiteAdministrativeTypeLibelle(): boolean {
        return this._validEntiteAdministrativeTypeLibelle;
    }
    set validEntiteAdministrativeTypeLibelle(value: boolean) {
        this._validEntiteAdministrativeTypeLibelle = value;
    }


    get items(): Array<EntiteAdministrativeDto> {
        return this.service.items;
    }

    set items(value: Array<EntiteAdministrativeDto>) {
        this.service.items = value;
    }

    get item(): EntiteAdministrativeDto {
        return this.service.item;
    }

    set item(value: EntiteAdministrativeDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): EntiteAdministrativeCriteria {
        return this.service.criteria;
    }

    set criteria(value: EntiteAdministrativeCriteria) {
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
