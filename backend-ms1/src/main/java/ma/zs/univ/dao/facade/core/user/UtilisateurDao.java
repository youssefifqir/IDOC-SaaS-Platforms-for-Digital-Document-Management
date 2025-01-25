package ma.zs.univ.dao.facade.core.user;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.user.Utilisateur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UtilisateurDao extends AbstractRepository<Utilisateur,Long>  {

    List<Utilisateur> findByAbonneId(Long id);
    int deleteByAbonneId(Long id);
    long countByAbonneId(Long id);
    Utilisateur findByUsername(String username);


}
