package ma.zs.univ.service.facade.utilisateur.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.Tag;
import ma.zs.univ.dao.criteria.core.doc.TagCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TagUtilisateurService {







	Tag create(Tag t);

    Tag update(Tag t);

    List<Tag> update(List<Tag> ts,boolean createIfNotExist);

    Tag findById(Long id);

    Tag findOrSave(Tag t);

    Tag findByReferenceEntity(Tag t);

    Tag findWithAssociatedLists(Long id);

    List<Tag> findAllOptimized();

    List<Tag> findAll();

    List<Tag> findByCriteria(TagCriteria criteria);

    List<Tag> findPaginatedByCriteria(TagCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TagCriteria criteria);

    List<Tag> delete(List<Tag> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Tag>> getToBeSavedAndToBeDeleted(List<Tag> oldList, List<Tag> newList);

    List<Tag> importData(List<Tag> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Tag> importExcel(MultipartFile file);

}
