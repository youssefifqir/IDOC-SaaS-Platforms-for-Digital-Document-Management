package ma.zs.univ.dao.facade.core.referetiel;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import java.util.List;


@Repository
public interface AcessManagementDao extends AbstractRepository<AcessManagement,Long>  {
    AcessManagement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW AcessManagement(item.id,item.libelle) FROM AcessManagement item")
    List<AcessManagement> findAllOptimized();

}
