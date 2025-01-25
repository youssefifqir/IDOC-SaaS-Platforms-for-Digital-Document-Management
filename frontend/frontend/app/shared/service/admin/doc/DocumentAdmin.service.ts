import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {BehaviorSubject, catchError, forkJoin, Observable, throwError} from 'rxjs';

import {environment} from 'src/environments/environment';
import {PaginatedList} from 'src/app/zynerator/dto/PaginatedList.model';
import * as moment from 'moment/moment';

import {DocumentDto} from 'src/app/shared/model/doc/Document.model';
import {DocumentCriteria} from 'src/app/shared/criteria/doc/DocumentCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {MinIOInfos} from "../../../model/doc/MinIOInfos.model";
import {error} from "protractor";


@Injectable({
  providedIn: 'root'
})
export class DocumentAdminService {

    private baseUrl = ' http://localhost:8036/api/minio/upload/file/ged';
    private ocrUrl='http://localhost:8090/api/ocr/extract/img';

    uploadFile(file: File, bucket: string): Observable<any> {
        const formData: FormData = new FormData();
        formData.append('file', file);
        const headers = new HttpHeaders({
            'bucket': bucket
        });
        return this.http.post(`${this.baseUrl}/upload/file/ged`, formData, { headers });
    }


    uploadFilesToMinio(files: File[]): Observable<MinIOInfos[]> {
        if (!files || files.length === 0) {
            return throwError(() => new Error("No files to upload"));
        }
        const formDataArray: FormData[] = files.map(file => {
            const formData = new FormData();
            formData.append('files', file);
            return formData;
        });
        const requests = formDataArray.map(formData =>
            this.http.post<MinIOInfos>("http://localhost:8036/api/minio/upload/files/yarbi", formData)
        );
        return forkJoin(requests);
    }

    uploadFileToMinioWithGeneratedPath(file: File, entiteAdministrativeCode: string, username: string): Observable<number> {
        if (!file) {
            return throwError(() => new Error("No file to upload"));
        }
        const formData = new FormData();
        formData.append('file', file);
        formData.append('entiteAdministrativeCode', entiteAdministrativeCode);
        formData.append('username', username);

        return this.http.post<number>("http://localhost:8036/api/minio/upload/file-with-path-generated", formData)
            .pipe(
                catchError(error => {
                    console.error('Upload failed', error);
                    return throwError(() => new Error('Upload failed'));
                })
            );
    }


    extractTextFromImage(image: File, language: string): Observable<{ text: string }> {
        const formData = new FormData();
        formData.append('Image', image);
        formData.append('DestinationLanguage', language);

        return this.http.post<{ text: string }>('http://localhost:8090/api/ocr/extract/img', formData);
    }


    private handleError(error: HttpErrorResponse) {
        console.error('An error occurred:', error);
        return throwError(() => new Error('Something went wrong; please try again later.'));
    }


























    protected _API = '';
    protected _items: Array<DocumentDto>;
    protected _item: DocumentDto;
    protected _selections: Array<DocumentDto>;
    protected _createDialog: boolean;
    protected _editDialog: boolean;
    protected _viewDialog: boolean;
    protected _criteria: DocumentCriteria;
    protected _validate = false;

    private _createActionIsValid = true;
    private _editActionIsValid = true;
    private _listActionIsValid = true;
    private _deleteActionIsValid = true;
    private _viewActionIsValid = true;
    private _duplicateActionIsValid = true;


    private _createAction = 'create';
    private _listAction = 'list';
    private _editAction = 'edit';
    private _deleteAction = 'delete';
    private _viewAction = 'view';
    private _duplicateAction = 'duplicate';
    private _entityName: string;

    protected API_PERMISSION: string ;





    constructor(private http: HttpClient) {
        this.API_PERMISSION = environment.apiUrl + 'modelPermissionUser/';
    }


    public findAll() {
        return this.http.get<Array<DocumentDto>>(this.API);
    }

    public findAllOptimized() {
        return this.http.get<Array<DocumentDto>>(this.API + 'optimized');
    }

    public findPaginatedByCriteria(criteria: DocumentCriteria): Observable<PaginatedList<DocumentDto>> {
        return this.http.post<PaginatedList<DocumentDto>>(this.API + 'find-paginated-by-criteria', criteria);
    }

    public save(): Observable<DocumentDto> {
        return this.http.post<DocumentDto>(this.API, this.item);
    }

