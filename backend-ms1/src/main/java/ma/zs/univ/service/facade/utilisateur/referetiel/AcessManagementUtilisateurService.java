package ma.zs.univ.service.facade.utilisateur.referetiel;

import java.util.List;
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import ma.zs.univ.dao.criteria.core.referetiel.AcessManagementCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AcessManagementUtilisateurService {







	AcessManagement create(AcessManagement t);

    AcessManagement update(AcessManagement t);

    List<AcessManagement> update(List<AcessManagement> ts,boolean createIfNotExist);

    AcessManagement findById(Long id);

    AcessManagement findOrSave(AcessManagement t);

    AcessManagement findByReferenceEntity(AcessManagement t);

    AcessManagement findWithAssociatedLists(Long id);

    List<AcessManagement> findAllOptimized();

    List<AcessManagement> findAll();

    List<AcessManagement> findByCriteria(AcessManagementCriteria criteria);

    List<AcessManagement> findPaginatedByCriteria(AcessManagementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AcessManagementCriteria criteria);

    List<AcessManagement> delete(List<AcessManagement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<AcessManagement>> getToBeSavedAndToBeDeleted(List<AcessManagement> oldList, List<AcessManagement> newList);

    List<AcessManagement> importData(List<AcessManagement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<AcessManagement> importExcel(MultipartFile file);

}
