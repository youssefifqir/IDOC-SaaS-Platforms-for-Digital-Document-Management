package ma.zs.univ.dao.facade.core.doc;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentAcessShare;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DocumentAcessShareDao extends AbstractRepository<DocumentAcessShare,Long>  {

    List<DocumentAcessShare> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentAcessShare> findByAcessShareId(Long id);
    int deleteByAcessShareId(Long id);
    long countByAcessShareCode(String code);


}
