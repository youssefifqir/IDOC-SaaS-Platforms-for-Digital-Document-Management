package ma.zs.univ.service.facade.abonne.abonne;

import java.util.List;
import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.dao.criteria.core.abonne.AbonneCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AbonneAbonneService {


    Abonne findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Abonne> findByEntiteAdministrativeId(Long id);
    int deleteByEntiteAdministrativeId(Long id);
    long countByEntiteAdministrativeCode(String code);




	Abonne create(Abonne t);

    Abonne update(Abonne t);

    List<Abonne> update(List<Abonne> ts,boolean createIfNotExist);

    Abonne findById(Long id);

    Abonne findOrSave(Abonne t);

    Abonne findByReferenceEntity(Abonne t);

    Abonne findWithAssociatedLists(Long id);

    List<Abonne> findAllOptimized();

    List<Abonne> findAll();

    List<Abonne> findByCriteria(AbonneCriteria criteria);

    List<Abonne> findPaginatedByCriteria(AbonneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AbonneCriteria criteria);

    List<Abonne> delete(List<Abonne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Abonne>> getToBeSavedAndToBeDeleted(List<Abonne> oldList, List<Abonne> newList);

    List<Abonne> importData(List<Abonne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Abonne> importExcel(MultipartFile file);

}
