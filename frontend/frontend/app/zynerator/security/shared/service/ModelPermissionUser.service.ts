import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';

import {ModelPermissionUserDto} from 'src/app/zynerator/security/shared/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from 'src/app/zynerator/security/shared/criteria/ModelPermissionUserCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {RoleService} from './Role.service';
import {Observable} from 'rxjs';
import {PaginatedList} from '../../../dto/PaginatedList.model';


@Injectable({
  providedIn: 'root'
})
export class ModelPermissionUserService extends AbstractService<ModelPermissionUserDto, ModelPermissionUserCriteria> {
     constructor(private http: HttpClient, private roleService: RoleService) {
        super();
        this.setHttp(http);
    }

    public initModelPermissionUser(): Observable<Array<ModelPermissionUserDto>> {
        return this.http.get<Array<ModelPermissionUserDto>>(this.API + 'init-model-permission-user');
    }
    public initSecurityModelPermissionUser(): Observable<Array<ModelPermissionUserDto>> {
        return this.http.get<Array<ModelPermissionUserDto>>(this.API + 'init-security-model-permission-user');
    }

    public constrcutDto(): ModelPermissionUserDto {
        return new ModelPermissionUserDto();
    }

    public constrcutCriteria(): ModelPermissionUserCriteria {
        return new ModelPermissionUserCriteria();
    }
    get API() {
        return environment.apiUrl + 'api/modelPermissionUser/';
    }
}
