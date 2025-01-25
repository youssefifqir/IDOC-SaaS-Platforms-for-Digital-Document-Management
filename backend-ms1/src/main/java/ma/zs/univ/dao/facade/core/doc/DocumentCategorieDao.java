package ma.zs.univ.dao.facade.core.doc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import org.springframework.stereotype.Repository;
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import java.util.List;


@Repository
public interface DocumentCategorieDao extends AbstractRepository<DocumentCategorie,Long>  {
    DocumentCategorie findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DocumentCategorie(item.id,item.libelle) FROM DocumentCategorie item")
    List<DocumentCategorie> findAllOptimized();

}
