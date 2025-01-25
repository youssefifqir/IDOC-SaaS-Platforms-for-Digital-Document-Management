import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {AcessShareDto} from '../referetiel/AcessShare.model';
import {DocumentDto} from './Document.model';

export class DocumentAcessShareDto extends BaseDto{

    public document: DocumentDto ;
    public acessShare: AcessShareDto ;
    

    constructor() {
        super();

        this.document = new DocumentDto() ;
        this.acessShare = new AcessShareDto() ;

        }

}
