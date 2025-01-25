package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.Tag;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.Tag;
import java.util.List;


@Repository
public interface TagDao extends AbstractRepository<Tag,Long>  {
    Tag findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Tag(item.id,item.libelle) FROM Tag item")
    List<Tag> findAllOptimized();

}
