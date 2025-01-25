import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {FieldCriteria} from './FieldCriteria.model';
import {DocumentFieldStateCriteria} from './DocumentFieldStateCriteria.model';
import {DocumentCriteria} from './DocumentCriteria.model';

export class DocumentFieldCriteria  extends BaseCriteria  {

    public id: number;
    public value: string;
    public valueLike: string;

}
