package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentType;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.DocumentType;
import java.util.List;


@Repository
public interface DocumentTypeDao extends AbstractRepository<DocumentType,Long>  {
    DocumentType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DocumentType(item.id,item.libelle) FROM DocumentType item")
    List<DocumentType> findAllOptimized();

}
