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



import {DocumentFieldUtilisateurService} from 'src/app/shared/service/utilisateur/doc/DocumentFieldUtilisateur.service';
import {DocumentFieldDto} from 'src/app/shared/model/doc/DocumentField.model';
import {DocumentFieldCriteria} from 'src/app/shared/criteria/doc/DocumentFieldCriteria.model';
import {FieldDto} from 'src/app/shared/model/doc/Field.model';
import {FieldUtilisateurService} from 'src/app/shared/service/utilisateur/doc/FieldUtilisateur.service';
import {DocumentFieldStateDto} from 'src/app/shared/model/doc/DocumentFieldState.model';
import {DocumentFieldStateUtilisateurService} from 'src/app/shared/service/utilisateur/doc/DocumentFieldStateUtilisateur.service';
import {DocumentDto} from 'src/app/shared/model/doc/Document.model';
import {DocumentUtilisateurService} from 'src/app/shared/service/utilisateur/doc/DocumentUtilisateur.service';
@Component({
  selector: 'app-document-field-create-utilisateur',
  templateUrl: './document-field-create-utilisateur.component.html'
})
export class DocumentFieldCreateUtilisateurComponent  implements OnInit {

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



    private _validFieldCode = true;
    private _validFieldLibelle = true;
    private _validDocumentReference = true;
    private _validDocumentFieldStateCode = true;
    private _validDocumentFieldStateLibelle = true;

	constructor(private service: DocumentFieldUtilisateurService , private fieldService: FieldUtilisateurService, private documentFieldStateService: DocumentFieldStateUtilisateurService, private documentService: DocumentUtilisateurService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.fieldService.findAll().subscribe((data) => this.fields = data);
        this.documentService.findAll().subscribe((data) => this.documents = data);
        this.documentFieldStateService.findAll().subscribe((data) => this.documentFieldStates = data);
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
                this.item = new DocumentFieldDto();
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
    public async openCreateDocument(document: string) {
    const isPermistted = await this.roleService.isPermitted('Document', 'add');
    if(isPermistted) {
         this.document = new DocumentDto();
         this.createDocumentDialog = true;
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
    get document(): DocumentDto {
        return this.documentService.item;
    }
    set document(value: DocumentDto) {
        this.documentService.item = value;
    }
    get documents(): Array<DocumentDto> {
        return this.documentService.items;
    }
    set documents(value: Array<DocumentDto>) {
        this.documentService.items = value;
    }
    get createDocumentDialog(): boolean {
        return this.documentService.createDialog;
    }
    set createDocumentDialog(value: boolean) {
        this.documentService.createDialog= value;
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




    get validFieldCode(): boolean {
        return this._validFieldCode;
    }
    set validFieldCode(value: boolean) {
        this._validFieldCode = value;
    }
    get validFieldLibelle(): boolean {
        return this._validFieldLibelle;
    }
    set validFieldLibelle(value: boolean) {
        this._validFieldLibelle = value;
    }
    get validDocumentReference(): boolean {
        return this._validDocumentReference;
    }
    set validDocumentReference(value: boolean) {
        this._validDocumentReference = value;
    }
    get validDocumentFieldStateCode(): boolean {
        return this._validDocumentFieldStateCode;
    }
    set validDocumentFieldStateCode(value: boolean) {
        this._validDocumentFieldStateCode = value;
    }
    get validDocumentFieldStateLibelle(): boolean {
        return this._validDocumentFieldStateLibelle;
    }
    set validDocumentFieldStateLibelle(value: boolean) {
        this._validDocumentFieldStateLibelle = value;
    }


    get items(): Array<DocumentFieldDto> {
        return this.service.items;
    }

    set items(value: Array<DocumentFieldDto>) {
        this.service.items = value;
    }

    get item(): DocumentFieldDto {
        return this.service.item;
    }

    set item(value: DocumentFieldDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DocumentFieldCriteria {
        return this.service.criteria;
    }

    set criteria(value: DocumentFieldCriteria) {
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
