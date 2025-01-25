import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {DocumentStateCriteria} from './DocumentStateCriteria.model';
import {DocumentPartageGroupeCriteria} from '../purchase/DocumentPartageGroupeCriteria.model';
import {DocumentTagCriteria} from './DocumentTagCriteria.model';
import {DocumentCategorieCriteria} from './DocumentCategorieCriteria.model';
import {DocumentFieldCriteria} from './DocumentFieldCriteria.model';
import {DocumentPartageUtilisateurCriteria} from '../purchase/DocumentPartageUtilisateurCriteria.model';
import {DocumentTypeCriteria} from './DocumentTypeCriteria.model';
import {EntiteAdministrativeCriteria} from '../user/EntiteAdministrativeCriteria.model';
import {UtilisateurCriteria} from '../user/UtilisateurCriteria.model';

export class DocumentCriteria  extends BaseCriteria  {

    public id: number;
    public reference: string;
    public referenceLike: string;
    public uploadDate: Date;
    public uploadDateFrom: Date;
    public uploadDateTo: Date;
    public dateLastUpdate: Date;
    public dateLastUpdateFrom: Date;
    public dateLastUpdateTo: Date;
    public content: string;
    public contentLike: string;
    public folder: null | boolean;
     public size: number;
     public sizeMin: number;
     public sizeMax: number;
    public description: string;
    public descriptionLike: string;
    public archive: null | boolean;
    public versionne: null | boolean;
  public documentState: DocumentStateCriteria ;
  public documentStates: Array<DocumentStateCriteria> ;
      public documentFields: Array<DocumentFieldCriteria>;
      public documentPartageGroupes: Array<DocumentPartageGroupeCriteria>;
      public documentPartageUtilisateurs: Array<DocumentPartageUtilisateurCriteria>;
      public documentTags: Array<DocumentTagCriteria>;

}
