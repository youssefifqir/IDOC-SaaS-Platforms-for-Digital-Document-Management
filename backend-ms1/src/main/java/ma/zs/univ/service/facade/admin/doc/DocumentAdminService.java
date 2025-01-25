package ma.zs.univ.service.facade.admin.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.dao.criteria.core.doc.DocumentCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentAdminService {



    List<Document> findByDocumentTypeId(Long id);
    int deleteByDocumentTypeId(Long id);
    long countByDocumentTypeCode(String code);
    List<Document> findByDocumentStateId(Long id);
    int deleteByDocumentStateId(Long id);
    long countByDocumentStateCode(String code);
    List<Document> findByDocumentCategorieId(Long id);
    int deleteByDocumentCategorieId(Long id);
    long countByDocumentCategorieCode(String code);
    List<Document> findByProprietaireId(Long id);
    int deleteByProprietaireId(Long id);
    long countByProprietaireId(Long id);
    List<Document> findByEntiteAdministrativeId(Long id);
    int deleteByEntiteAdministrativeId(Long id);
    long countByEntiteAdministrativeCode(String code);
    List<Document> findByEntiteAdministrativeProprietaireId(Long id);
    int deleteByEntiteAdministrativeProprietaireId(Long id);
    long countByEntiteAdministrativeProprietaireCode(String code);




	Document create(Document t);

    Document update(Document t);

    List<Document> update(List<Document> ts,boolean createIfNotExist);

    Document findById(Long id);

    Document findOrSave(Document t);

    Document findByReferenceEntity(Document t);

    Document findWithAssociatedLists(Long id);

    List<Document> findAllOptimized();

    List<Document> findAll();

    List<Document> findByCriteria(DocumentCriteria criteria);

    List<Document> findPaginatedByCriteria(DocumentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentCriteria criteria);

    List<Document> delete(List<Document> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Document>> getToBeSavedAndToBeDeleted(List<Document> oldList, List<Document> newList);

    List<Document> importData(List<Document> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Document> importExcel(MultipartFile file);

}
