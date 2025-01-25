package ma.zs.univ.dao.facade.core.purchase;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DocumentPartageGroupeDao extends AbstractRepository<DocumentPartageGroupe,Long>  {

    List<DocumentPartageGroupe> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentPartageGroupe> findByGroupeId(Long id);
    int deleteByGroupeId(Long id);
    long countByGroupeCode(String code);
    List<DocumentPartageGroupe> findByAcessShareId(Long id);
    int deleteByAcessShareId(Long id);
    long countByAcessShareCode(String code);


}
