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


import {GroupeAbonneService} from 'src/app/shared/service/abonne/user/GroupeAbonne.service';
import {GroupeDto} from 'src/app/shared/model/user/Groupe.model';
import {GroupeCriteria} from 'src/app/shared/criteria/user/GroupeCriteria.model';

import {GroupeUtilisateurDto} from 'src/app/shared/model/purchase/GroupeUtilisateur.model';
import {GroupeUtilisateurAbonneService} from 'src/app/shared/service/abonne/purchase/GroupeUtilisateurAbonne.service';
import {UtilisateurDto} from 'src/app/shared/model/user/Utilisateur.model';
import {UtilisateurAbonneService} from 'src/app/shared/service/abonne/user/UtilisateurAbonne.service';
@Component({
  selector: 'app-groupe-view-abonne',
  templateUrl: './groupe-view-abonne.component.html'
})
export class GroupeViewAbonneComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    groupeUtilisateurs = new GroupeUtilisateurDto();
    groupeUtilisateurss: Array<GroupeUtilisateurDto> = [];

    constructor(private service: GroupeAbonneService, private groupeUtilisateurService: GroupeUtilisateurAbonneService, private utilisateurService: UtilisateurAbonneService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get utilisateur(): UtilisateurDto {
        return this.utilisateurService.item;
    }
    set utilisateur(value: UtilisateurDto) {
        this.utilisateurService.item = value;
    }
    get utilisateurs(): Array<UtilisateurDto> {
        return this.utilisateurService.items;
    }
    set utilisateurs(value: Array<UtilisateurDto>) {
        this.utilisateurService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<GroupeDto> {
        return this.service.items;
    }

    set items(value: Array<GroupeDto>) {
        this.service.items = value;
    }

    get item(): GroupeDto {
        return this.service.item;
    }

    set item(value: GroupeDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): GroupeCriteria {
        return this.service.criteria;
    }

    set criteria(value: GroupeCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
