package  ma.zs.univ.dao.specification.core.doc;

import ma.zs.univ.dao.criteria.core.doc.TagCriteria;
import ma.zs.univ.bean.core.doc.Tag;
import ma.zs.univ.zynerator.specification.AbstractSpecification;


public class TagSpecification extends  AbstractSpecification<TagCriteria, Tag>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TagSpecification(TagCriteria criteria) {
        super(criteria);
    }

    public TagSpecification(TagCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
