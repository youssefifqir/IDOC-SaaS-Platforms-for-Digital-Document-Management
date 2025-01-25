package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.Document;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.Document;
import java.util.List;


@Repository
public interface DocumentDao extends AbstractRepository<Document,Long>  {
    Document findByReference(String reference);
    int deleteByReference(String reference);

    List<Document> findByDocumentTypeId(Long id);
    int deleteByDocumentTypeId(Long id);
    long countByDocumentTypeCode(String code);
    List<Document> findByDocumentStateId(Long id);
    int deleteByDocumentStateId(Long id);
    long countByDocumentStateCode(String code);
    List<Document> findByDocumentCategorieId(Long id);
    int deleteByDocumentCategorieId(Long id);
    long countByDocumentCategorieCode(String code);
    List<Document> findByProprietaireId(Long id);
    int deleteByProprietaireId(Long id);
    long countByProprietaireId(Long id);
    List<Document> findByEntiteAdministrativeId(Long id);
    int deleteByEntiteAdministrativeId(Long id);
    long countByEntiteAdministrativeCode(String code);
    List<Document> findByEntiteAdministrativeProprietaireId(Long id);
    int deleteByEntiteAdministrativeProprietaireId(Long id);
    long countByEntiteAdministrativeProprietaireCode(String code);

    @Query("SELECT NEW Document(item.id,item.reference) FROM Document item")
    List<Document> findAllOptimized();

}
