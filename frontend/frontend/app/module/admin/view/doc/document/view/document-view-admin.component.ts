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


import {DocumentAdminService} from 'src/app/shared/service/admin/doc/DocumentAdmin.service';
import {DocumentDto} from 'src/app/shared/model/doc/Document.model';
import {DocumentCriteria} from 'src/app/shared/criteria/doc/DocumentCriteria.model';

import {DocumentStateDto} from 'src/app/shared/model/doc/DocumentState.model';
import {DocumentStateAdminService} from 'src/app/shared/service/admin/doc/DocumentStateAdmin.service';
import {DocumentTagDto} from 'src/app/shared/model/doc/DocumentTag.model';
import {DocumentTagAdminService} from 'src/app/shared/service/admin/doc/DocumentTagAdmin.service';
import {AcessShareDto} from 'src/app/shared/model/referetiel/AcessShare.model';
import {AcessShareAdminService} from 'src/app/shared/service/admin/referetiel/AcessShareAdmin.service';
import {DocumentFieldDto} from 'src/app/shared/model/doc/DocumentField.model';
import {DocumentFieldAdminService} from 'src/app/shared/service/admin/doc/DocumentFieldAdmin.service';
import {GroupeDto} from 'src/app/shared/model/user/Groupe.model';
import {GroupeAdminService} from 'src/app/shared/service/admin/user/GroupeAdmin.service';
import {DocumentTypeDto} from 'src/app/shared/model/doc/DocumentType.model';
import {DocumentTypeAdminService} from 'src/app/shared/service/admin/doc/DocumentTypeAdmin.service';
import {DocumentFieldStateDto} from 'src/app/shared/model/doc/DocumentFieldState.model';
import {DocumentFieldStateAdminService} from 'src/app/shared/service/admin/doc/DocumentFieldStateAdmin.service';
import {FieldDto} from 'src/app/shared/model/doc/Field.model';
import {FieldAdminService} from 'src/app/shared/service/admin/doc/FieldAdmin.service';
import {DocumentPartageGroupeDto} from 'src/app/shared/model/purchase/DocumentPartageGroupe.model';
import {DocumentPartageGroupeAdminService} from 'src/app/shared/service/admin/purchase/DocumentPartageGroupeAdmin.service';
import {DocumentCategorieDto} from 'src/app/shared/model/doc/DocumentCategorie.model';
import {DocumentCategorieAdminService} from 'src/app/shared/service/admin/doc/DocumentCategorieAdmin.service';
import {DocumentPartageUtilisateurDto} from 'src/app/shared/model/purchase/DocumentPartageUtilisateur.model';
import {DocumentPartageUtilisateurAdminService} from 'src/app/shared/service/admin/purchase/DocumentPartageUtilisateurAdmin.service';
import {EntiteAdministrativeDto} from 'src/app/shared/model/user/EntiteAdministrative.model';
import {EntiteAdministrativeAdminService} from 'src/app/shared/service/admin/user/EntiteAdministrativeAdmin.service';
import {TagDto} from 'src/app/shared/model/doc/Tag.model';
import {TagAdminService} from 'src/app/shared/service/admin/doc/TagAdmin.service';
import {UtilisateurDto} from 'src/app/shared/model/user/Utilisateur.model';
import {UtilisateurAdminService} from 'src/app/shared/service/admin/user/UtilisateurAdmin.service';
@Component({
  selector: 'app-document-view-admin',
  templateUrl: './document-view-admin.component.html'
})
export class DocumentViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    documentFields = new DocumentFieldDto();
    documentFieldss: Array<DocumentFieldDto> = [];
    documentPartageGroupes = new DocumentPartageGroupeDto();
    documentPartageGroupess: Array<DocumentPartageGroupeDto> = [];
    documentPartageUtilisateurs = new DocumentPartageUtilisateurDto();
    documentPartageUtilisateurss: Array<DocumentPartageUtilisateurDto> = [];
    documentTags = new DocumentTagDto();
    documentTagss: Array<DocumentTagDto> = [];

    constructor(private service: DocumentAdminService, private documentStateService: DocumentStateAdminService, private documentTagService: DocumentTagAdminService, private acessShareService: AcessShareAdminService, private documentFieldService: DocumentFieldAdminService, private groupeService: GroupeAdminService, private documentTypeService: DocumentTypeAdminService, private documentFieldStateService: DocumentFieldStateAdminService, private fieldService: FieldAdminService, private documentPartageGroupeService: DocumentPartageGroupeAdminService, private documentCategorieService: DocumentCategorieAdminService, private documentPartageUtilisateurService: DocumentPartageUtilisateurAdminService, private entiteAdministrativeService: EntiteAdministrativeAdminService, private tagService: TagAdminService, private utilisateurService: UtilisateurAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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
    get groupe(): GroupeDto {
        return this.groupeService.item;
    }
    set groupe(value: GroupeDto) {
        this.groupeService.item = value;
    }
    get groupes(): Array<GroupeDto> {
        return this.groupeService.items;
    }
    set groupes(value: Array<GroupeDto>) {
        this.groupeService.items = value;
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
    get proprietaire(): UtilisateurDto {
        return this.utilisateurService.item;
    }
    set proprietaire(value: UtilisateurDto) {
        this.utilisateurService.item = value;
    }
    get proprietaires(): Array<UtilisateurDto> {
        return this.utilisateurService.items;
    }
    set proprietaires(value: Array<UtilisateurDto>) {
        this.utilisateurService.items = value;
    }
    get field(): FieldDto {
        return this.fieldService.item;
    }
    set field(value: FieldDto) {
        this.fieldService.item = value;
    }
    get fields(): Array<FieldDto> {
        return this.fieldService.items;
    }
    set fields(value: Array<FieldDto>) {
        this.fieldService.items = value;
    }
    get documentFieldState(): DocumentFieldStateDto {
        return this.documentFieldStateService.item;
    }
    set documentFieldState(value: DocumentFieldStateDto) {
        this.documentFieldStateService.item = value;
    }
    get documentFieldStates(): Array<DocumentFieldStateDto> {
        return this.documentFieldStateService.items;
    }
    set documentFieldStates(value: Array<DocumentFieldStateDto>) {
        this.documentFieldStateService.items = value;
    }
    get documentType(): DocumentTypeDto {
        return this.documentTypeService.item;
    }
    set documentType(value: DocumentTypeDto) {
        this.documentTypeService.item = value;
    }
    get documentTypes(): Array<DocumentTypeDto> {
        return this.documentTypeService.items;
    }
    set documentTypes(value: Array<DocumentTypeDto>) {
        this.documentTypeService.items = value;
    }
    get entiteAdministrativeProprietaire(): EntiteAdministrativeDto {
        return this.entiteAdministrativeService.item;
    }
    set entiteAdministrativeProprietaire(value: EntiteAdministrativeDto) {
        this.entiteAdministrativeService.item = value;
    }
    get entiteAdministrativeProprietaires(): Array<EntiteAdministrativeDto> {
        return this.entiteAdministrativeService.items;
    }
    set entiteAdministrativeProprietaires(value: Array<EntiteAdministrativeDto>) {
        this.entiteAdministrativeService.items = value;
    }
    get tag(): TagDto {
        return this.tagService.item;
    }
    set tag(value: TagDto) {
        this.tagService.item = value;
    }
    get tags(): Array<TagDto> {
        return this.tagService.items;
    }
    set tags(value: Array<TagDto>) {
        this.tagService.items = value;
    }
    get acessShare(): AcessShareDto {
        return this.acessShareService.item;
    }
    set acessShare(value: AcessShareDto) {
        this.acessShareService.item = value;
    }
    get acessShares(): Array<AcessShareDto> {
        return this.acessShareService.items;
    }
    set acessShares(value: Array<AcessShareDto>) {
        this.acessShareService.items = value;
    }
    get documentCategorie(): DocumentCategorieDto {
        return this.documentCategorieService.item;
    }
    set documentCategorie(value: DocumentCategorieDto) {
        this.documentCategorieService.item = value;
    }
    get documentCategories(): Array<DocumentCategorieDto> {
        return this.documentCategorieService.items;
    }
    set documentCategories(value: Array<DocumentCategorieDto>) {
        this.documentCategorieService.items = value;
    }
    get documentState(): DocumentStateDto {
        return this.documentStateService.item;
    }
    set documentState(value: DocumentStateDto) {
        this.documentStateService.item = value;
    }
    get documentStates(): Array<DocumentStateDto> {
        return this.documentStateService.items;
    }
    set documentStates(value: Array<DocumentStateDto>) {
        this.documentStateService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<DocumentDto> {
        return this.service.items;
    }

    set items(value: Array<DocumentDto>) {
        this.service.items = value;
    }

    get item(): DocumentDto {
        return this.service.item;
    }

    set item(value: DocumentDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): DocumentCriteria {
        return this.service.criteria;
    }

    set criteria(value: DocumentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
