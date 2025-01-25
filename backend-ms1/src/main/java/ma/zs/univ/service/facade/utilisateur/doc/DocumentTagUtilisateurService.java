package ma.zs.univ.service.facade.utilisateur.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentTag;
import ma.zs.univ.dao.criteria.core.doc.DocumentTagCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentTagUtilisateurService {



    List<DocumentTag> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentTag> findByTagId(Long id);
    int deleteByTagId(Long id);
    long countByTagCode(String code);




	DocumentTag create(DocumentTag t);

    DocumentTag update(DocumentTag t);

    List<DocumentTag> update(List<DocumentTag> ts,boolean createIfNotExist);

    DocumentTag findById(Long id);

    DocumentTag findOrSave(DocumentTag t);

    DocumentTag findByReferenceEntity(DocumentTag t);

    DocumentTag findWithAssociatedLists(Long id);

    List<DocumentTag> findAllOptimized();

    List<DocumentTag> findAll();

    List<DocumentTag> findByCriteria(DocumentTagCriteria criteria);

    List<DocumentTag> findPaginatedByCriteria(DocumentTagCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentTagCriteria criteria);

    List<DocumentTag> delete(List<DocumentTag> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentTag>> getToBeSavedAndToBeDeleted(List<DocumentTag> oldList, List<DocumentTag> newList);

    List<DocumentTag> importData(List<DocumentTag> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentTag> importExcel(MultipartFile file);

}
