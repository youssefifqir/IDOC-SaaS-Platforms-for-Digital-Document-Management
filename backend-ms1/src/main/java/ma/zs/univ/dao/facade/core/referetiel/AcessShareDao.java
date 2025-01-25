package ma.zs.univ.dao.facade.core.referetiel;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import java.util.List;


@Repository
public interface AcessShareDao extends AbstractRepository<AcessShare,Long>  {
    AcessShare findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW AcessShare(item.id,item.libelle) FROM AcessShare item")
    List<AcessShare> findAllOptimized();

}
