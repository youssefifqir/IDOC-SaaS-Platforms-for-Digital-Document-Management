package ma.zs.univ.service.facade.admin.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import ma.zs.univ.dao.criteria.core.doc.DocumentFieldStateCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentFieldStateAdminService {







	DocumentFieldState create(DocumentFieldState t);

    DocumentFieldState update(DocumentFieldState t);

    List<DocumentFieldState> update(List<DocumentFieldState> ts,boolean createIfNotExist);

    DocumentFieldState findById(Long id);

    DocumentFieldState findOrSave(DocumentFieldState t);

    DocumentFieldState findByReferenceEntity(DocumentFieldState t);

    DocumentFieldState findWithAssociatedLists(Long id);

    List<DocumentFieldState> findAllOptimized();

    List<DocumentFieldState> findAll();

    List<DocumentFieldState> findByCriteria(DocumentFieldStateCriteria criteria);

    List<DocumentFieldState> findPaginatedByCriteria(DocumentFieldStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentFieldStateCriteria criteria);

    List<DocumentFieldState> delete(List<DocumentFieldState> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentFieldState>> getToBeSavedAndToBeDeleted(List<DocumentFieldState> oldList, List<DocumentFieldState> newList);

    List<DocumentFieldState> importData(List<DocumentFieldState> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentFieldState> importExcel(MultipartFile file);

}
