import {Component, OnInit, Input} from '@angular/core';

import { AbstractCreateController } from 'src/app/zynerator/controller/AbstractCreateController';

import {ActionPermissionService} from 'src/app/zynerator/security/shared/service/ActionPermission.service';
import {ActionPermissionDto} from 'src/app/zynerator/security/shared/model/ActionPermission.model';
import {ActionPermissionCriteria} from 'src/app/zynerator/security/shared/criteria/ActionPermissionCriteria.model';
@Component({
  selector: 'app-action-permission-create',
  templateUrl: './action-permission-create.component.html'
})
export class ActionPermissionCreateComponent extends AbstractCreateController<ActionPermissionDto, ActionPermissionCriteria, ActionPermissionService>  implements OnInit {



   private _validActionPermissionReference = true;

    constructor( private actionPermissionService: ActionPermissionService ) {
        super(actionPermissionService);
    }

    ngOnInit(): void {
    }





    public override setValidation(value: boolean){
        this.validActionPermissionReference = value;
    }



    public override validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateActionPermissionReference();
    }

    public validateActionPermissionReference(){
        if (this.stringUtilService.isEmpty(this.item.reference)) {
        this.errorMessages.push('Reference non valide');
        this.validActionPermissionReference = false;
        } else {
            this.validActionPermissionReference = true;
        }
    }






    get validActionPermissionReference(): boolean {
        return this._validActionPermissionReference;
    }

    set validActionPermissionReference(value: boolean) {
         this._validActionPermissionReference = value;
    }



}
