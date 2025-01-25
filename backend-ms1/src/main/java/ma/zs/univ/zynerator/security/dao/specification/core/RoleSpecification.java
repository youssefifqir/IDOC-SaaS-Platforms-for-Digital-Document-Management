package ma.zs.univ.zynerator.security.dao.specification.core;

import ma.zs.univ.zynerator.security.bean.Role;
import ma.zs.univ.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class RoleSpecification extends  AbstractSpecification<RoleCriteria, Role>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("authority", criteria.getAuthority(),criteria.getAuthorityLike());
    }

    public RoleSpecification(RoleCriteria criteria) {
        super(criteria);
    }

    public RoleSpecification(RoleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
