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


import {DocumentFieldAbonneService} from 'src/app/shared/service/abonne/doc/DocumentFieldAbonne.service';
import {DocumentFieldDto} from 'src/app/shared/model/doc/DocumentField.model';
import {DocumentFieldCriteria} from 'src/app/shared/criteria/doc/DocumentFieldCriteria.model';

import {FieldDto} from 'src/app/shared/model/doc/Field.model';
import {FieldAbonneService} from 'src/app/shared/service/abonne/doc/FieldAbonne.service';
import {DocumentFieldStateDto} from 'src/app/shared/model/doc/DocumentFieldState.model';
import {DocumentFieldStateAbonneService} from 'src/app/shared/service/abonne/doc/DocumentFieldStateAbonne.service';
import {DocumentDto} from 'src/app/shared/model/doc/Document.model';
import {DocumentAbonneService} from 'src/app/shared/service/abonne/doc/DocumentAbonne.service';
@Component({
  selector: 'app-document-field-view-abonne',
  templateUrl: './document-field-view-abonne.component.html'
})
export class DocumentFieldViewAbonneComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: DocumentFieldAbonneService, private fieldService: FieldAbonneService, private documentFieldStateService: DocumentFieldStateAbonneService, private documentService: DocumentAbonneService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): DocumentFieldCriteria {
        return this.service.criteria;
    }

    set criteria(value: DocumentFieldCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
