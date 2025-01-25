import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
  modelAbonne: any[];
  modelUtilisateur: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{

                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'document properties',
						icon: 'fas fa-file-alt',
						items: [
								  {
									label: 'document fields',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/document-field/list']
								  },
								  {
									label: 'fields',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/field/list']
								  },
								  {
									label: 'type d\'entite administrative',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/entite-administrative-type/list']
								  },
								  {
									label: 'status des documents',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/document-state/list']
								  },
								  {
									label: 'categorie',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/document-categorie/list']
								  },
								  {
									label: 'tags',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/tag/list']
								  },
								  {
									label: 'document type',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/document-type/list']
								  },
								  {
									label: 'status document field',
									icon: 'fa-solid fa-circle-check',
									routerLink: ['/app/admin/doc/document-field-state/list']
								  },
						]
					  },
					  {
						label: 'Gestion des Abonnes',
						icon: 'fas fa-user-friends',
						items: [
								  {
									label: 'Liste des abonnes',
									icon: 'fa-sharp fa-solid fa-check',
									routerLink: ['/app/admin/abonne/abonne/list']
								  },
						]
					  },
					  {
						label: 'Management des documents',
						icon: 'fa-sharp fa-solid fa-folder fa-xl',
						items: [
								  {
									label: 'Liste document',
									icon: 'pi pi-check',
									routerLink: ['/app/admin/doc/document/list']
								  },
						]
					  },
					  {
						label: 'Management des utilisateurs',
						icon: 'fa-solid fa-users fa-xl',
						items: [
                            {
                                label: 'Liste utilisateur',
                                icon: 'fas fa-user',
                                routerLink: ['/app/admin/user/utilisateur/list']
                            },
                            {
                                label: 'Liste entite administrative',
                                icon: 'fas fa-building',
                                routerLink: ['/app/admin/user/entite-administrative/list']
                            },
                            {
                                label: 'Liste groupe',
                                icon: 'fas fa-users',
                                routerLink: ['/app/admin/user/groupe/list']
                            },
						]
					  },
					  {
						label: 'Referentiel',
						icon: 'fas fa-key',
						items: [
								  {
									label: 'Liste d\'acess share',
									icon: 'fa-sharp fa-solid fa-check',
									routerLink: ['/app/admin/referetiel/acess-share/list']
								  },
								  {
									label: 'management d\'acess share',
									icon: 'fa-sharp fa-solid fa-check',
									routerLink: ['/app/admin/referetiel/acess-management/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'fa-solid fa-shield fa-xl',
					  items: [
						  {
							  label: 'List User',
							  icon: 'fa-sharp fa-solid fa-check',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'fa-sharp fa-solid fa-check',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'fa-sharp fa-solid fa-check',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];
    this.modelAbonne =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'document proprties',
						icon: 'fas fa-file-alt',
						items: [
								  {
									label: 'Liste document field',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/document-field/list']
								  },
								  {
									label: 'Liste field',
									icon: 'fas fa-file-signature',
									routerLink: ['/app/abonne/doc/field/list']
								  },
								  {
									label: 'Liste entite administrative type',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/entite-administrative-type/list']
								  },
								  {
									label: 'Liste document state',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/document-state/list']
								  },
								  {
									label: 'Liste document categorie',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/document-categorie/list']
								  },
								  {
									label: 'Liste tag',
									icon: 'fas fa-tag',
									routerLink: ['/app/abonne/doc/tag/list']
								  },
								  {
									label: 'Liste document type',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/document-type/list']
								  },
								  {
									label: 'Liste document field state',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/document-field-state/list']
								  },
						]
					  },
					  {
						label: 'invite your teammates',
						icon: 'fas fa-user-friends',
						items: [
								  {
									label: 'your teammates',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/abonne/abonne/abonne/list']
								  },
						]
					  },
					  {
						label: 'Document Management',
						icon: 'fa-sharp fa-solid fa-folder fa-xl',
						items: [
								  {
									label: 'Liste document',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/doc/document/list']
								  },
						]
					  },
					  {
						label: 'User Management',
						icon: 'fa-solid fa-users fa-xl',
						items: [
								  {
									label: 'Liste utilisateur',
									icon: 'fas fa-user',
									routerLink: ['/app/abonne/user/utilisateur/list']
								  },
								  {
									label: 'Liste entite administrative',
									icon: 'fas fa-building',
									routerLink: ['/app/abonne/user/entite-administrative/list']
								  },
								  {
									label: 'Liste groupe',
									icon: 'fas fa-users',
									routerLink: ['/app/abonne/user/groupe/list']
								  },
						]
					  },
					  {
						label: 'Referentiel',
						icon: 'fas fa-key',
						items: [
								  {
									label: 'Liste acess share',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/referetiel/acess-share/list']
								  },
								  {
									label: 'Liste acess management',
									icon: 'pi pi-check',
									routerLink: ['/app/abonne/referetiel/acess-management/list']
								  },
						]
					  },

				 /*  {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  } */
			]
	    }
    ];
    this.modelUtilisateur =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Collaborator',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste document field',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/document-field/list']
								  },
								  {
									label: 'Liste field',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/field/list']
								  },
								  {
									label: 'Liste entite administrative type',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/entite-administrative-type/list']
								  },
								  {
									label: 'Liste document state',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/document-state/list']
								  },
								  {
									label: 'Liste document categorie',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/document-categorie/list']
								  },
								  {
									label: 'Liste tag',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/tag/list']
								  },
								  {
									label: 'Liste document type',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/document-type/list']
								  },
								  {
									label: 'Liste document field state',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/document-field-state/list']
								  },
						]
					  },
					  {
						label: 'Gestion Abonne',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste abonne',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/utilisateur/abonne/abonne/list']
								  },
						]
					  },
					  {
						label: 'Document Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste document',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/doc/document/list']
								  },
						]
					  },
					  {
						label: 'User Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste utilisateur',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/user/utilisateur/list']
								  },
								  {
									label: 'Liste entite administrative',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/user/entite-administrative/list']
								  },
								  {
									label: 'Liste groupe',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/user/groupe/list']
								  },
						]
					  },
					  {
						label: 'Referentiel',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste acess share',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/referetiel/acess-share/list']
								  },
								  {
									label: 'Liste acess management',
									icon: 'pi pi-check',
									routerLink: ['/app/utilisateur/referetiel/acess-management/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
