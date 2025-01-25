package ma.zs.univ.dao.facade.core.purchase;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DocumentPartageUtilisateurDao extends AbstractRepository<DocumentPartageUtilisateur,Long>  {

    List<DocumentPartageUtilisateur> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentPartageUtilisateur> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);
    List<DocumentPartageUtilisateur> findByAcessShareId(Long id);
    int deleteByAcessShareId(Long id);
    long countByAcessShareCode(String code);


}
