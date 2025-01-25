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




import {DocumentFieldStateAbonneService} from 'src/app/shared/service/abonne/doc/DocumentFieldStateAbonne.service';
import {DocumentFieldStateDto} from 'src/app/shared/model/doc/DocumentFieldState.model';
import {DocumentFieldStateCriteria} from 'src/app/shared/criteria/doc/DocumentFieldStateCriteria.model';



@Component({
  selector: 'app-document-field-state-edit-abonne',
  templateUrl: './document-field-state-edit-abonne.component.html'
})
export class DocumentFieldStateEditAbonneComponent implements OnInit {

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



    private _validDocumentFieldStateCode = true;
    private _validDocumentFieldStateLibelle = true;




    constructor(private service: DocumentFieldStateAbonneService , @Inject(PLATFORM_ID) private platformId?) {
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
            this.item = new DocumentFieldStateDto();
        } , error =>{
            console.log(error);
        });
    }



    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validDocumentFieldStateCode = value;
        this.validDocumentFieldStateLibelle = value;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateDocumentFieldStateCode();
        this.validateDocumentFieldStateLibelle();
    }

    public validateDocumentFieldStateCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validDocumentFieldStateCode = false;
        } else {
            this.validDocumentFieldStateCode = true;
        }
    }

    public validateDocumentFieldStateLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
            this.errorMessages.push('Libelle non valide');
            this.validDocumentFieldStateLibelle = false;
        } else {
            this.validDocumentFieldStateLibelle = true;
        }
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


	get items(): Array<DocumentFieldStateDto> {
        return this.service.items;
    }

    set items(value: Array<DocumentFieldStateDto>) {
        this.service.items = value;
    }

    get item(): DocumentFieldStateDto {
        return this.service.item;
    }

    set item(value: DocumentFieldStateDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): DocumentFieldStateCriteria {
        return this.service.criteria;
    }

    set criteria(value: DocumentFieldStateCriteria) {
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
