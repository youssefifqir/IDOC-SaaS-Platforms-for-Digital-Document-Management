package ma.zs.univ.service.facade.abonne.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentAcessShare;
import ma.zs.univ.dao.criteria.core.doc.DocumentAcessShareCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentAcessShareAbonneService {



    List<DocumentAcessShare> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentAcessShare> findByAcessShareId(Long id);
    int deleteByAcessShareId(Long id);
    long countByAcessShareCode(String code);




	DocumentAcessShare create(DocumentAcessShare t);

    DocumentAcessShare update(DocumentAcessShare t);

    List<DocumentAcessShare> update(List<DocumentAcessShare> ts,boolean createIfNotExist);

    DocumentAcessShare findById(Long id);

    DocumentAcessShare findOrSave(DocumentAcessShare t);

    DocumentAcessShare findByReferenceEntity(DocumentAcessShare t);

    DocumentAcessShare findWithAssociatedLists(Long id);

    List<DocumentAcessShare> findAllOptimized();

    List<DocumentAcessShare> findAll();

    List<DocumentAcessShare> findByCriteria(DocumentAcessShareCriteria criteria);

    List<DocumentAcessShare> findPaginatedByCriteria(DocumentAcessShareCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentAcessShareCriteria criteria);

    List<DocumentAcessShare> delete(List<DocumentAcessShare> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentAcessShare>> getToBeSavedAndToBeDeleted(List<DocumentAcessShare> oldList, List<DocumentAcessShare> newList);

    List<DocumentAcessShare> importData(List<DocumentAcessShare> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentAcessShare> importExcel(MultipartFile file);

}
