package ma.zs.univ.service.facade.admin.doc;

import java.util.List;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import ma.zs.univ.dao.criteria.core.doc.EntiteAdministrativeTypeCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EntiteAdministrativeTypeAdminService {







	EntiteAdministrativeType create(EntiteAdministrativeType t);

    EntiteAdministrativeType update(EntiteAdministrativeType t);

    List<EntiteAdministrativeType> update(List<EntiteAdministrativeType> ts,boolean createIfNotExist);

    EntiteAdministrativeType findById(Long id);

    EntiteAdministrativeType findOrSave(EntiteAdministrativeType t);

    EntiteAdministrativeType findByReferenceEntity(EntiteAdministrativeType t);

    EntiteAdministrativeType findWithAssociatedLists(Long id);

    List<EntiteAdministrativeType> findAllOptimized();

    List<EntiteAdministrativeType> findAll();

    List<EntiteAdministrativeType> findByCriteria(EntiteAdministrativeTypeCriteria criteria);

    List<EntiteAdministrativeType> findPaginatedByCriteria(EntiteAdministrativeTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntiteAdministrativeTypeCriteria criteria);

    List<EntiteAdministrativeType> delete(List<EntiteAdministrativeType> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EntiteAdministrativeType>> getToBeSavedAndToBeDeleted(List<EntiteAdministrativeType> oldList, List<EntiteAdministrativeType> newList);

    List<EntiteAdministrativeType> importData(List<EntiteAdministrativeType> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EntiteAdministrativeType> importExcel(MultipartFile file);

}
