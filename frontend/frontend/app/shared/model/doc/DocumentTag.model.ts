import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {TagDto} from './Tag.model';
import {DocumentDto} from './Document.model';

export class DocumentTagDto extends BaseDto{

    public document: DocumentDto ;
    public tag: TagDto ;
    

    constructor() {
        super();


        }

}
