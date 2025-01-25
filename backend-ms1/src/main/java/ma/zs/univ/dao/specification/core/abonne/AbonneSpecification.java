package  ma.zs.univ.dao.specification.core.abonne;

import ma.zs.univ.dao.criteria.core.abonne.AbonneCriteria;
import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class AbonneSpecification extends  AbstractSpecification<AbonneCriteria, Abonne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateFk("entiteAdministrative","id", criteria.getEntiteAdministrative()==null?null:criteria.getEntiteAdministrative().getId());
        addPredicateFk("entiteAdministrative","id", criteria.getEntiteAdministratives());
        addPredicateFk("entiteAdministrative","code", criteria.getEntiteAdministrative()==null?null:criteria.getEntiteAdministrative().getCode());
    }

    public AbonneSpecification(AbonneCriteria criteria) {
        super(criteria);
    }

    public AbonneSpecification(AbonneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
