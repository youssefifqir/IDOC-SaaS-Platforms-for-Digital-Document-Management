package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentTagCriteria;
import ma.zs.univ.bean.core.doc.DocumentTag;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentTagSpecification extends  AbstractSpecification<DocumentTagCriteria, DocumentTag>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("document","id", criteria.getDocument()==null?null:criteria.getDocument().getId());
        addPredicateFk("document","id", criteria.getDocuments());
        addPredicateFk("document","reference", criteria.getDocument()==null?null:criteria.getDocument().getReference());
        addPredicateFk("tag","id", criteria.getTag()==null?null:criteria.getTag().getId());
        addPredicateFk("tag","id", criteria.getTags());
        addPredicateFk("tag","code", criteria.getTag()==null?null:criteria.getTag().getCode());
    }

    public DocumentTagSpecification(DocumentTagCriteria criteria) {
        super(criteria);
    }

    public DocumentTagSpecification(DocumentTagCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
