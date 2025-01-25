package  ma.zs.univ.dao.specification.core.purchase;

import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageUtilisateurCriteria;
import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class DocumentPartageUtilisateurSpecification extends  AbstractSpecification<DocumentPartageUtilisateurCriteria, DocumentPartageUtilisateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateShare", criteria.getDateShare(), criteria.getDateShareFrom(), criteria.getDateShareTo());
        addPredicateFk("document","id", criteria.getDocument()==null?null:criteria.getDocument().getId());
        addPredicateFk("document","id", criteria.getDocuments());
        addPredicateFk("document","reference", criteria.getDocument()==null?null:criteria.getDocument().getReference());
        addPredicateFk("utilisateur","id", criteria.getUtilisateur()==null?null:criteria.getUtilisateur().getId());
        addPredicateFk("utilisateur","id", criteria.getUtilisateurs());
        addPredicateFk("acessShare","id", criteria.getAcessShare()==null?null:criteria.getAcessShare().getId());
        addPredicateFk("acessShare","id", criteria.getAcessShares());
        addPredicateFk("acessShare","code", criteria.getAcessShare()==null?null:criteria.getAcessShare().getCode());
    }

    public DocumentPartageUtilisateurSpecification(DocumentPartageUtilisateurCriteria criteria) {
        super(criteria);
    }

    public DocumentPartageUtilisateurSpecification(DocumentPartageUtilisateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
