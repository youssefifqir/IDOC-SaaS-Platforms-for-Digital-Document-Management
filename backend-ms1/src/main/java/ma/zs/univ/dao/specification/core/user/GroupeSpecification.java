package  ma.zs.univ.dao.specification.core.user;

import ma.zs.univ.dao.criteria.core.user.GroupeCriteria;
import ma.zs.univ.bean.core.user.Groupe;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class GroupeSpecification extends  AbstractSpecification<GroupeCriteria, Groupe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("utilisateur","id", criteria.getUtilisateur()==null?null:criteria.getUtilisateur().getId());
        addPredicateFk("utilisateur","id", criteria.getUtilisateurs());
    }

    public GroupeSpecification(GroupeCriteria criteria) {
        super(criteria);
    }

    public GroupeSpecification(GroupeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
