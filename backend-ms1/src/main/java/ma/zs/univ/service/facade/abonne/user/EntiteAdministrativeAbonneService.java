package ma.zs.univ.service.facade.abonne.user;

import java.util.List;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.dao.criteria.core.user.EntiteAdministrativeCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EntiteAdministrativeAbonneService {



    List<EntiteAdministrative> findByChefId(Long id);
    int deleteByChefId(Long id);
    long countByChefId(Long id);
    List<EntiteAdministrative> findByEntiteAdministrativeTypeId(Long id);
    int deleteByEntiteAdministrativeTypeId(Long id);
    long countByEntiteAdministrativeTypeCode(String code);
    List<EntiteAdministrative> findByAbonneId(Long id);
    int deleteByAbonneId(Long id);
    long countByAbonneId(Long id);




	EntiteAdministrative create(EntiteAdministrative t);

    EntiteAdministrative update(EntiteAdministrative t);

    List<EntiteAdministrative> update(List<EntiteAdministrative> ts,boolean createIfNotExist);

    EntiteAdministrative findById(Long id);

    EntiteAdministrative findOrSave(EntiteAdministrative t);

    EntiteAdministrative findByReferenceEntity(EntiteAdministrative t);

    EntiteAdministrative findWithAssociatedLists(Long id);

    List<EntiteAdministrative> findAllOptimized();

    List<EntiteAdministrative> findAll();

    List<EntiteAdministrative> findByCriteria(EntiteAdministrativeCriteria criteria);

    List<EntiteAdministrative> findPaginatedByCriteria(EntiteAdministrativeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntiteAdministrativeCriteria criteria);

    List<EntiteAdministrative> delete(List<EntiteAdministrative> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EntiteAdministrative>> getToBeSavedAndToBeDeleted(List<EntiteAdministrative> oldList, List<EntiteAdministrative> newList);

    List<EntiteAdministrative> importData(List<EntiteAdministrative> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EntiteAdministrative> importExcel(MultipartFile file);

}
