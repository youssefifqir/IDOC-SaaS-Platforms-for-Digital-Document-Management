import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';

import {ActionPermissionDto} from 'src/app/zynerator/security/shared/model/ActionPermission.model';
import {ActionPermissionCriteria} from 'src/app/zynerator/security/shared/criteria/ActionPermissionCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {RoleService} from './Role.service';


@Injectable({
  providedIn: 'root'
})
export class ActionPermissionService extends AbstractService<ActionPermissionDto, ActionPermissionCriteria> {
     constructor(private http: HttpClient, private roleService: RoleService) {
        super();
        this.setHttp(http);
    }

    public constrcutDto(): ActionPermissionDto {
        return new ActionPermissionDto();
    }

    public constrcutCriteria(): ActionPermissionCriteria {
        return new ActionPermissionCriteria();
    }
    get API() {
        return environment.apiUrl + 'api/actionPermission/';
    }
}
