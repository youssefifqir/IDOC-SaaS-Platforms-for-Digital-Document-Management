package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentCategorieCriteria;
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentCategorieSpecification extends  AbstractSpecification<DocumentCategorieCriteria, DocumentCategorie>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public DocumentCategorieSpecification(DocumentCategorieCriteria criteria) {
        super(criteria);
    }

    public DocumentCategorieSpecification(DocumentCategorieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
