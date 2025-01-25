package  ma.zs.univ.dao.specification.core.purchase;

import ma.zs.univ.dao.criteria.core.purchase.GroupeUtilisateurCriteria;
import ma.zs.univ.bean.core.purchase.GroupeUtilisateur;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class GroupeUtilisateurSpecification extends  AbstractSpecification<GroupeUtilisateurCriteria, GroupeUtilisateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("groupe","id", criteria.getGroupe()==null?null:criteria.getGroupe().getId());
        addPredicateFk("groupe","id", criteria.getGroupes());
        addPredicateFk("groupe","code", criteria.getGroupe()==null?null:criteria.getGroupe().getCode());
        addPredicateFk("utilisateur","id", criteria.getUtilisateur()==null?null:criteria.getUtilisateur().getId());
        addPredicateFk("utilisateur","id", criteria.getUtilisateurs());
    }

    public GroupeUtilisateurSpecification(GroupeUtilisateurCriteria criteria) {
        super(criteria);
    }

    public GroupeUtilisateurSpecification(GroupeUtilisateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
