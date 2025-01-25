import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {EntiteAdministrativeDto} from '../user/EntiteAdministrative.model';

export class AbonneDto extends BaseDto{

    public description: string;

   public credentialsNonExpired: null | boolean;

   public enabled: null | boolean;

   public accountNonExpired: null | boolean;

   public accountNonLocked: null | boolean;

   public passwordChanged: null | boolean;

    public username: string;

    public password: string;

    public entiteAdministrative: EntiteAdministrativeDto ;
    

    constructor() {
        super();

        this.description = '';
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.accountNonExpired = null;
        this.accountNonLocked = null;
        this.passwordChanged = null;
        this.username = '';
        this.password = '';
        this.entiteAdministrative = new EntiteAdministrativeDto() ;

        }

}
