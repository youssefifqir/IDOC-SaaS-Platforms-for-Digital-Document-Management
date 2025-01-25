import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {AcessShareCriteria} from '../referetiel/AcessShareCriteria.model';
import {GroupeCriteria} from '../user/GroupeCriteria.model';
import {DocumentCriteria} from '../doc/DocumentCriteria.model';

export class DocumentPartageGroupeCriteria  extends BaseCriteria  {

    public id: number;
    public dateShare: Date;
    public dateShareFrom: Date;
    public dateShareTo: Date;
  public acessShare: AcessShareCriteria ;
  public acessShares: Array<AcessShareCriteria> ;

}
