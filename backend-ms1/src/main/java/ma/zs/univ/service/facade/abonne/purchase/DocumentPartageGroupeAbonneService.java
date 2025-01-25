package ma.zs.univ.service.facade.abonne.purchase;

import java.util.List;
import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe;
import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageGroupeCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentPartageGroupeAbonneService {



    List<DocumentPartageGroupe> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentPartageGroupe> findByGroupeId(Long id);
    int deleteByGroupeId(Long id);
    long countByGroupeCode(String code);
    List<DocumentPartageGroupe> findByAcessShareId(Long id);
    int deleteByAcessShareId(Long id);
    long countByAcessShareCode(String code);




	DocumentPartageGroupe create(DocumentPartageGroupe t);

    DocumentPartageGroupe update(DocumentPartageGroupe t);

    List<DocumentPartageGroupe> update(List<DocumentPartageGroupe> ts,boolean createIfNotExist);

    DocumentPartageGroupe findById(Long id);

    DocumentPartageGroupe findOrSave(DocumentPartageGroupe t);

    DocumentPartageGroupe findByReferenceEntity(DocumentPartageGroupe t);

    DocumentPartageGroupe findWithAssociatedLists(Long id);

    List<DocumentPartageGroupe> findAllOptimized();

    List<DocumentPartageGroupe> findAll();

    List<DocumentPartageGroupe> findByCriteria(DocumentPartageGroupeCriteria criteria);

    List<DocumentPartageGroupe> findPaginatedByCriteria(DocumentPartageGroupeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentPartageGroupeCriteria criteria);

    List<DocumentPartageGroupe> delete(List<DocumentPartageGroupe> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentPartageGroupe>> getToBeSavedAndToBeDeleted(List<DocumentPartageGroupe> oldList, List<DocumentPartageGroupe> newList);

    List<DocumentPartageGroupe> importData(List<DocumentPartageGroupe> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentPartageGroupe> importExcel(MultipartFile file);

}
