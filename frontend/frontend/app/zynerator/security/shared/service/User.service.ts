import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';

import {environment} from 'src/environments/environment';

import {UserDto} from 'src/app/zynerator/security/shared/model/User.model';
import {UserCriteria} from 'src/app/zynerator/security/shared/criteria/UserCriteria.model';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {RoleService} from './Role.service';


@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDto, UserCriteria> {
     constructor(private http: HttpClient, private roleService: RoleService) {
        super();
        this.setHttp(http);
    }

    findByUsername(username: string): Observable<UserDto> {
        return this.http.get<UserDto>( this.API + 'Username/' + username);
    }
    public constrcutDto(): UserDto {
        return new UserDto();
    }

    public constrcutCriteria(): UserCriteria {
        return new UserCriteria();
    }
    get API() {
        return environment.apiUrl + 'api/user/';
    }
}
