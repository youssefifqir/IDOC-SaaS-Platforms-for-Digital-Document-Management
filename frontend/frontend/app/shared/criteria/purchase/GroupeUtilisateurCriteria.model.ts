import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {GroupeCriteria} from '../user/GroupeCriteria.model';
import {UtilisateurCriteria} from '../user/UtilisateurCriteria.model';

export class GroupeUtilisateurCriteria  extends BaseCriteria  {

    public id: number;
  public utilisateur: UtilisateurCriteria ;
  public utilisateurs: Array<UtilisateurCriteria> ;

}