    public delete(dto: DocumentDto) {
        return this.http.delete<number>(this.API + 'id/' + dto.id);
    }


    public edit(): Observable<DocumentDto> {
        return this.http.put<DocumentDto>(this.API, this.item);
    }


    public findByCriteria(criteria: DocumentCriteria): Observable<Array<DocumentDto>> {
        return this.http.post<Array<DocumentDto>>(this.API + 'find-by-criteria', criteria);
    }

    public findByIdWithAssociatedList(item: DocumentDto): Observable<DocumentDto> {
        return this.http.get<DocumentDto>(this.API + 'id/' + item.id);
    }

    public deleteMultiple() {
        return this.http.post<void>(this.API + 'multiple', this.selections);
    }
    public exportPdf(element: DocumentDto): Observable<ArrayBuffer> {
        return this.http.post(this.API + 'exportPdf/', element, {responseType: 'arraybuffer'});
    }

    public hasActionPermission(username: string, actionReference: string): Observable<boolean> {
        // tslint:disable-next-line:max-line-length
        return this.http.get<boolean>(this.API_PERMISSION + 'user/' + username + '/model/' + this.entityName + '/action/' + actionReference);
    }

    public importExcel(file: File): Observable<Array<DocumentDto>> {
        const formData: FormData = new FormData();
        formData.append('file', file, file.name);
        return this.http.post<Array<DocumentDto>>(this.API + 'import-excel', formData);
    }



    public format(myDate: Date): Date {
        if (myDate != null) {
            const newdate = new Date(myDate);
            const formattedDate = moment(newdate).format(environment.dateFormatEdit);
            console.log(formattedDate);
            myDate = new Date(formattedDate);
        }
        return myDate;
    }

    get API() {
        return environment.apiUrlGedservice + 'admin/document/';
    }

    public get items(): Array<DocumentDto> {
        if (this._items == null) {
            this._items = new Array<DocumentDto>();
        }
        return this._items;
    }

    public set items(value: Array<DocumentDto>) {
        this._items = value;
    }

    public get item(): DocumentDto {
        if (this._item == null) {
            this._item = new DocumentDto();
        }
        return this._item;
    }

    public set item(value: DocumentDto) {
        this._item = value;
    }

    public get selections(): Array<DocumentDto> {
        if (this._selections == null) {
            this._selections = new Array<DocumentDto>();
        }
        return this._selections;
    }


    public set selections(value: Array<DocumentDto>) {
        this._selections = value;
    }

    public get createDialog(): boolean {
        return this._createDialog;
    }

    public set createDialog(value: boolean) {
        this._createDialog = value;
    }

    public get editDialog(): boolean {
        return this._editDialog;
    }

    public set editDialog(value: boolean) {
        this._editDialog = value;
    }

    public get viewDialog(): boolean {
        return this._viewDialog;
    }

    public set viewDialog(value: boolean) {
        this._viewDialog = value;
    }

    public get criteria(): DocumentCriteria {
        if (this._criteria == null) {
            this._criteria = new DocumentCriteria();
        }
        return this._criteria;
    }

    public set criteria(value: DocumentCriteria) {
        this._criteria = value;
    }


    public setApi(API: string) {
        this._API = API;
    }

    set validate(value: boolean) {
        this._validate = value;
    }


    get createAction(): string {
        return this._createAction;
    }

    set createAction(value: string) {
        this._createAction = value;
    }

    get listAction(): string {
        return this._listAction;
    }

    set listAction(value: string) {
        this._listAction = value;
    }

    get editAction(): string {
        return this._editAction;
    }

    set editAction(value: string) {
        this._editAction = value;
    }

    get deleteAction(): string {
        return this._deleteAction;
    }

    set deleteAction(value: string) {
        this._deleteAction = value;
    }

    get createActionIsValid(): boolean {
        return this._createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this._createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this._editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this._editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this._listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this._listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this._deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this._deleteActionIsValid = value;
    }

    get viewAction(): string {
        return this._viewAction;
    }

    set viewAction(value: string) {
        this._viewAction = value;
    }

    get duplicateAction(): string {
        return this._duplicateAction;
    }

    set duplicateAction(value: string) {
        this._duplicateAction = value;
    }

    get viewActionIsValid(): boolean {
        return this._viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this._viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this._duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this._duplicateActionIsValid = value;
    }

    get entityName(): string {
        return this._entityName;
    }

    set entityName(value: string) {
        this._entityName = value;
    }




}
