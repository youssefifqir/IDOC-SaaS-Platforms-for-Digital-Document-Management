import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {RoleCriteria} from './RoleCriteria.model';
import {UserCriteria} from './UserCriteria.model';

export class RoleUserCriteria  extends BaseCriteria  {

    public id: number;
  public role: RoleCriteria ;
  public roles: Array<RoleCriteria> ;
  public user: UserCriteria ;
  public users: Array<UserCriteria> ;

}
