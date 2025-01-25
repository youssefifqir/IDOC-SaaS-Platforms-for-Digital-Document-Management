package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.Field;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.Field;
import java.util.List;


@Repository
public interface FieldDao extends AbstractRepository<Field,Long>  {
    Field findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Field(item.id,item.libelle) FROM Field item")
    List<Field> findAllOptimized();

}
