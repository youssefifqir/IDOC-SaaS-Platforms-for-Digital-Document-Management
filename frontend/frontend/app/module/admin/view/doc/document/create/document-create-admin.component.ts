import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';

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
import {FileUploadEvent, FileUploadModule} from 'primeng/fileupload';

interface UploadEvent {
    originalEvent: Event;
    files: File[];
}


@Component({
    selector: 'app-document-create-admin',
    templateUrl: './document-create-admin.component.html'
})
export class DocumentCreateAdminComponent  implements OnInit {




















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

    private _documentFieldsElement = new DocumentFieldDto();
    private _documentPartageGroupesElement = new DocumentPartageGroupeDto();
    private _documentPartageUtilisateursElement = new DocumentPartageUtilisateurDto();
    private _documentTagsElement = new DocumentTagDto();


    private _validDocumentReference = true;
    private _validDocumentTypeCode = true;
    private _validDocumentTypeLibelle = true;
    private _validDocumentStateCode = true;
    private _validDocumentStateLibelle = true;
    private _validDocumentCategorieCode = true;
    private _validDocumentCategorieLibelle = true;
    private _validEntiteAdministrativeCode = true;
    private _validEntiteAdministrativeLibelle = true;
    private _validEntiteAdministrativeProprietaireCode = true;
    private _validEntiteAdministrativeProprietaireLibelle = true;
    private _documentTags: Array<DocumentTagDto> = [];

    constructor(private authService: AuthService ,private service: DocumentAdminService , private documentStateService: DocumentStateAdminService, private documentTagService: DocumentTagAdminService, private acessShareService: AcessShareAdminService, private documentFieldService: DocumentFieldAdminService, private groupeService: GroupeAdminService, private documentTypeService: DocumentTypeAdminService, private documentFieldStateService: DocumentFieldStateAdminService, private fieldService: FieldAdminService, private documentPartageGroupeService: DocumentPartageGroupeAdminService, private documentCategorieService: DocumentCategorieAdminService, private documentPartageUtilisateurService: DocumentPartageUtilisateurAdminService, private entiteAdministrativeService: EntiteAdministrativeAdminService, private tagService: TagAdminService, private utilisateurService: UtilisateurAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        //
    //
        this.documentFieldsElement.field = new FieldDto();
        this.fieldService.findAll().subscribe((data) => this.fields = data);
        this.documentFieldsElement.documentFieldState = new DocumentFieldStateDto();
        this.documentFieldStateService.findAll().subscribe((data) => this.documentFieldStates = data);
        this.documentPartageGroupesElement.groupe = new GroupeDto();
        this.groupeService.findAll().subscribe((data) => this.groupes = data);
        this.documentPartageGroupesElement.acessShare = new AcessShareDto();
        this.acessShareService.findAll().subscribe((data) => this.acessShares = data);
        this.documentPartageUtilisateursElement.utilisateur = new UtilisateurDto();
        this.utilisateurService.findAll().subscribe((data) => this.utilisateurs = data);
        this.documentPartageUtilisateursElement.acessShare = new AcessShareDto();
        this.acessShareService.findAll().subscribe((data) => this.acessShares = data);
        this.tagService.findAll().subscribe(data => this.prepareDocumentTags(data));
        this.documentTagsElement.tag = new TagDto();
        this.tagService.findAll().subscribe((data) => this.tags = data);
        this.documentTypeService.findAll().subscribe((data) => this.documentTypes = data);
        this.documentStateService.findAll().subscribe((data) => this.documentStates = data);
        this.documentCategorieService.findAll().subscribe((data) => this.documentCategories = data);
        this.utilisateurService.findAll().subscribe((data) => this.proprietaires = data);
        this.entiteAdministrativeService.findAll().subscribe((data) => this.entiteAdministratives = data);
        this.entiteAdministrativeService.findAll().subscribe((data) => this.entiteAdministrativeProprietaires = data);
        //this.username=this.authService.authenticatedUser.username;
    }



    selectedFiles: File[];
    uploadedFiles: File[] = [];
    username:string = '';
    entiteAdministrativeCode:string ='';

