package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentState;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.DocumentState;
import java.util.List;


@Repository
public interface DocumentStateDao extends AbstractRepository<DocumentState,Long>  {
    DocumentState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DocumentState(item.id,item.libelle) FROM DocumentState item")
    List<DocumentState> findAllOptimized();

}
