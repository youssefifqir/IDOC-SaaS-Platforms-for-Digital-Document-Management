package  ma.zs.univ.dao.specification.core.referetiel;

import ma.zs.univ.dao.criteria.core.referetiel.AcessManagementCriteria;
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class AcessManagementSpecification extends  AbstractSpecification<AcessManagementCriteria, AcessManagement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public AcessManagementSpecification(AcessManagementCriteria criteria) {
        super(criteria);
    }

    public AcessManagementSpecification(AcessManagementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
