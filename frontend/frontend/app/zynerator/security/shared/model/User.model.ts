import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {RoleUserDto} from './RoleUser.model';
import {ModelPermissionUserDto} from './ModelPermissionUser.model';

export class UserDto extends BaseDto {

    public credentialsNonExpired: null | boolean;

    public enabled: null | boolean;

    public email: string;
    public accountNonExpired: null | boolean;
    public firstName: string;
    public lastName: string;
    public phone: string;

    public accountNonLocked: null | boolean;

    public username: string;

    public password: string;

    public passwordChanged: null | boolean;

    public modelPermissionUsers: Array<ModelPermissionUserDto>;
    public roleUsers: Array<RoleUserDto>;
    public createdAt: Date;
    public updatedAt: Date;

    public newPassword: string;
    public confirmPassword: string;


    constructor() {
        super();

        this.credentialsNonExpired = null;
        this.enabled = null;
        this.email = '';
        this.accountNonExpired = null;
        this.accountNonLocked = null;
        this.username = '';
        this.password = '';
        this.passwordChanged = null;
        this.modelPermissionUsers = new Array<ModelPermissionUserDto>();
        this.roleUsers = new Array<RoleUserDto>();

    }

}
