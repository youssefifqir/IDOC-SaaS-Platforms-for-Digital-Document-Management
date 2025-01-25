import {Component, OnInit} from '@angular/core';
import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {RoleDto} from 'src/app/zynerator/security/shared/model/Role.model';
import {RoleCriteria} from 'src/app/zynerator/security/shared/criteria/RoleCriteria.model';
import {AbstractListController} from 'src/app/zynerator/controller/AbstractListController';
import { environment } from 'src/environments/environment';



@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html'
})
export class RoleListComponent extends AbstractListController<RoleDto, RoleCriteria, RoleService>  implements OnInit {

    override fileName = 'Role';



    constructor( private roleservice: RoleService  ) {
        super(roleservice);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
    }


    public override initCol() {
        this.cols = [
            {field: 'authority', header: 'Authority'},
        ];
    }





   public override prepareColumnExport(): void {
        this.exportData = this.items.map(e => {
            return {
                 'Authority': e.authority ,
            }
        });

        this.criteriaData = [{
            'Authority': this.criteria.authority ? this.criteria.authority : environment.emptyForExport ,
        }];
      }
}
