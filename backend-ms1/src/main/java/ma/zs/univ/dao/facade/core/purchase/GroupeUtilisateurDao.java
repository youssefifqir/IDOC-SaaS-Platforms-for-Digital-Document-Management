package ma.zs.univ.dao.facade.core.purchase;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.purchase.GroupeUtilisateur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface GroupeUtilisateurDao extends AbstractRepository<GroupeUtilisateur,Long>  {

    List<GroupeUtilisateur> findByGroupeId(Long id);
    int deleteByGroupeId(Long id);
    long countByGroupeCode(String code);
    List<GroupeUtilisateur> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);


}
