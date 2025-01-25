import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {AcessShareDto} from '../referetiel/AcessShare.model';
import {DocumentDto} from '../doc/Document.model';
import {UtilisateurDto} from '../user/Utilisateur.model';

export class DocumentPartageUtilisateurDto extends BaseDto{

   public dateShare: Date;

    public document: DocumentDto ;
    public utilisateur: UtilisateurDto ;
    public acessShare: AcessShareDto ;
    

    constructor() {
        super();

        this.dateShare = null;

        }

}
