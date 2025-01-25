import {Component, OnInit, Input} from '@angular/core';


import {AbstractEditController} from 'src/app/zynerator/controller/AbstractEditController';

import {ModelPermissionService} from 'src/app/zynerator/security/shared/service/ModelPermission.service';
import {ModelPermissionDto} from 'src/app/zynerator/security/shared/model/ModelPermission.model';
import {ModelPermissionCriteria} from 'src/app/zynerator/security/shared/criteria/ModelPermissionCriteria.model';



@Component({
  selector: 'app-model-permission-edit',
  templateUrl: './model-permission-edit.component.html'
})
export class ModelPermissionEditComponent extends AbstractEditController<ModelPermissionDto, ModelPermissionCriteria, ModelPermissionService>   implements OnInit {


    private _validModelPermissionReference = true;




    constructor( private modelPermissionService: ModelPermissionService ) {
        super(modelPermissionService);
    }

    ngOnInit(): void {
    }


    public override setValidation(value: boolean){
        this.validModelPermissionReference = value;
        }
    public override validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateModelPermissionReference();
    }
    public validateModelPermissionReference(){
        if (this.stringUtilService.isEmpty(this.item.reference)) {
            this.errorMessages.push('Reference non valide');
            this.validModelPermissionReference = false;
        } else {
            this.validModelPermissionReference = true;
        }
    }






    get validModelPermissionReference(): boolean {
        return this._validModelPermissionReference;
    }
    set validModelPermissionReference(value: boolean) {
        this._validModelPermissionReference = value;
    }

}
