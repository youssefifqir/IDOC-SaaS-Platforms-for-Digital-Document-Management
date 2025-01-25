import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {RoleDto} from './Role.model';
import {UserDto} from './User.model';

export class RoleUserDto extends BaseDto {

    public role: RoleDto;
    public user: UserDto;


    constructor() {
        super();

        this.role = new RoleDto();
        this.user = new UserDto();

    }

}
