import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {PaginatedList} from 'src/app/zynerator/dto/PaginatedList.model';
import {MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import * as moment from 'moment/moment';
import {environment} from 'src/environments/environment';
import {ScheduleDto} from '../dto/ScheduleDto.model';



export abstract class AbstractService<DTO extends BaseDto, CRITERIA extends BaseCriteria> {
    protected _API = '';
    protected _items: Array<DTO>;
    private httpClient: HttpClient;
    protected _item: DTO;
    protected _selections: Array<DTO>;
    protected _createDialog: boolean;
    protected _editDialog: boolean;
    protected _viewDialog: boolean;
    protected _criteria: CRITERIA;
    protected _index = 0;
    protected _back = false;
    protected _next = true;
    protected _validate = false;
    protected _steps: MenuItem[];
    protected _fileTempDtosForOne: FileTempDto[];
    protected _fileTempDtos: Array<FileTempDto[]>;

    private _createActionIsValid = false;
    private _editActionIsValid = false;
    private _listActionIsValid = false;
    private _deleteActionIsValid = false;
    private _viewActionIsValid = false;
    private _duplicateActionIsValid = false;


    private _createAction = 'create';
    private _listAction = 'list';
    private _editAction = 'edit';
    private _deleteAction = 'delete';
    private _viewAction = 'view';
    private _duplicateAction = 'duplicate';
    private _entityName: string;

    protected API_PERMISSION: string ;


    constructor() {
        this.API_PERMISSION = environment.apiUrl + 'modelPermissionUser/';
    }

    public hasActionPermission(username: string, actionReference: string): Observable<boolean> {
        // tslint:disable-next-line:max-line-length
        return this.httpClient.get<boolean>(this.API_PERMISSION + 'user/' + username + '/model/' + this.entityName + '/action/' + actionReference);
    }


    public findSchedule(startTime: string, endTime: string): Observable<Array<ScheduleDto>> {
        return this.httpClient.get<Array<ScheduleDto>>(this.API + 'start/' + startTime + '/end/' + endTime);
    }

    public importExcel(file: File): Observable<Array<DTO>> {
        const formData: FormData = new FormData();
        formData.append('file', file, file.name);
        return this.httpClient.post<Array<DTO>>(this.API + 'import-excel', formData);
    }

    public findByMonth(month: number): Observable<Array<ScheduleDto>> {
        return this.httpClient.get<Array<ScheduleDto>>(this.API + 'month/' + month);
    }

    public initStepper(): void {
        this.index = 0;
        this.back = false;
        this.next = true;
        this.validate = false;
    }

    private performNextOrBack(step: number) {
        if (this.index + step < this.steps.length && this.index + step >= 0) {
            this.index += step;
            this.manageNextAndBack();
        }
    }

    public performNext(): void {
        this.performNextOrBack(1);
    }

    public performBack(): void {
        this.performNextOrBack(-1);
    }

    public manageNextAndBack() {
        this.index === 0 ? this.back = false : this.back = true;
        this.index === this.steps.length - 1 ? this.next = false : this.next = true;
        this.index === this.steps.length - 1 ? this.validate = true : this.validate = false;
    }


    public findPaginatedByCriteria(criteria: CRITERIA): Observable<PaginatedList<DTO>> {
        return this.httpClient.post<PaginatedList<DTO>>(this.API + 'find-paginated-by-criteria', criteria);
    }

    public findAll() {
        return this.httpClient.get<Array<DTO>>(this.API);
    }

    public findAllOptimized() {
        return this.httpClient.get<Array<DTO>>(this.API + 'optimized');
    }

    public save(): Observable<DTO> {
        return this.httpClient.post<DTO>(this.API, this.item);
    }

    public delete(dto: DTO) {
        return this.httpClient.delete<number>(this.API + 'id/' + dto.id);
    }


    public edit(): Observable<DTO> {
        return this.httpClient.put<DTO>(this.API, this.item);
    }


    public findByCriteria(criteria: CRITERIA): Observable<Array<DTO>> {
        return this.httpClient.post<Array<DTO>>(this.API + 'find-by-criteria', criteria);
    }

    public findByIdWithAssociatedList(item: DTO): Observable<DTO> {
        return this.httpClient.get<DTO>(this.API + 'id/' + item.id);
    }

    public deleteMultiple() {
        return this.httpClient.post<void>(this.API + 'multiple', this.selections);
    }

    public upload(formData: FormData, i: number) {
        this.httpClient.post<FileTempDto>(this.API + 'upload', formData).subscribe(data => this.fileTempDtosForOne[i] = data);
    }

    public uploadMultiple(formData: FormData, i: number) {
        this.httpClient.post<FileTempDto[]>(this.API + 'upload-multiple', formData).subscribe(data => this.fileTempDtos[i] = data);

    }

    public exportPdf(element: DTO): Observable<ArrayBuffer> {
        return this.httpClient.post(this.API + 'exportPdf/', element, {responseType: 'arraybuffer'});
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

    public get items(): Array<DTO> {
        if (this._items == null) {
            this._items = new Array<DTO>();
        }
        return this._items;
    }

    public set items(value: Array<DTO>) {
        this._items = value;
    }

    public get item(): DTO {
        if (this._item == null) {
            this._item = this.constrcutDto();
            //TODO : this._item = new DTO();
        }
        return this._item;
    }

    public set item(value: DTO) {
        this._item = value;
    }

    public get selections(): Array<DTO> {
        if (this._selections == null) {
            this._selections = new Array<DTO>();
        }
        return this._selections;
    }


    public set selections(value: Array<DTO>) {
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

    public get criteria(): CRITERIA {
        if (this._criteria == null) {
            this._criteria = this.constrcutCriteria();
            //TODO : this._criteria = new CRITERIA();
        }
        return this._criteria;
    }

    public set criteria(value: CRITERIA) {
        this._criteria = value;
    }

    public abstract constrcutDto(): DTO;

    public abstract constrcutCriteria(): CRITERIA;

    public setApi(API: string) {
        this._API = API;
    }

    public abstract get API() ;

    public setHttp(http: HttpClient) {
        this.httpClient = http;
    }


    get index(): number {
        return this._index;
    }

    set index(value: number) {
        this._index = value;
    }

    get back(): boolean {
        return this._back;
    }

    set back(value: boolean) {
        this._back = value;
    }

    get next(): boolean {
        return this._next;
    }

    set next(value: boolean) {
        this._next = value;
    }

    get validate(): boolean {
        return this._validate;
    }

    set validate(value: boolean) {
        this._validate = value;
    }

    get steps(): MenuItem[] {
        return this._steps;
    }

    set steps(value: MenuItem[]) {
        this._steps = value;
    }

    get fileTempDtos(): Array<FileTempDto[]> {
        if (this._fileTempDtos == null) {
            this._fileTempDtos = new Array<FileTempDto[]>();
        }
        return this._fileTempDtos;
    }

    set fileTempDtos(value: Array<FileTempDto[]>) {
        this._fileTempDtos = value;
    }

    get fileTempDtosForOne(): FileTempDto[] {
        if (this._fileTempDtosForOne == null) {
            this._fileTempDtosForOne = new Array<FileTempDto>();
        }
        return this._fileTempDtosForOne;
    }

    set fileTempDtosForOne(value: FileTempDto[]) {
        this._fileTempDtosForOne = value;
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
