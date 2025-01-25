import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {AbonneCriteria} from '../abonne/AbonneCriteria.model';
import {EntiteAdministrativeTypeCriteria} from '../doc/EntiteAdministrativeTypeCriteria.model';
import {UtilisateurCriteria} from './UtilisateurCriteria.model';

export class EntiteAdministrativeCriteria  extends BaseCriteria  {

    public id: number;
    public code: string;
    public codeLike: string;
    public description: string;
    public descriptionLike: string;
    public libelle: string;
    public libelleLike: string;
    private _codeEntiteAdminParent : string;
  public chef: UtilisateurCriteria ;
  public chefs: Array<UtilisateurCriteria> ;
  public entiteAdministrativeType: EntiteAdministrativeTypeCriteria ;
  public entiteAdministrativeTypes: Array<EntiteAdministrativeTypeCriteria> ;

}
