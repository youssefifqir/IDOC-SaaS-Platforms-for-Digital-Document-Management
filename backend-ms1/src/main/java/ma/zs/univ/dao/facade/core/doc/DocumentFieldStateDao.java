package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import java.util.List;


@Repository
public interface DocumentFieldStateDao extends AbstractRepository<DocumentFieldState,Long>  {
    DocumentFieldState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DocumentFieldState(item.id,item.libelle) FROM DocumentFieldState item")
    List<DocumentFieldState> findAllOptimized();

}
