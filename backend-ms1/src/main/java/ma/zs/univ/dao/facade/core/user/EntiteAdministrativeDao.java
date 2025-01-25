package ma.zs.univ.dao.facade.core.user;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import java.util.List;


@Repository
public interface EntiteAdministrativeDao extends AbstractRepository<EntiteAdministrative,Long>  {
    EntiteAdministrative findByCode(String code);
    int deleteByCode(String code);

    List<EntiteAdministrative> findByChefId(Long id);
    int deleteByChefId(Long id);
    long countByChefId(Long id);
    List<EntiteAdministrative> findByEntiteAdministrativeTypeId(Long id);
    int deleteByEntiteAdministrativeTypeId(Long id);
    long countByEntiteAdministrativeTypeCode(String code);
    List<EntiteAdministrative> findByAbonneId(Long id);
    int deleteByAbonneId(Long id);
    long countByAbonneId(Long id);

    @Query("SELECT NEW EntiteAdministrative(item.id,item.libelle) FROM EntiteAdministrative item")
    List<EntiteAdministrative> findAllOptimized();

}
