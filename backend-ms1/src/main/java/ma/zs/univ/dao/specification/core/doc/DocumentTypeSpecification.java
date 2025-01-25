package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentTypeCriteria;
import ma.zs.univ.bean.core.doc.DocumentType;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentTypeSpecification extends  AbstractSpecification<DocumentTypeCriteria, DocumentType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public DocumentTypeSpecification(DocumentTypeCriteria criteria) {
        super(criteria);
    }

    public DocumentTypeSpecification(DocumentTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
