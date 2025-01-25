package ma.zs.univ.service.facade.utilisateur.purchase;

import java.util.List;
import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur;
import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageUtilisateurCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentPartageUtilisateurUtilisateurService {



    List<DocumentPartageUtilisateur> findByDocumentId(Long id);
    int deleteByDocumentId(Long id);
    long countByDocumentReference(String reference);
    List<DocumentPartageUtilisateur> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);
    List<DocumentPartageUtilisateur> findByAcessShareId(Long id);
    int deleteByAcessShareId(Long id);
    long countByAcessShareCode(String code);




	DocumentPartageUtilisateur create(DocumentPartageUtilisateur t);

    DocumentPartageUtilisateur update(DocumentPartageUtilisateur t);

    List<DocumentPartageUtilisateur> update(List<DocumentPartageUtilisateur> ts,boolean createIfNotExist);

    DocumentPartageUtilisateur findById(Long id);

    DocumentPartageUtilisateur findOrSave(DocumentPartageUtilisateur t);

    DocumentPartageUtilisateur findByReferenceEntity(DocumentPartageUtilisateur t);

    DocumentPartageUtilisateur findWithAssociatedLists(Long id);

    List<DocumentPartageUtilisateur> findAllOptimized();

    List<DocumentPartageUtilisateur> findAll();

    List<DocumentPartageUtilisateur> findByCriteria(DocumentPartageUtilisateurCriteria criteria);

    List<DocumentPartageUtilisateur> findPaginatedByCriteria(DocumentPartageUtilisateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentPartageUtilisateurCriteria criteria);

    List<DocumentPartageUtilisateur> delete(List<DocumentPartageUtilisateur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentPartageUtilisateur>> getToBeSavedAndToBeDeleted(List<DocumentPartageUtilisateur> oldList, List<DocumentPartageUtilisateur> newList);

    List<DocumentPartageUtilisateur> importData(List<DocumentPartageUtilisateur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentPartageUtilisateur> importExcel(MultipartFile file);

}
