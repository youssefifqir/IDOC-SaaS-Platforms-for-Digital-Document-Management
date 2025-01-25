import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {ModelPermissionCriteria} from './ModelPermissionCriteria.model';
import {UserCriteria} from './UserCriteria.model';
import {ActionPermissionCriteria} from './ActionPermissionCriteria.model';

export class ModelPermissionUserCriteria  extends BaseCriteria  {

    public id: number;
    public value: null | boolean;
    public subAttribute: string;
    public subAttributeLike: string;
  public actionPermission: ActionPermissionCriteria ;
  public actionPermissions: Array<ActionPermissionCriteria> ;
  public modelPermission: ModelPermissionCriteria ;
  public modelPermissions: Array<ModelPermissionCriteria> ;
  public user: UserCriteria ;
  public users: Array<UserCriteria> ;

}
