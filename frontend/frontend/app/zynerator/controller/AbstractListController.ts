import {ConfirmationService, MenuItem, MessageService} from 'primeng/api';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Injectable} from '@angular/core';

import {environment} from 'src/environments/environment';

import {ExportService} from 'src/app/zynerator/util/Export.service';
import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {catchError, forkJoin, Observable, of, tap} from "rxjs";


@Injectable()
export class AbstractListController<DTO extends BaseDto, CRITERIA extends BaseCriteria, SERVICE extends AbstractService<DTO, CRITERIA>> {

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected fileName: string;
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected service: SERVICE;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


    constructor(service: SERVICE) {
        this.service = service;
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    init(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
    }
    //

    public activateSecurityConstraint(entityName: string) {
        this.entityName = entityName;
        let createActionPermission = of(true);
        let editActionPermission = of(true);
        let deleteActionPermission = of(true);
        let listActionPermission = of(true);
        let duplicateActionPermission = of(true);
        let viewActionPermission = of(true);
        if (this.enableSecurity){
            createActionPermission = this.hasCreateActionPermission(this.createAction);
            editActionPermission = this.hasEditeActionPermission(this.editAction);
            deleteActionPermission = this.hasDeleteActionPermission(this.deleteAction);
            listActionPermission = this.hasListActionPermission(this.listAction);
            duplicateActionPermission = this.hasDuplicateActionPermission(this.duplicateAction);
            viewActionPermission = this.hasViewActionPermission(this.viewAction);
        }
        else {
            this.createActionIsValid= true;
            this.editActionIsValid= true;
            this.deleteActionIsValid= true;
            this.listActionIsValid= true;
            this.duplicateActionIsValid= true;
            this.viewActionIsValid= true;
        }
        return forkJoin([
            createActionPermission,
            editActionPermission,
            deleteActionPermission,
            listActionPermission,
            duplicateActionPermission,
            viewActionPermission
        ]);
    }


    public hasCreateActionPermission(action: string) {
        const username = this.authService.authenticatedUser.username;
        return this.service.hasActionPermission(username, action).pipe(
            tap(data => this.createActionIsValid = data),
            catchError(error => {
                console.log(error);
                return of(null);
            })
        );
    }
    public hasEditeActionPermission(action: string) {
        const username = this.authService.authenticatedUser.username;
        return this.service.hasActionPermission(username, action).pipe(
            tap(data => this.editActionIsValid = data),
            catchError(error => {
                console.log(error);
                return of(null);
            })
        );
    }

    public hasDeleteActionPermission(action: string) {
        const username = this.authService.authenticatedUser.username;
        return this.service.hasActionPermission(username, action).pipe(
            tap(data => this.deleteActionIsValid = data),
            catchError(error => {
                console.log(error);
                return of(null);
            })
        );
    }

    public hasListActionPermission(action: string) {
        const username = this.authService.authenticatedUser.username;
        return this.service.hasActionPermission(username, action).pipe(
            tap(data => this.listActionIsValid = data),
            catchError(error => {
                console.log(error);
                return of(null);
            })
        );
    }

    public hasDuplicateActionPermission(action: string) {
        const username = this.authService.authenticatedUser.username;
        return this.service.hasActionPermission(username, action).pipe(
            tap(data => this.duplicateActionIsValid = data),
            catchError(error => {
                console.log(error);
                return of(null);
            })
        );
    }

    public hasViewActionPermission(action: string) {
        const username = this.authService.authenticatedUser.username;
        return this.service.hasActionPermission(username, action).pipe(
            tap(data => this.viewActionIsValid = data),
            catchError(error => {
                console.log(error);
                return of(null);
            })
        );
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
            this.selections = new Array<DTO>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public initCol() {
        this.cols = [
            {field: 'code', header: 'Code'},
            {field: 'libelle', header: 'Libelle'},
        ];
    }

    public async edit(dto: DTO) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: DTO) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = this.service.constrcutDto();
        this.createDialog = true;
        this.service.initStepper();
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    this.items = this.items.filter(item => !this.selections.includes(item));
                    this.selections = new Array<DTO>();
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


    public async delete(dto: DTO) {

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

    public async duplicate(dto: DTO) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    public initDuplicate(dto: DTO) {
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

    public exportPdf(dto: DTO): void {
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

    public prepareColumnExport() {
    }

    get items(): Array<DTO> {
        return this.service.items;
    }

    set items(value: Array<DTO>) {
        this.service.items = value;
    }

    get selections(): Array<DTO> {
        return this.service.selections;
    }

    set selections(value: Array<DTO>) {
        this.service.selections = value;
    }

    get item(): DTO {
        return this.service.item;
    }

    set item(value: DTO) {
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

    get criteria(): CRITERIA {
        return this.service.criteria;
    }

    set criteria(value: CRITERIA) {
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
}
