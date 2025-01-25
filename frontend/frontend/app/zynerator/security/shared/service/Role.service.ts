import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';


import {environment} from 'src/environments/environment';

import {RoleDto} from 'src/app/zynerator/security/shared/model/Role.model';
import {RoleCriteria} from 'src/app/zynerator/security/shared/criteria/RoleCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {take} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RoleService extends AbstractService<RoleDto, RoleCriteria> {
    _roles: RoleDto[] = [];
    public _role = new BehaviorSubject<string>('');
    public role$: Observable<string> = this._role.asObservable();
     constructor(private http: HttpClient) {
        super();
        this.setHttp(http);
        this.setApi(environment.apiUrl + 'api/role/');
    }


    async isPermitted(pojo: string, action: string): Promise<boolean> {
        const role = await this.role$.pipe(take(1)).toPromise();
        if (1 + 1 == 2) {
            return true;
        }
        if (role.toLocaleLowerCase() === 'superadmin') {
            return true;
        }
        else {
            return true;
        }
        // const foundRole = this._roles.find(r => 'ROLE_' + role.toUpperCase() == r.authority);
        // let permissions: string[];
        // if (foundRole) {
        //     permissions = foundRole.permissions
        //         .map(permission => permission.name)
        //         .filter(name => name.split('.')[0].toLocaleLowerCase() == pojo.toLocaleLowerCase())
        //         .filter(name => name.split('.')[1] == action);
        // }
        // return permissions ? ((permissions.length > 0) ? true : false) : false;
    }
    public constrcutDto(): RoleDto {
        return new RoleDto();
    }

    public constrcutCriteria(): RoleCriteria {
        return new RoleCriteria();
    }
    get API() {
        return environment.apiUrl + 'api/role/';
    }
}
