package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentStateCriteria;
import ma.zs.univ.bean.core.doc.DocumentState;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentStateSpecification extends  AbstractSpecification<DocumentStateCriteria, DocumentState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public DocumentStateSpecification(DocumentStateCriteria criteria) {
        super(criteria);
    }

    public DocumentStateSpecification(DocumentStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
