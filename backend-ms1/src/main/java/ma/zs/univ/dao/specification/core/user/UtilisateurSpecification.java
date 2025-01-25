package  ma.zs.univ.dao.specification.core.user;

import ma.zs.univ.dao.criteria.core.user.UtilisateurCriteria;
import ma.zs.univ.bean.core.user.Utilisateur;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class UtilisateurSpecification extends  AbstractSpecification<UtilisateurCriteria, Utilisateur>  {

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
        addPredicateFk("abonne","id", criteria.getAbonne()==null?null:criteria.getAbonne().getId());
        addPredicateFk("abonne","id", criteria.getAbonnes());
    }

    public UtilisateurSpecification(UtilisateurCriteria criteria) {
        super(criteria);
    }

    public UtilisateurSpecification(UtilisateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
