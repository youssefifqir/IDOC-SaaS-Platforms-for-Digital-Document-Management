package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentFieldStateCriteria;
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentFieldStateSpecification extends  AbstractSpecification<DocumentFieldStateCriteria, DocumentFieldState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public DocumentFieldStateSpecification(DocumentFieldStateCriteria criteria) {
        super(criteria);
    }

    public DocumentFieldStateSpecification(DocumentFieldStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
