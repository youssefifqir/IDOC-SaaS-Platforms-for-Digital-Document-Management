package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.DocumentAcessShareCriteria;
import ma.zs.univ.bean.core.doc.DocumentAcessShare;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentAcessShareSpecification extends  AbstractSpecification<DocumentAcessShareCriteria, DocumentAcessShare>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("document","id", criteria.getDocument()==null?null:criteria.getDocument().getId());
        addPredicateFk("document","id", criteria.getDocuments());
        addPredicateFk("document","reference", criteria.getDocument()==null?null:criteria.getDocument().getReference());
        addPredicateFk("acessShare","id", criteria.getAcessShare()==null?null:criteria.getAcessShare().getId());
        addPredicateFk("acessShare","id", criteria.getAcessShares());
        addPredicateFk("acessShare","code", criteria.getAcessShare()==null?null:criteria.getAcessShare().getCode());
    }

    public DocumentAcessShareSpecification(DocumentAcessShareCriteria criteria) {
        super(criteria);
    }

    public DocumentAcessShareSpecification(DocumentAcessShareCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
