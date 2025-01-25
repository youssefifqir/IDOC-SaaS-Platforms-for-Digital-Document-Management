package ma.zs.univ.service.facade.admin.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.DocumentState;
import ma.zs.univ.dao.criteria.core.doc.DocumentStateCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DocumentStateAdminService {







	DocumentState create(DocumentState t);

    DocumentState update(DocumentState t);

    List<DocumentState> update(List<DocumentState> ts,boolean createIfNotExist);

    DocumentState findById(Long id);

    DocumentState findOrSave(DocumentState t);

    DocumentState findByReferenceEntity(DocumentState t);

    DocumentState findWithAssociatedLists(Long id);

    List<DocumentState> findAllOptimized();

    List<DocumentState> findAll();

    List<DocumentState> findByCriteria(DocumentStateCriteria criteria);

    List<DocumentState> findPaginatedByCriteria(DocumentStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DocumentStateCriteria criteria);

    List<DocumentState> delete(List<DocumentState> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DocumentState>> getToBeSavedAndToBeDeleted(List<DocumentState> oldList, List<DocumentState> newList);

    List<DocumentState> importData(List<DocumentState> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DocumentState> importExcel(MultipartFile file);

}
