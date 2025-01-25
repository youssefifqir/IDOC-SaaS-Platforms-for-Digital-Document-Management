import {Component, OnInit} from '@angular/core';


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
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


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
  selector: 'app-entite-administrative-view-abonne',
  templateUrl: './entite-administrative-view-abonne.component.html'
})
export class EntiteAdministrativeViewAbonneComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: EntiteAdministrativeAbonneService, private abonneService: AbonneAbonneService, private entiteAdministrativeTypeService: EntiteAdministrativeTypeAbonneService, private utilisateurService: UtilisateurAbonneService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): EntiteAdministrativeCriteria {
        return this.service.criteria;
    }

    set criteria(value: EntiteAdministrativeCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
