package  ma.zs.univ.dao.specification.core.purchase;

import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageGroupeCriteria;
import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentPartageGroupeSpecification extends  AbstractSpecification<DocumentPartageGroupeCriteria, DocumentPartageGroupe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateShare", criteria.getDateShare(), criteria.getDateShareFrom(), criteria.getDateShareTo());
        addPredicateFk("document","id", criteria.getDocument()==null?null:criteria.getDocument().getId());
        addPredicateFk("document","id", criteria.getDocuments());
        addPredicateFk("document","reference", criteria.getDocument()==null?null:criteria.getDocument().getReference());
        addPredicateFk("groupe","id", criteria.getGroupe()==null?null:criteria.getGroupe().getId());
        addPredicateFk("groupe","id", criteria.getGroupes());
        addPredicateFk("groupe","code", criteria.getGroupe()==null?null:criteria.getGroupe().getCode());
        addPredicateFk("acessShare","id", criteria.getAcessShare()==null?null:criteria.getAcessShare().getId());
        addPredicateFk("acessShare","id", criteria.getAcessShares());
        addPredicateFk("acessShare","code", criteria.getAcessShare()==null?null:criteria.getAcessShare().getCode());
    }

    public DocumentPartageGroupeSpecification(DocumentPartageGroupeCriteria criteria) {
        super(criteria);
    }

    public DocumentPartageGroupeSpecification(DocumentPartageGroupeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
