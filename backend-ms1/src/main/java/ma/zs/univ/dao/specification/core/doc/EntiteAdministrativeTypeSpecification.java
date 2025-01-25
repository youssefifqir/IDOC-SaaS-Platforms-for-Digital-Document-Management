package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.EntiteAdministrativeTypeCriteria;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class EntiteAdministrativeTypeSpecification extends  AbstractSpecification<EntiteAdministrativeTypeCriteria, EntiteAdministrativeType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public EntiteAdministrativeTypeSpecification(EntiteAdministrativeTypeCriteria criteria) {
        super(criteria);
    }

    public EntiteAdministrativeTypeSpecification(EntiteAdministrativeTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
