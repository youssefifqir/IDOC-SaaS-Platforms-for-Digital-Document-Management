package ma.zs.univ.dao.facade.core.doc;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentField;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DocumentFieldDao extends AbstractRepository<DocumentField,Long>  {

    List<DocumentField> findByFieldId(Long id);
    int deleteByFieldId(Long id);
    long countByFieldCode(String code);
    List<DocumentField> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentField> findByDocumentFieldStateId(Long id);
    int deleteByDocumentFieldStateId(Long id);
    long countByDocumentFieldStateCode(String code);


}
