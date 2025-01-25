package ma.zs.univ.service.facade.utilisateur.user;

import java.util.List;
import ma.zs.univ.bean.core.user.Utilisateur;
import ma.zs.univ.dao.criteria.core.user.UtilisateurCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface UtilisateurUtilisateurService {


    Utilisateur findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Utilisateur> findByAbonneId(Long id);
    int deleteByAbonneId(Long id);
    long countByAbonneId(Long id);




	Utilisateur create(Utilisateur t);

    Utilisateur update(Utilisateur t);

    List<Utilisateur> update(List<Utilisateur> ts,boolean createIfNotExist);

    Utilisateur findById(Long id);

    Utilisateur findOrSave(Utilisateur t);

    Utilisateur findByReferenceEntity(Utilisateur t);

    Utilisateur findWithAssociatedLists(Long id);

    List<Utilisateur> findAllOptimized();

    List<Utilisateur> findAll();

    List<Utilisateur> findByCriteria(UtilisateurCriteria criteria);

    List<Utilisateur> findPaginatedByCriteria(UtilisateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(UtilisateurCriteria criteria);

    List<Utilisateur> delete(List<Utilisateur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Utilisateur>> getToBeSavedAndToBeDeleted(List<Utilisateur> oldList, List<Utilisateur> newList);

    List<Utilisateur> importData(List<Utilisateur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Utilisateur> importExcel(MultipartFile file);

}
