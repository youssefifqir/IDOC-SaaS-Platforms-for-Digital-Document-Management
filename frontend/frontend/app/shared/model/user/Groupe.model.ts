import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {GroupeUtilisateurDto} from '../purchase/GroupeUtilisateur.model';
import {UtilisateurDto} from './Utilisateur.model';

export class GroupeDto extends BaseDto{

    public code: string;

    public libelle: string;

    public utilisateur: UtilisateurDto ;
     public groupeUtilisateurs: Array<GroupeUtilisateurDto>;
    

    constructor() {
        super();

        this.code = '';
        this.libelle = '';
        this.utilisateur = new UtilisateurDto() ;
        this.groupeUtilisateurs = new Array<GroupeUtilisateurDto>();

        }

}
