import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';

import {environment} from 'src/environments/environment';

import {ModelPermissionDto} from 'src/app/zynerator/security/shared/model/ModelPermission.model';
import {ModelPermissionCriteria} from 'src/app/zynerator/security/shared/criteria/ModelPermissionCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {RoleService} from './Role.service';


@Injectable({
  providedIn: 'root'
})
export class ModelPermissionService extends AbstractService<ModelPermissionDto, ModelPermissionCriteria> {
     constructor(private http: HttpClient, private roleService: RoleService) {
        super();
        this.setHttp(http);
    }

    public constrcutDto(): ModelPermissionDto {
        return new ModelPermissionDto();
    }

    public constrcutCriteria(): ModelPermissionCriteria {
        return new ModelPermissionCriteria();
    }
    get API() {
        return environment.apiUrl + 'api/modelPermission/';
    }
}
