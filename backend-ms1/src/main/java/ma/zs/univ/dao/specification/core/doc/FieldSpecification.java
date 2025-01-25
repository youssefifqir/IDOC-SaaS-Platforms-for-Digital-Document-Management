package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.FieldCriteria;
import ma.zs.univ.bean.core.doc.Field;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class FieldSpecification extends  AbstractSpecification<FieldCriteria, Field>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public FieldSpecification(FieldCriteria criteria) {
        super(criteria);
    }

    public FieldSpecification(FieldCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
