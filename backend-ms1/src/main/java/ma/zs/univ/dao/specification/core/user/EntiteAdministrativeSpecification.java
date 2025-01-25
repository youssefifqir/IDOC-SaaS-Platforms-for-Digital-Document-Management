package  ma.zs.univ.dao.specification.core.user;

import ma.zs.univ.dao.criteria.core.user.EntiteAdministrativeCriteria;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class EntiteAdministrativeSpecification extends  AbstractSpecification<EntiteAdministrativeCriteria, EntiteAdministrative>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("chef","id", criteria.getChef()==null?null:criteria.getChef().getId());
        addPredicateFk("chef","id", criteria.getChefs());
        addPredicateFk("entiteAdministrativeType","id", criteria.getEntiteAdministrativeType()==null?null:criteria.getEntiteAdministrativeType().getId());
        addPredicateFk("entiteAdministrativeType","id", criteria.getEntiteAdministrativeTypes());
        addPredicateFk("entiteAdministrativeType","code", criteria.getEntiteAdministrativeType()==null?null:criteria.getEntiteAdministrativeType().getCode());
        addPredicateFk("abonne","id", criteria.getAbonne()==null?null:criteria.getAbonne().getId());
        addPredicateFk("abonne","id", criteria.getAbonnes());
    }

    public EntiteAdministrativeSpecification(EntiteAdministrativeCriteria criteria) {
        super(criteria);
    }

    public EntiteAdministrativeSpecification(EntiteAdministrativeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
