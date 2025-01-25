package ma.zs.univ.service.facade.abonne.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentField;
import ma.zs.univ.dao.criteria.core.doc.DocumentFieldCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentFieldAbonneService {



    List<DocumentField> findByFieldId(Long id);
    int deleteByFieldId(Long id);
    long countByFieldCode(String code);
    List<DocumentField> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentField> findByDocumentFieldStateId(Long id);
    int deleteByDocumentFieldStateId(Long id);
    long countByDocumentFieldStateCode(String code);




	DocumentField create(DocumentField t);

    DocumentField update(DocumentField t);

    List<DocumentField> update(List<DocumentField> ts,boolean createIfNotExist);

    DocumentField findById(Long id);

    DocumentField findOrSave(DocumentField t);

    DocumentField findByReferenceEntity(DocumentField t);

    DocumentField findWithAssociatedLists(Long id);

    List<DocumentField> findAllOptimized();

    List<DocumentField> findAll();

    List<DocumentField> findByCriteria(DocumentFieldCriteria criteria);

    List<DocumentField> findPaginatedByCriteria(DocumentFieldCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentFieldCriteria criteria);

    List<DocumentField> delete(List<DocumentField> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentField>> getToBeSavedAndToBeDeleted(List<DocumentField> oldList, List<DocumentField> newList);

    List<DocumentField> importData(List<DocumentField> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentField> importExcel(MultipartFile file);

}
