package ma.zs.univ.service.facade.admin.purchase;

import java.util.List;
import ma.zs.univ.bean.core.purchase.GroupeUtilisateur;
import ma.zs.univ.dao.criteria.core.purchase.GroupeUtilisateurCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface GroupeUtilisateurAdminService {



    List<GroupeUtilisateur> findByGroupeId(Long id);
    int deleteByGroupeId(Long id);
    long countByGroupeCode(String code);
    List<GroupeUtilisateur> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);




	GroupeUtilisateur create(GroupeUtilisateur t);

    GroupeUtilisateur update(GroupeUtilisateur t);

    List<GroupeUtilisateur> update(List<GroupeUtilisateur> ts,boolean createIfNotExist);

    GroupeUtilisateur findById(Long id);

    GroupeUtilisateur findOrSave(GroupeUtilisateur t);

    GroupeUtilisateur findByReferenceEntity(GroupeUtilisateur t);

    GroupeUtilisateur findWithAssociatedLists(Long id);

    List<GroupeUtilisateur> findAllOptimized();

    List<GroupeUtilisateur> findAll();

    List<GroupeUtilisateur> findByCriteria(GroupeUtilisateurCriteria criteria);

    List<GroupeUtilisateur> findPaginatedByCriteria(GroupeUtilisateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GroupeUtilisateurCriteria criteria);

    List<GroupeUtilisateur> delete(List<GroupeUtilisateur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<GroupeUtilisateur>> getToBeSavedAndToBeDeleted(List<GroupeUtilisateur> oldList, List<GroupeUtilisateur> newList);

    List<GroupeUtilisateur> importData(List<GroupeUtilisateur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<GroupeUtilisateur> importExcel(MultipartFile file);

}
