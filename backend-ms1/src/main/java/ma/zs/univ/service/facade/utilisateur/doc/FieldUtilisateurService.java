package ma.zs.univ.service.facade.utilisateur.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.Field;
import ma.zs.univ.dao.criteria.core.doc.FieldCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface FieldUtilisateurService {







	Field create(Field t);

    Field update(Field t);

    List<Field> update(List<Field> ts,boolean createIfNotExist);

    Field findById(Long id);

    Field findOrSave(Field t);

    Field findByReferenceEntity(Field t);

    Field findWithAssociatedLists(Long id);

    List<Field> findAllOptimized();

    List<Field> findAll();

    List<Field> findByCriteria(FieldCriteria criteria);

    List<Field> findPaginatedByCriteria(FieldCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FieldCriteria criteria);

    List<Field> delete(List<Field> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Field>> getToBeSavedAndToBeDeleted(List<Field> oldList, List<Field> newList);

    List<Field> importData(List<Field> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Field> importExcel(MultipartFile file);

}
