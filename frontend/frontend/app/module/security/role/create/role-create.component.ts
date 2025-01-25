import {Component, OnInit, Input} from '@angular/core';

import { AbstractCreateController } from 'src/app/zynerator/controller/AbstractCreateController';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {RoleDto} from 'src/app/zynerator/security/shared/model/Role.model';
import {RoleCriteria} from 'src/app/zynerator/security/shared/criteria/RoleCriteria.model';
@Component({
  selector: 'app-role-create',
  templateUrl: './role-create.component.html'
})
export class RoleCreateComponent extends AbstractCreateController<RoleDto, RoleCriteria, RoleService>  implements OnInit {



   private _validRoleAuthority = true;

    constructor( private roleservice: RoleService ) {
        super(roleservice);
    }

    ngOnInit(): void {
    }





    public override setValidation(value: boolean){
        this.validRoleAuthority = value;
    }



    public override validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateRoleAuthority();
    }

    public validateRoleAuthority(){
        if (this.stringUtilService.isEmpty(this.item.authority)) {
        this.errorMessages.push('Authority non valide');
        this.validRoleAuthority = false;
        } else {
            this.validRoleAuthority = true;
        }
    }






    get validRoleAuthority(): boolean {
        return this._validRoleAuthority;
    }

    set validRoleAuthority(value: boolean) {
         this._validRoleAuthority = value;
    }



}