    onUploadMultiple(event: any) {
        this.uploadedFiles = event.files;
        this.selectedFiles = [...this.uploadedFiles];
        this.entiteAdministrativeCode=this.item.entiteAdministrative.code;
        this.service.uploadFilesToMinio(this.selectedFiles).subscribe({
            next: (minioInfos) => {
                this.messageService.add({severity:'success', summary: 'Success', detail: 'Files uploaded successfully'});
                console.log('Files uploaded successfully:', minioInfos);
            },
            error: (err) => {
                this.messageService.add({severity:'error', summary: 'Error', detail: 'File upload failed'});
                console.error('File upload failed:', err);
            }
        });
    }


    onUploadWithGeneratedPath(event: any) {
        this.username=this.item.proprietaire.username
        this.entiteAdministrativeCode=this.item.entiteAdministrative.code;
        this.uploadedFiles = event.files;
        const file = this.uploadedFiles[0]; // Assuming you only upload one file with the generated path
        this.service.uploadFileToMinioWithGeneratedPath(file, this.entiteAdministrativeCode, this.username).subscribe({
            next: (result) => {
                if (result === 1) {
                    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'File uploaded successfully with generated path' });
                    console.log('File uploaded successfully with generated path:', result);
                } else {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'File upload failed with generated path' });
                    console.error('File upload failed with generated path:', result);
                }
            },
            error: (err) => {
                this.messageService.add({ severity: 'error', summary: 'Error', detail: 'File upload failed with generated path' });
                console.error('File upload failed with generated path:', err);
            }
        });
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
                this.item = new DocumentDto();
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


    prepareDocumentTags(tags: Array<TagDto>): void{
        if( tags != null){
            tags.forEach(e => {
                const documentTag = new DocumentTagDto();
                documentTag.tag = e;
                this.documentTags.push(documentTag);
            });
        }
    }

    validateDocumentFields(){
        this.errorMessages = new Array();
    }
    validateDocumentPartageGroupes(){
        this.errorMessages = new Array();
    }
    validateDocumentPartageUtilisateurs(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
        this.validDocumentReference = value;
    }

    public addDocumentFields() {
        if( this.item.documentFields == null )
            this.item.documentFields = new Array<DocumentFieldDto>();
        this.validateDocumentFields();
        if (this.errorMessages.length === 0) {
            this.item.documentFields.push({... this.documentFieldsElement});
            this.documentFieldsElement = new DocumentFieldDto();
        }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
    }


    public deletedocumentFields(p: DocumentFieldDto) {
        this.item.documentFields.forEach((element, index) => {
            if (element === p) { this.item.documentFields.splice(index, 1); }
        });
    }

    public editdocumentFields(p: DocumentFieldDto) {
        this.documentFieldsElement = {... p};
        this.activeTab = 0;
    }
    public addDocumentPartageGroupes() {
        if( this.item.documentPartageGroupes == null )
            this.item.documentPartageGroupes = new Array<DocumentPartageGroupeDto>();
        this.validateDocumentPartageGroupes();
        if (this.errorMessages.length === 0) {
            this.item.documentPartageGroupes.push({... this.documentPartageGroupesElement});
            this.documentPartageGroupesElement = new DocumentPartageGroupeDto();
        }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
    }


    public deletedocumentPartageGroupes(p: DocumentPartageGroupeDto) {
        this.item.documentPartageGroupes.forEach((element, index) => {
            if (element === p) { this.item.documentPartageGroupes.splice(index, 1); }
        });
    }

    public editdocumentPartageGroupes(p: DocumentPartageGroupeDto) {
        this.documentPartageGroupesElement = {... p};
        this.activeTab = 0;
    }
    public addDocumentPartageUtilisateurs() {
        if( this.item.documentPartageUtilisateurs == null )
            this.item.documentPartageUtilisateurs = new Array<DocumentPartageUtilisateurDto>();
        this.validateDocumentPartageUtilisateurs();
        if (this.errorMessages.length === 0) {
            this.item.documentPartageUtilisateurs.push({... this.documentPartageUtilisateursElement});
            this.documentPartageUtilisateursElement = new DocumentPartageUtilisateurDto();
        }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
    }


    public deletedocumentPartageUtilisateurs(p: DocumentPartageUtilisateurDto) {
        this.item.documentPartageUtilisateurs.forEach((element, index) => {
            if (element === p) { this.item.documentPartageUtilisateurs.splice(index, 1); }
        });
    }

    public editdocumentPartageUtilisateurs(p: DocumentPartageUtilisateurDto) {
        this.documentPartageUtilisateursElement = {... p};
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateDocumentReference();
    }

    public validateDocumentReference(){
        if (this.stringUtilService.isEmpty(this.item.reference)) {
            this.errorMessages.push('Reference non valide');
            this.validDocumentReference = false;
        } else {
            this.validDocumentReference = true;
        }
    }


    public async openCreateField(field: string) {
        const isPermistted = await this.roleService.isPermitted('Field', 'add');
        if(isPermistted) {
            this.field = new FieldDto();
            this.createFieldDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateDocumentFieldState(documentFieldState: string) {
        const isPermistted = await this.roleService.isPermitted('DocumentFieldState', 'add');
        if(isPermistted) {
            this.documentFieldState = new DocumentFieldStateDto();
            this.createDocumentFieldStateDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateDocumentType(documentType: string) {
        const isPermistted = await this.roleService.isPermitted('DocumentType', 'add');
        if(isPermistted) {
            this.documentType = new DocumentTypeDto();
            this.createDocumentTypeDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateTag(tag: string) {
        const isPermistted = await this.roleService.isPermitted('Tag', 'add');
        if(isPermistted) {
            this.tag = new TagDto();
            this.createTagDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateDocumentCategorie(documentCategorie: string) {
        const isPermistted = await this.roleService.isPermitted('DocumentCategorie', 'add');
        if(isPermistted) {
            this.documentCategorie = new DocumentCategorieDto();
            this.createDocumentCategorieDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
    public async openCreateDocumentState(documentState: string) {
        const isPermistted = await this.roleService.isPermitted('DocumentState', 'add');
        if(isPermistted) {
            this.documentState = new DocumentStateDto();
            this.createDocumentStateDialog = true;
        }else{
            this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createGroupeDialog(): boolean {
        return this.groupeService.createDialog;
    }
    set createGroupeDialog(value: boolean) {
        this.groupeService.createDialog= value;
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
    get createProprietaireDialog(): boolean {
        return this.utilisateurService.createDialog;
    }
    set createProprietaireDialog(value: boolean) {
        this.utilisateurService.createDialog= value;
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
    get createFieldDialog(): boolean {
        return this.fieldService.createDialog;
    }
    set createFieldDialog(value: boolean) {
        this.fieldService.createDialog= value;
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
    get createDocumentFieldStateDialog(): boolean {
        return this.documentFieldStateService.createDialog;
    }
    set createDocumentFieldStateDialog(value: boolean) {
        this.documentFieldStateService.createDialog= value;
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
    get createDocumentTypeDialog(): boolean {
        return this.documentTypeService.createDialog;
    }
    set createDocumentTypeDialog(value: boolean) {
        this.documentTypeService.createDialog= value;
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
    get createEntiteAdministrativeProprietaireDialog(): boolean {
        return this.entiteAdministrativeService.createDialog;
    }
    set createEntiteAdministrativeProprietaireDialog(value: boolean) {
        this.entiteAdministrativeService.createDialog= value;
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
    get createTagDialog(): boolean {
        return this.tagService.createDialog;
    }
    set createTagDialog(value: boolean) {
        this.tagService.createDialog= value;
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
    get createAcessShareDialog(): boolean {
        return this.acessShareService.createDialog;
    }
    set createAcessShareDialog(value: boolean) {
        this.acessShareService.createDialog= value;
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
    get createDocumentCategorieDialog(): boolean {
        return this.documentCategorieService.createDialog;
    }
    set createDocumentCategorieDialog(value: boolean) {
        this.documentCategorieService.createDialog= value;
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
    get createDocumentStateDialog(): boolean {
        return this.documentStateService.createDialog;
    }
    set createDocumentStateDialog(value: boolean) {
        this.documentStateService.createDialog= value;
    }

    get documentTags(): Array<DocumentTagDto> {
        if( this._documentTags == null )
            this._documentTags = new Array();
        return this._documentTags;
    }

    set documentTags(value: Array<DocumentTagDto>) {
        this._documentTags = value;
    }


    get validDocumentReference(): boolean {
        return this._validDocumentReference;
    }

    set validDocumentReference(value: boolean) {
        this._validDocumentReference = value;
    }

    get validDocumentTypeCode(): boolean {
        return this._validDocumentTypeCode;
    }
    set validDocumentTypeCode(value: boolean) {
        this._validDocumentTypeCode = value;
    }
    get validDocumentTypeLibelle(): boolean {
        return this._validDocumentTypeLibelle;
    }
    set validDocumentTypeLibelle(value: boolean) {
        this._validDocumentTypeLibelle = value;
    }
    get validDocumentStateCode(): boolean {
        return this._validDocumentStateCode;
    }
    set validDocumentStateCode(value: boolean) {
        this._validDocumentStateCode = value;
    }
    get validDocumentStateLibelle(): boolean {
        return this._validDocumentStateLibelle;
    }
    set validDocumentStateLibelle(value: boolean) {
        this._validDocumentStateLibelle = value;
    }
    get validDocumentCategorieCode(): boolean {
        return this._validDocumentCategorieCode;
    }
    set validDocumentCategorieCode(value: boolean) {
        this._validDocumentCategorieCode = value;
    }
    get validDocumentCategorieLibelle(): boolean {
        return this._validDocumentCategorieLibelle;
    }
    set validDocumentCategorieLibelle(value: boolean) {
        this._validDocumentCategorieLibelle = value;
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
    get validEntiteAdministrativeProprietaireCode(): boolean {
        return this._validEntiteAdministrativeProprietaireCode;
    }
    set validEntiteAdministrativeProprietaireCode(value: boolean) {
        this._validEntiteAdministrativeProprietaireCode = value;
    }
    get validEntiteAdministrativeProprietaireLibelle(): boolean {
        return this._validEntiteAdministrativeProprietaireLibelle;
    }
    set validEntiteAdministrativeProprietaireLibelle(value: boolean) {
        this._validEntiteAdministrativeProprietaireLibelle = value;
    }

    get documentFieldsElement(): DocumentFieldDto {
        if( this._documentFieldsElement == null )
            this._documentFieldsElement = new DocumentFieldDto();
        return this._documentFieldsElement;
    }

    set documentFieldsElement(value: DocumentFieldDto) {
        this._documentFieldsElement = value;
    }
    get documentPartageGroupesElement(): DocumentPartageGroupeDto {
        if( this._documentPartageGroupesElement == null )
            this._documentPartageGroupesElement = new DocumentPartageGroupeDto();
        return this._documentPartageGroupesElement;
    }

    set documentPartageGroupesElement(value: DocumentPartageGroupeDto) {
        this._documentPartageGroupesElement = value;
    }
    get documentPartageUtilisateursElement(): DocumentPartageUtilisateurDto {
        if( this._documentPartageUtilisateursElement == null )
            this._documentPartageUtilisateursElement = new DocumentPartageUtilisateurDto();
        return this._documentPartageUtilisateursElement;
    }

    set documentPartageUtilisateursElement(value: DocumentPartageUtilisateurDto) {
        this._documentPartageUtilisateursElement = value;
    }
    get documentTagsElement(): DocumentTagDto {
        if( this._documentTagsElement == null )
            this._documentTagsElement = new DocumentTagDto();
        return this._documentTagsElement;
    }

    set documentTagsElement(value: DocumentTagDto) {
        this._documentTagsElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DocumentCriteria {
        return this.service.criteria;
    }

    set criteria(value: DocumentCriteria) {
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
    //start




}
