import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {FieldDto} from './Field.model';
import {DocumentFieldStateDto} from './DocumentFieldState.model';
import {DocumentDto} from './Document.model';

export class DocumentFieldDto extends BaseDto{

    public value: string;

    public field: FieldDto ;
    public document: DocumentDto ;
    public documentFieldState: DocumentFieldStateDto ;
    

    constructor() {
        super();

        this.value = '';

        }

}
