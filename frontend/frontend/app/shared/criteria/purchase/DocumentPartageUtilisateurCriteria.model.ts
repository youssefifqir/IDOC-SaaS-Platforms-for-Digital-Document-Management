import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {AcessShareCriteria} from '../referetiel/AcessShareCriteria.model';
import {DocumentCriteria} from '../doc/DocumentCriteria.model';
import {UtilisateurCriteria} from '../user/UtilisateurCriteria.model';

export class DocumentPartageUtilisateurCriteria  extends BaseCriteria  {

    public id: number;
    public dateShare: Date;
    public dateShareFrom: Date;
    public dateShareTo: Date;

}
