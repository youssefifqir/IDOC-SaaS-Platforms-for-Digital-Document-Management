import {Component, OnInit} from '@angular/core';
import {ModelPermissionService} from 'src/app/zynerator/security/shared/service/ModelPermission.service';
import {ModelPermissionDto} from 'src/app/zynerator/security/shared/model/ModelPermission.model';
import {ModelPermissionCriteria} from 'src/app/zynerator/security/shared/criteria/ModelPermissionCriteria.model';
import {AbstractListController} from 'src/app/zynerator/controller/AbstractListController';
import { environment } from 'src/environments/environment';



@Component({
  selector: 'app-model-permission-list',
  templateUrl: './model-permission-list.component.html'
})
export class ModelPermissionListComponent extends AbstractListController<ModelPermissionDto, ModelPermissionCriteria, ModelPermissionService>  implements OnInit {

    override fileName = 'ModelPermission';



    constructor( private modelPermissionService: ModelPermissionService  ) {
        super(modelPermissionService);
    }

     ngOnInit(): void{
         this.activateSecurityConstraint('ModelPermission').subscribe(() => {
            if (this.listActionIsValid) {
                this.findPaginatedByCriteria();
                this.initExport();
                this.initCol();
            }
        });
    }


    public override initCol() {
        this.cols = [
            {field: 'reference', header: 'Reference'},
            {field: 'libelle', header: 'Libelle'},
        ];
    }





   public override prepareColumnExport(): void {
        this.exportData = this.items.map(e => {
            return {
                 'Reference': e.reference ,
                 'Libelle': e.libelle ,
            }
        });

        this.criteriaData = [{
            'Reference': this.criteria.reference ? this.criteria.reference : environment.emptyForExport ,
            'Libelle': this.criteria.libelle ? this.criteria.libelle : environment.emptyForExport ,
        }];
      }
}
