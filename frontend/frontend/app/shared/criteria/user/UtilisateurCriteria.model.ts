import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {AbonneCriteria} from '../abonne/AbonneCriteria.model';

export class UtilisateurCriteria  extends BaseCriteria  {

    public description: string;
    public descriptionLike: string;
    public credentialsNonExpired: null | boolean;
    public enabled: null | boolean;
    public accountNonExpired: null | boolean;
    public accountNonLocked: null | boolean;
    public passwordChanged: null | boolean;
    public username: string;
    public usernameLike: string;
    public password: string;
    public passwordLike: string;

}
