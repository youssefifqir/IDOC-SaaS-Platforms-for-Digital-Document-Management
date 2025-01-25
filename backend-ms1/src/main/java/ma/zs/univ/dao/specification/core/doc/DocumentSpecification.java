package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentCriteria;
import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentSpecification extends  AbstractSpecification<DocumentCriteria, Document>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("uploadDate", criteria.getUploadDate(), criteria.getUploadDateFrom(), criteria.getUploadDateTo());
        addPredicate("dateLastUpdate", criteria.getDateLastUpdate(), criteria.getDateLastUpdateFrom(), criteria.getDateLastUpdateTo());
        addPredicate("content", criteria.getContent(),criteria.getContentLike());
        addPredicateBool("folder", criteria.getFolder());
        addPredicateBigDecimal("size", criteria.getSize(), criteria.getSizeMin(), criteria.getSizeMax());
        addPredicateBool("archive", criteria.getArchive());
        addPredicateBool("versionne", criteria.getVersionne());
        addPredicateFk("documentType","id", criteria.getDocumentType()==null?null:criteria.getDocumentType().getId());
        addPredicateFk("documentType","id", criteria.getDocumentTypes());
        addPredicateFk("documentType","code", criteria.getDocumentType()==null?null:criteria.getDocumentType().getCode());
        addPredicateFk("documentState","id", criteria.getDocumentState()==null?null:criteria.getDocumentState().getId());
        addPredicateFk("documentState","id", criteria.getDocumentStates());
        addPredicateFk("documentState","code", criteria.getDocumentState()==null?null:criteria.getDocumentState().getCode());
        addPredicateFk("documentCategorie","id", criteria.getDocumentCategorie()==null?null:criteria.getDocumentCategorie().getId());
        addPredicateFk("documentCategorie","id", criteria.getDocumentCategories());
        addPredicateFk("documentCategorie","code", criteria.getDocumentCategorie()==null?null:criteria.getDocumentCategorie().getCode());
        addPredicateFk("proprietaire","id", criteria.getProprietaire()==null?null:criteria.getProprietaire().getId());
        addPredicateFk("proprietaire","id", criteria.getProprietaires());
        addPredicateFk("entiteAdministrative","id", criteria.getEntiteAdministrative()==null?null:criteria.getEntiteAdministrative().getId());
        addPredicateFk("entiteAdministrative","id", criteria.getEntiteAdministratives());
        addPredicateFk("entiteAdministrative","code", criteria.getEntiteAdministrative()==null?null:criteria.getEntiteAdministrative().getCode());
        addPredicateFk("entiteAdministrativeProprietaire","id", criteria.getEntiteAdministrativeProprietaire()==null?null:criteria.getEntiteAdministrativeProprietaire().getId());
        addPredicateFk("entiteAdministrativeProprietaire","id", criteria.getEntiteAdministrativeProprietaires());
        addPredicateFk("entiteAdministrativeProprietaire","code", criteria.getEntiteAdministrativeProprietaire()==null?null:criteria.getEntiteAdministrativeProprietaire().getCode());
    }

    public DocumentSpecification(DocumentCriteria criteria) {
        super(criteria);
    }

    public DocumentSpecification(DocumentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
