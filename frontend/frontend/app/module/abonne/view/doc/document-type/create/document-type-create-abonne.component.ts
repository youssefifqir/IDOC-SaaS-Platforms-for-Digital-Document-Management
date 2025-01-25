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



import {DocumentTypeAbonneService} from 'src/app/shared/service/abonne/doc/DocumentTypeAbonne.service';
import {DocumentTypeDto} from 'src/app/shared/model/doc/DocumentType.model';
import {DocumentTypeCriteria} from 'src/app/shared/criteria/doc/DocumentTypeCriteria.model';
@Component({
  selector: 'app-document-type-create-abonne',
  templateUrl: './document-type-create-abonne.component.html'
})
export class DocumentTypeCreateAbonneComponent  implements OnInit {

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



   private _validDocumentTypeCode = true;
   private _validDocumentTypeLibelle = true;

	constructor(private service: DocumentTypeAbonneService , @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
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
                this.item = new DocumentTypeDto();
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
        this.validDocumentTypeCode = value;
        this.validDocumentTypeLibelle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateDocumentTypeCode();
        this.validateDocumentTypeLibelle();
    }

    public validateDocumentTypeCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validDocumentTypeCode = false;
        } else {
            this.validDocumentTypeCode = true;
        }
    }
    public validateDocumentTypeLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validDocumentTypeLibelle = false;
        } else {
            this.validDocumentTypeLibelle = true;
        }
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



    get items(): Array<DocumentTypeDto> {
        return this.service.items;
    }

    set items(value: Array<DocumentTypeDto>) {
        this.service.items = value;
    }

    get item(): DocumentTypeDto {
        return this.service.item;
    }

    set item(value: DocumentTypeDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DocumentTypeCriteria {
        return this.service.criteria;
    }

    set criteria(value: DocumentTypeCriteria) {
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
