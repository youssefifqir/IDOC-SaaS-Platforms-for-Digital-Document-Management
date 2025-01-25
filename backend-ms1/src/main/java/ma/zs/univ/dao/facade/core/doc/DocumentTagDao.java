package ma.zs.univ.dao.facade.core.doc;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentTag;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DocumentTagDao extends AbstractRepository<DocumentTag,Long>  {

    List<DocumentTag> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentTag> findByTagId(Long id);
    int deleteByTagId(Long id);
    long countByTagCode(String code);


}
