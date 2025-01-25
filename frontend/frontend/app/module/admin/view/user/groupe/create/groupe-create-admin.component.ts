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



import {GroupeAdminService} from 'src/app/shared/service/admin/user/GroupeAdmin.service';
import {GroupeDto} from 'src/app/shared/model/user/Groupe.model';
import {GroupeCriteria} from 'src/app/shared/criteria/user/GroupeCriteria.model';
import {GroupeUtilisateurDto} from 'src/app/shared/model/purchase/GroupeUtilisateur.model';
import {GroupeUtilisateurAdminService} from 'src/app/shared/service/admin/purchase/GroupeUtilisateurAdmin.service';
import {UtilisateurDto} from 'src/app/shared/model/user/Utilisateur.model';
import {UtilisateurAdminService} from 'src/app/shared/service/admin/user/UtilisateurAdmin.service';
@Component({
  selector: 'app-groupe-create-admin',
  templateUrl: './groupe-create-admin.component.html'
})
export class GroupeCreateAdminComponent  implements OnInit {

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

    private _groupeUtilisateursElement = new GroupeUtilisateurDto();


   private _validGroupeCode = true;
   private _validGroupeLibelle = true;
    private _groupeUtilisateurs: Array<GroupeUtilisateurDto> = [];

	constructor(private service: GroupeAdminService , private groupeUtilisateurService: GroupeUtilisateurAdminService, private utilisateurService: UtilisateurAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.utilisateurService.findAll().subscribe(data => this.prepareGroupeUtilisateurs(data));
        this.groupeUtilisateursElement.utilisateur = new UtilisateurDto();
        this.utilisateurService.findAll().subscribe((data) => this.utilisateurs = data);
        this.utilisateurService.findAll().subscribe((data) => this.utilisateurs = data);
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
                this.item = new GroupeDto();
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


     prepareGroupeUtilisateurs(utilisateurs: Array<UtilisateurDto>): void{
        if( utilisateurs != null){
                utilisateurs.forEach(e => {
                const groupeUtilisateur = new GroupeUtilisateurDto();
                groupeUtilisateur.utilisateur = e;
                this.groupeUtilisateurs.push(groupeUtilisateur);
            });
        }
    }



    public  setValidation(value: boolean){
        this.validGroupeCode = value;
        this.validGroupeLibelle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateGroupeCode();
        this.validateGroupeLibelle();
    }

    public validateGroupeCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validGroupeCode = false;
        } else {
            this.validGroupeCode = true;
        }
    }
    public validateGroupeLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validGroupeLibelle = false;
        } else {
            this.validGroupeLibelle = true;
        }
    }


    public async openCreateUtilisateur(utilisateur: string) {
    const isPermistted = await this.roleService.isPermitted('Utilisateur', 'add');
    if(isPermistted) {
         this.utilisateur = new UtilisateurDto();
         this.createUtilisateurDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createUtilisateurDialog(): boolean {
        return this.utilisateurService.createDialog;
    }
    set createUtilisateurDialog(value: boolean) {
        this.utilisateurService.createDialog= value;
    }

    get groupeUtilisateurs(): Array<GroupeUtilisateurDto> {
        if( this._groupeUtilisateurs == null )
            this._groupeUtilisateurs = new Array();
        return this._groupeUtilisateurs;
    }

    set groupeUtilisateurs(value: Array<GroupeUtilisateurDto>) {
        this._groupeUtilisateurs = value;
    }


    get validGroupeCode(): boolean {
        return this._validGroupeCode;
    }

    set validGroupeCode(value: boolean) {
         this._validGroupeCode = value;
    }
    get validGroupeLibelle(): boolean {
        return this._validGroupeLibelle;
    }

    set validGroupeLibelle(value: boolean) {
         this._validGroupeLibelle = value;
    }


    get groupeUtilisateursElement(): GroupeUtilisateurDto {
        if( this._groupeUtilisateursElement == null )
            this._groupeUtilisateursElement = new GroupeUtilisateurDto();
        return this._groupeUtilisateursElement;
    }

    set groupeUtilisateursElement(value: GroupeUtilisateurDto) {
        this._groupeUtilisateursElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): GroupeCriteria {
        return this.service.criteria;
    }

    set criteria(value: GroupeCriteria) {
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
