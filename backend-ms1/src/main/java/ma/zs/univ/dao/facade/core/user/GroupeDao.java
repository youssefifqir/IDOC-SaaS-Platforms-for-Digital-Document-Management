package ma.zs.univ.dao.facade.core.user;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.user.Groupe;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.user.Groupe;
import java.util.List;


@Repository
public interface GroupeDao extends AbstractRepository<Groupe,Long>  {
    Groupe findByCode(String code);
    int deleteByCode(String code);

    List<Groupe> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);

    @Query("SELECT NEW Groupe(item.id,item.libelle) FROM Groupe item")
    List<Groupe> findAllOptimized();

}
