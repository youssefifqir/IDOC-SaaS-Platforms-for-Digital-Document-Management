import {Component, OnInit} from '@angular/core';
import {ModelPermissionUserService} from 'src/app/zynerator/security/shared/service/ModelPermissionUser.service';
import {ModelPermissionUserDto} from 'src/app/zynerator/security/shared/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from 'src/app/zynerator/security/shared/criteria/ModelPermissionUserCriteria.model';
import {AbstractListController} from 'src/app/zynerator/controller/AbstractListController';
import { environment } from 'src/environments/environment';

import {ModelPermissionDto} from 'src/app/zynerator/security/shared/model/ModelPermission.model';
import {ModelPermissionService} from 'src/app/zynerator/security/shared/service/ModelPermission.service';
import {UserDto} from 'src/app/zynerator/security/shared/model/User.model';
import {UserService} from 'src/app/zynerator/security/shared/service/User.service';
import {ActionPermissionDto} from 'src/app/zynerator/security/shared/model/ActionPermission.model';
import {ActionPermissionService} from 'src/app/zynerator/security/shared/service/ActionPermission.service';


@Component({
  selector: 'app-model-permission-user-list',
  templateUrl: './model-permission-user-list.component.html'
})
export class ModelPermissionUserListComponent extends AbstractListController<ModelPermissionUserDto, ModelPermissionUserCriteria, ModelPermissionUserService>  implements OnInit {

    override fileName = 'ModelPermissionUser';

     yesOrNoValue: any[] = [];
    actionPermissions: Array<ActionPermissionDto>;
    modelPermissions: Array<ModelPermissionDto>;
    users: Array<UserDto>;


    constructor( private modelPermissionUserService: ModelPermissionUserService  , private modelPermissionService: ModelPermissionService, private userService: UserService, private actionPermissionService: ActionPermissionService) {
        super(modelPermissionUserService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadActionPermission();
        this.loadModelPermission();
        this.loadUser();
        this.yesOrNoValue =  [{label: 'Value', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
    }


    public override initCol() {
        this.cols = [
            {field: 'actionPermission?.reference', header: 'Action permission'},
            {field: 'modelPermission?.reference', header: 'Model permission'},
            {field: 'value', header: 'Value'},
            {field: 'user?.email', header: 'User'},
            {field: 'subAttribute', header: 'Sub attribute'},
        ];
    }


    public async loadActionPermission(){
       this.actionPermissionService.findAllOptimized().subscribe(data => this.actionPermissions = data, error => console.log(error));
    }

    public async loadModelPermission(){
       this.modelPermissionService.findAllOptimized().subscribe(data => this.modelPermissions = data, error => console.log(error));
    }

    public async loadUser(){
       this.userService.findAllOptimized().subscribe(users => this.users = users, error => console.log(error));
    }



   public override prepareColumnExport(): void {
        this.exportData = this.items.map(e => {
            return {
                'Action permission': e.actionPermission?.reference ,
                'Model permission': e.modelPermission?.reference ,
                'Value': e.value? 'Vrai' : 'Faux' ,
                'User': e.user?.email ,
                 'Sub attribute': e.subAttribute ,
            }
        });

        this.criteriaData = [{
        //'Action permission': this.criteria.actionPermission?.reference ? this.criteria.actionPermission?.reference : environment.emptyForExport ,
        //'Model permission': this.criteria.modelPermission?.reference ? this.criteria.modelPermission?.reference : environment.emptyForExport ,
            'Value': this.criteria.value ? (this.criteria.value ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
        //'User': this.criteria.user?.email ? this.criteria.user?.email : environment.emptyForExport ,
            'Sub attribute': this.criteria.subAttribute ? this.criteria.subAttribute : environment.emptyForExport ,
        }];
      }
}
