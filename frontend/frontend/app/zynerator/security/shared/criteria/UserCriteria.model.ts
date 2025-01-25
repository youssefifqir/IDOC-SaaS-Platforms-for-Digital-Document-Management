import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {RoleUserCriteria} from './RoleUserCriteria.model';
import {ModelPermissionUserCriteria} from './ModelPermissionUserCriteria.model';

export class UserCriteria  extends BaseCriteria  {

    public id: number;
    public credentialsNonExpired: null | boolean;
    public enabled: null | boolean;
    public email: string;
    public emailLike: string;
    public accountNonExpired: null | boolean;
    public accountNonLocked: null | boolean;
    public username: string;
    public usernameLike: string;
    public firstName: string;
    public firstNameLike: string;
    public lastName: string;
    public lastNameLike: string;
    public phone: string;
    public phoneLike: string;
    public password: string;
    public passwordLike: string;
    public passwordChanged: null | boolean;
      public modelPermissionUsers: Array<ModelPermissionUserCriteria>;
      public roleUsers: Array<RoleUserCriteria>;

}
