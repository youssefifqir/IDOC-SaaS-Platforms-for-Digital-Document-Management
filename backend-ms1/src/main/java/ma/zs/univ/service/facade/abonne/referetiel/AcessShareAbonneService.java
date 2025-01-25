package ma.zs.univ.service.facade.abonne.referetiel;

import java.util.List;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.dao.criteria.core.referetiel.AcessShareCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AcessShareAbonneService {







	AcessShare create(AcessShare t);

    AcessShare update(AcessShare t);

    List<AcessShare> update(List<AcessShare> ts,boolean createIfNotExist);

    AcessShare findById(Long id);

    AcessShare findOrSave(AcessShare t);

    AcessShare findByReferenceEntity(AcessShare t);

    AcessShare findWithAssociatedLists(Long id);

    List<AcessShare> findAllOptimized();

    List<AcessShare> findAll();

    List<AcessShare> findByCriteria(AcessShareCriteria criteria);

    List<AcessShare> findPaginatedByCriteria(AcessShareCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AcessShareCriteria criteria);

    List<AcessShare> delete(List<AcessShare> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<AcessShare>> getToBeSavedAndToBeDeleted(List<AcessShare> oldList, List<AcessShare> newList);

    List<AcessShare> importData(List<AcessShare> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<AcessShare> importExcel(MultipartFile file);

}
