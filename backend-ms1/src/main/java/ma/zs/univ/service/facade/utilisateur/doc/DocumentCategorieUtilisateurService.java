package ma.zs.univ.service.facade.utilisateur.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import ma.zs.univ.dao.criteria.core.doc.DocumentCategorieCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentCategorieUtilisateurService {







	DocumentCategorie create(DocumentCategorie t);

    DocumentCategorie update(DocumentCategorie t);

    List<DocumentCategorie> update(List<DocumentCategorie> ts,boolean createIfNotExist);

    DocumentCategorie findById(Long id);

    DocumentCategorie findOrSave(DocumentCategorie t);

    DocumentCategorie findByReferenceEntity(DocumentCategorie t);

    DocumentCategorie findWithAssociatedLists(Long id);

    List<DocumentCategorie> findAllOptimized();

    List<DocumentCategorie> findAll();

    List<DocumentCategorie> findByCriteria(DocumentCategorieCriteria criteria);

    List<DocumentCategorie> findPaginatedByCriteria(DocumentCategorieCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentCategorieCriteria criteria);

    List<DocumentCategorie> delete(List<DocumentCategorie> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentCategorie>> getToBeSavedAndToBeDeleted(List<DocumentCategorie> oldList, List<DocumentCategorie> newList);

    List<DocumentCategorie> importData(List<DocumentCategorie> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentCategorie> importExcel(MultipartFile file);

}
