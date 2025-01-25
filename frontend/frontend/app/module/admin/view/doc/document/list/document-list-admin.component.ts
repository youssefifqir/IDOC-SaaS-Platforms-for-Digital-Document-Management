import {Component, OnInit} from '@angular/core';
import {DocumentAdminService} from 'src/app/shared/service/admin/doc/DocumentAdmin.service';
import {DocumentDto} from 'src/app/shared/model/doc/Document.model';
import {DocumentCriteria} from 'src/app/shared/criteria/doc/DocumentCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
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

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {ExportService} from 'src/app/zynerator/util/Export.service';


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
import {FileUploadModule, UploadEvent} from 'primeng/fileupload';
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {DialogModule} from "primeng/dialog";
import {HttpErrorResponse} from "@angular/common/http";
import {share} from "rxjs";




@Component({
  selector: 'app-document-list-admin',
  templateUrl: './document-list-admin.component.html',
})

export class DocumentListAdminComponent implements OnInit {
    visible: boolean = false;
    protected file2: any;

    showMenu = false;

    toggleMenu() {
        this.showMenu = !this.showMenu;
    }

    showDialog() {
        this.visible = true;
    }
    visibleText: boolean = false;

    showDialogText() {
        this.visibleText = true;
    }
    textExtrait:string;
    language: string;
    uploadedFiles: File[] = [];
    selectedFile: File;
    onFileSelected(event: any) {
        this.uploadedFiles = event.files;
        if (this.uploadedFiles.length > 0) {
            this.selectedFile = this.uploadedFiles[0]; // Assume only one file is needed for OCR
        }
    }
    onUpload() {
        if (this.selectedFile && this.language) {
            this.service.extractTextFromImage(this.selectedFile, this.language).subscribe({
                next: (result) => {
                    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Text extracted successfully' });
                    console.log('Text extracted successfully:', result.text);
                    this.textExtrait = result.text;  // Access the text property
                   // this.visibleText = true;  // Ensure the dialog is shown
                   // this.visible = false;  // Close the scan dialog
                },
                error: (err) => {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Text extraction failed' });
                    console.error('Text extraction failed:', err);
                }
            });
        } else {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Please select a file and specify a language' });
        }
    }





    protected fileName = 'Document';
    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


     yesOrNoFolder: any[] = [];
     yesOrNoArchive: any[] = [];
     yesOrNoVersionne: any[] = [];
    documentTypes: Array<DocumentTypeDto>;
    documentStates: Array<DocumentStateDto>;
    documentCategories: Array<DocumentCategorieDto>;
    proprietaires: Array<UtilisateurDto>;
    entiteAdministratives: Array<EntiteAdministrativeDto>;
    entiteAdministrativeProprietaires: Array<EntiteAdministrativeDto>;


