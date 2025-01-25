import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';

import {DocumentStateDto} from './DocumentState.model';
import {DocumentPartageGroupeDto} from '../purchase/DocumentPartageGroupe.model';
import {DocumentTagDto} from './DocumentTag.model';
import {DocumentCategorieDto} from './DocumentCategorie.model';
import {DocumentFieldDto} from './DocumentField.model';
import {DocumentPartageUtilisateurDto} from '../purchase/DocumentPartageUtilisateur.model';
import {DocumentTypeDto} from './DocumentType.model';
import {EntiteAdministrativeDto} from '../user/EntiteAdministrative.model';
import {UtilisateurDto} from '../user/Utilisateur.model';

export class DocumentDto extends BaseDto{

    public reference: string;

   public uploadDate: Date;

   public dateLastUpdate: Date;

    public content: string;

   public folder: null | boolean;

    public size: null | number;

    public description: string;

   public archive: null | boolean;

   public versionne: null | boolean;

    public documentType: DocumentTypeDto ;
    public documentState: DocumentStateDto ;
    public documentCategorie: DocumentCategorieDto ;
    public proprietaire: UtilisateurDto ;
    public entiteAdministrative: EntiteAdministrativeDto ;
    public entiteAdministrativeProprietaire: EntiteAdministrativeDto ;
     public documentFields: Array<DocumentFieldDto>;
     public documentPartageGroupes: Array<DocumentPartageGroupeDto>;
     public documentPartageUtilisateurs: Array<DocumentPartageUtilisateurDto>;
     public documentTags: Array<DocumentTagDto>;
    

    constructor() {
        super();

        this.reference = '';
        this.uploadDate = null;
        this.dateLastUpdate = null;
        this.content = '';
        this.folder = null;
        this.size = null;
        this.description = '';
        this.archive = null;
        this.versionne = null;
        this.documentState = new DocumentStateDto() ;
        this.documentFields = new Array<DocumentFieldDto>();
        this.documentPartageGroupes = new Array<DocumentPartageGroupeDto>();
        this.documentPartageUtilisateurs = new Array<DocumentPartageUtilisateurDto>();
        this.documentTags = new Array<DocumentTagDto>();

        }

}
