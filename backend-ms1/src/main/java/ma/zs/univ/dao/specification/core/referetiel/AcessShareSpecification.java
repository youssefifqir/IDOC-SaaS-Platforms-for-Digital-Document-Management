package  ma.zs.univ.dao.specification.core.referetiel;

import ma.zs.univ.dao.criteria.core.referetiel.AcessShareCriteria;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class AcessShareSpecification extends  AbstractSpecification<AcessShareCriteria, AcessShare>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public AcessShareSpecification(AcessShareCriteria criteria) {
        super(criteria);
    }

    public AcessShareSpecification(AcessShareCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
