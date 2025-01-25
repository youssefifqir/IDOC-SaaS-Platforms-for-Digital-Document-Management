import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {GroupeDto} from '../user/Groupe.model';
import {UtilisateurDto} from '../user/Utilisateur.model';

export class GroupeUtilisateurDto extends BaseDto{

    public groupe: GroupeDto ;
    public utilisateur: UtilisateurDto ;
    

    constructor() {
        super();

        this.utilisateur = new UtilisateurDto() ;

        }

}
