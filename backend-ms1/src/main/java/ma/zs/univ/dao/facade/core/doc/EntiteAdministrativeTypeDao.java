package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import java.util.List;


@Repository
public interface EntiteAdministrativeTypeDao extends AbstractRepository<EntiteAdministrativeType,Long>  {
    EntiteAdministrativeType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EntiteAdministrativeType(item.id,item.libelle) FROM EntiteAdministrativeType item")
    List<EntiteAdministrativeType> findAllOptimized();

}
