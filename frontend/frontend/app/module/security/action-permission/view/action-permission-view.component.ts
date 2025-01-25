import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/zynerator/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {ActionPermissionService} from 'src/app/zynerator/security/shared/service/ActionPermission.service';
import {ActionPermissionDto} from 'src/app/zynerator/security/shared/model/ActionPermission.model';
import {ActionPermissionCriteria} from 'src/app/zynerator/security/shared/criteria/ActionPermissionCriteria.model';

@Component({
  selector: 'app-action-permission-view',
  templateUrl: './action-permission-view.component.html'
})
export class ActionPermissionViewComponent extends AbstractViewController<ActionPermissionDto, ActionPermissionCriteria, ActionPermissionService> implements OnInit {


    constructor(private actionPermissionService: ActionPermissionService){
        super(actionPermissionService);
    }

    ngOnInit(): void {
    }




}
