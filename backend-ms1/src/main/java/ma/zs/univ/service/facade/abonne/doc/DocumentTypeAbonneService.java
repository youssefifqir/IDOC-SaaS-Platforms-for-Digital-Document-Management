package ma.zs.univ.service.facade.abonne.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentType;
import ma.zs.univ.dao.criteria.core.doc.DocumentTypeCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentTypeAbonneService {







	DocumentType create(DocumentType t);

    DocumentType update(DocumentType t);

    List<DocumentType> update(List<DocumentType> ts,boolean createIfNotExist);

    DocumentType findById(Long id);

    DocumentType findOrSave(DocumentType t);

    DocumentType findByReferenceEntity(DocumentType t);

    DocumentType findWithAssociatedLists(Long id);

    List<DocumentType> findAllOptimized();

    List<DocumentType> findAll();

    List<DocumentType> findByCriteria(DocumentTypeCriteria criteria);

    List<DocumentType> findPaginatedByCriteria(DocumentTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentTypeCriteria criteria);

    List<DocumentType> delete(List<DocumentType> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentType>> getToBeSavedAndToBeDeleted(List<DocumentType> oldList, List<DocumentType> newList);

    List<DocumentType> importData(List<DocumentType> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentType> importExcel(MultipartFile file);

}
