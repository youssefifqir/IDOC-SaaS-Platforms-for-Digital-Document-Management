import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {AcessShareDto} from '../referetiel/AcessShare.model';
import {GroupeDto} from '../user/Groupe.model';
import {DocumentDto} from '../doc/Document.model';

export class DocumentPartageGroupeDto extends BaseDto{

   public dateShare: Date;

    public document: DocumentDto ;
    public groupe: GroupeDto ;
    public acessShare: AcessShareDto ;
    

    constructor() {
        super();

        this.dateShare = null;
        this.acessShare = new AcessShareDto() ;

        }

}
