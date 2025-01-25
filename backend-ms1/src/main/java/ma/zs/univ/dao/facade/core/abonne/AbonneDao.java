package ma.zs.univ.dao.facade.core.abonne;

import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.bean.core.abonne.Abonne;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AbonneDao extends AbstractRepository<Abonne,Long>  {

    List<Abonne> findByEntiteAdministrativeId(Long id);
    int deleteByEntiteAdministrativeId(Long id);
    long countByEntiteAdministrativeCode(String code);
    Abonne findByUsername(String username);


}
