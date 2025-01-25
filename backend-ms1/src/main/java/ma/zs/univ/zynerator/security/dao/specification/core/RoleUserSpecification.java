package ma.zs.univ.zynerator.security.dao.specification.core;

import ma.zs.univ.zynerator.security.bean.RoleUser;
import ma.zs.univ.zynerator.security.dao.criteria.core.RoleUserCriteria;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class RoleUserSpecification extends  AbstractSpecification<RoleUserCriteria, RoleUser>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("role","id", criteria.getRole()==null?null:criteria.getRole().getId());
        addPredicateFk("role","id", criteria.getRoles());
        addPredicateFk("role","authority", criteria.getRole()==null?null:criteria.getRole().getAuthority());
        addPredicateFk("user","id", criteria.getUser()==null?null:criteria.getUser().getId());
        addPredicateFk("user","id", criteria.getUsers());
        addPredicateFk("user","email", criteria.getUser()==null?null:criteria.getUser().getEmail());
    }

    public RoleUserSpecification(RoleUserCriteria criteria) {
        super(criteria);
    }

    public RoleUserSpecification(RoleUserCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
