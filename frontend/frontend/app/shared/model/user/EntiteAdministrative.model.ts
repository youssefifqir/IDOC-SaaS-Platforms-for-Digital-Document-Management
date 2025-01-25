import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {AbonneDto} from '../abonne/Abonne.model';
import {EntiteAdministrativeTypeDto} from '../doc/EntiteAdministrativeType.model';
import {UtilisateurDto} from './Utilisateur.model';

export class EntiteAdministrativeDto extends BaseDto{

    public code: string;

    public description: string;

    public libelle: string;

    public chef: UtilisateurDto ;
    public entiteAdministrativeType: EntiteAdministrativeTypeDto ;
    public abonne: AbonneDto ;
   public codeEntiteAdminParent: string;


    constructor() {
        super();

        this.code = '';
        this.description = '';
        this.libelle = '';
        this.chef = new UtilisateurDto() ;
        this.entiteAdministrativeType = new EntiteAdministrativeTypeDto() ;

        }

}