    constructor( private service: DocumentAdminService  , private documentStateService: DocumentStateAdminService, private documentTagService: DocumentTagAdminService, private acessShareService: AcessShareAdminService, private documentFieldService: DocumentFieldAdminService, private groupeService: GroupeAdminService, private documentTypeService: DocumentTypeAdminService, private documentFieldStateService: DocumentFieldStateAdminService, private fieldService: FieldAdminService, private documentPartageGroupeService: DocumentPartageGroupeAdminService, private documentCategorieService: DocumentCategorieAdminService, private documentPartageUtilisateurService: DocumentPartageUtilisateurAdminService, private entiteAdministrativeService: EntiteAdministrativeAdminService, private tagService: TagAdminService, private utilisateurService: UtilisateurAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadDocumentType();
        this.loadDocumentState();
        this.loadDocumentCategorie();
        this.loadProprietaire();
        this.loadEntiteAdministrative();
        this.loadEntiteAdministrativeProprietaire();
        this.yesOrNoFolder =  [{label: 'Folder', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
        this.yesOrNoArchive =  [{label: 'Archive', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
        this.yesOrNoVersionne =  [{label: 'Versionne', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];

    }




    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    console.log('File uploaded successfully!', response);
                },
                error => {
                    console.error('Error uploading file:', error);
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<DocumentDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: DocumentDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: DocumentDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new DocumentDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    this.items = this.items.filter(item => !this.selections.includes(item));
                    this.selections = new Array<DocumentDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: DocumentDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: DocumentDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: DocumentDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new DocumentDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new DocumentDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }



    public initCol() {
        this.cols = [
            {field: 'reference', header: 'Reference'},
            {field: 'uploadDate', header: 'Upload date'},
            {field: 'dateLastUpdate', header: 'Date last update'},
            {field: 'content', header: 'Content'},
            {field: 'folder', header: 'Folder'},
            {field: 'size', header: 'Size'},
            {field: 'documentType?.libelle', header: 'Document type'},
            {field: 'documentState?.libelle', header: 'Document state'},
            {field: 'documentCategorie?.libelle', header: 'Document categorie'},
            {field: 'proprietaire?.id', header: 'Proprietaire'},
            {field: 'archive', header: 'Archive'},
            {field: 'versionne', header: 'Versionne'},
            {field: 'entiteAdministrative?.libelle', header: 'Entite administrative'},
            {field: 'entiteAdministrativeProprietaire?.libelle', header: 'Entite administrative proprietaire'},
        ];
    }


    public async loadDocumentType(){
        this.documentTypeService.findAllOptimized().subscribe(documentTypes => this.documentTypes = documentTypes, error => console.log(error))
    }
    public async loadDocumentState(){
        this.documentStateService.findAllOptimized().subscribe(documentStates => this.documentStates = documentStates, error => console.log(error))
    }
    public async loadDocumentCategorie(){
        this.documentCategorieService.findAllOptimized().subscribe(documentCategories => this.documentCategories = documentCategories, error => console.log(error))
    }
    public async loadProprietaire(){
        this.utilisateurService.findAll().subscribe(proprietaires => this.proprietaires = proprietaires, error => console.log(error))
    }
    public async loadEntiteAdministrative(){
        this.entiteAdministrativeService.findAllOptimized().subscribe(entiteAdministratives => this.entiteAdministratives = entiteAdministratives, error => console.log(error))
    }
    public async loadEntiteAdministrativeProprietaire(){
        this.entiteAdministrativeService.findAllOptimized().subscribe(entiteAdministrativeProprietaires => this.entiteAdministrativeProprietaires = entiteAdministrativeProprietaires, error => console.log(error))
    }


	public initDuplicate(res: DocumentDto) {
        if (res.documentFields != null) {
             res.documentFields.forEach(d => { d.document = null; d.id = null; });
        }
        if (res.documentPartageGroupes != null) {
             res.documentPartageGroupes.forEach(d => { d.document = null; d.id = null; });
        }
        if (res.documentPartageUtilisateurs != null) {
             res.documentPartageUtilisateurs.forEach(d => { d.document = null; d.id = null; });
        }
        if (res.documentTags != null) {
             res.documentTags.forEach(d => { d.document = null; d.id = null; });
        }
	}



   public prepareColumnExport(): void {
        this.exportData = this.items.map(e => {
            return {
                 'Reference': e.reference ,
                'Upload date': this.datePipe.transform(e.uploadDate , 'dd/MM/yyyy hh:mm'),
                'Date last update': this.datePipe.transform(e.dateLastUpdate , 'dd/MM/yyyy hh:mm'),
                 'Content': e.content ,
                'Folder': e.folder? 'Vrai' : 'Faux' ,
                 'Size': e.size ,
                'Document type': e.documentType?.libelle ,
                'Document state': e.documentState?.libelle ,
                'Document categorie': e.documentCategorie?.libelle ,
                 'Description': e.description ,
                'Proprietaire': e.proprietaire?.id ,
                'Archive': e.archive? 'Vrai' : 'Faux' ,
                'Versionne': e.versionne? 'Vrai' : 'Faux' ,
                'Entite administrative': e.entiteAdministrative?.libelle ,
                'Entite administrative proprietaire': e.entiteAdministrativeProprietaire?.libelle ,
            }
        });

        this.criteriaData = [{
            'Reference': this.criteria.reference ? this.criteria.reference : environment.emptyForExport ,
            'Upload date Min': this.criteria.uploadDateFrom ? this.datePipe.transform(this.criteria.uploadDateFrom , this.dateFormat) : environment.emptyForExport ,
            'Upload date Max': this.criteria.uploadDateTo ? this.datePipe.transform(this.criteria.uploadDateTo , this.dateFormat) : environment.emptyForExport ,
            'Date last update Min': this.criteria.dateLastUpdateFrom ? this.datePipe.transform(this.criteria.dateLastUpdateFrom , this.dateFormat) : environment.emptyForExport ,
            'Date last update Max': this.criteria.dateLastUpdateTo ? this.datePipe.transform(this.criteria.dateLastUpdateTo , this.dateFormat) : environment.emptyForExport ,
            'Content': this.criteria.content ? this.criteria.content : environment.emptyForExport ,
            'Folder': this.criteria.folder ? (this.criteria.folder ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
            'Size Min': this.criteria.sizeMin ? this.criteria.sizeMin : environment.emptyForExport ,
            'Size Max': this.criteria.sizeMax ? this.criteria.sizeMax : environment.emptyForExport ,
        //'Document type': this.criteria.documentType?.libelle ? this.criteria.documentType?.libelle : environment.emptyForExport ,
        //'Document state': this.criteria.documentState?.libelle ? this.criteria.documentState?.libelle : environment.emptyForExport ,
        //'Document categorie': this.criteria.documentCategorie?.libelle ? this.criteria.documentCategorie?.libelle : environment.emptyForExport ,
            'Description': this.criteria.description ? this.criteria.description : environment.emptyForExport ,
        //'Proprietaire': this.criteria.proprietaire?.id ? this.criteria.proprietaire?.id : environment.emptyForExport ,
            'Archive': this.criteria.archive ? (this.criteria.archive ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
            'Versionne': this.criteria.versionne ? (this.criteria.versionne ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
        //'Entite administrative': this.criteria.entiteAdministrative?.libelle ? this.criteria.entiteAdministrative?.libelle : environment.emptyForExport ,
        //'Entite administrative proprietaire': this.criteria.entiteAdministrativeProprietaire?.libelle ? this.criteria.entiteAdministrativeProprietaire?.libelle : environment.emptyForExport ,
        }];
      }



    get items(): Array<DocumentDto> {
        return this.service.items;
    }

    set items(value: Array<DocumentDto>) {
        this.service.items = value;
    }

    get selections(): Array<DocumentDto> {
        return this.service.selections;
    }

    set selections(value: Array<DocumentDto>) {
        this.service.selections = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
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

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }

    protected readonly event = event;
    protected readonly share = share;
}
