package ma.zs.univ.service.facade.utilisateur.user;

import java.util.List;
import ma.zs.univ.bean.core.user.Groupe;
import ma.zs.univ.dao.criteria.core.user.GroupeCriteria;
import ma.zs.univ.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface GroupeUtilisateurService {



    List<Groupe> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);




	Groupe create(Groupe t);

    Groupe update(Groupe t);

    List<Groupe> update(List<Groupe> ts,boolean createIfNotExist);

    Groupe findById(Long id);

    Groupe findOrSave(Groupe t);

    Groupe findByReferenceEntity(Groupe t);

    Groupe findWithAssociatedLists(Long id);

    List<Groupe> findAllOptimized();

    List<Groupe> findAll();

    List<Groupe> findByCriteria(GroupeCriteria criteria);

    List<Groupe> findPaginatedByCriteria(GroupeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GroupeCriteria criteria);

    List<Groupe> delete(List<Groupe> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Groupe>> getToBeSavedAndToBeDeleted(List<Groupe> oldList, List<Groupe> newList);

    List<Groupe> importData(List<Groupe> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Groupe> importExcel(MultipartFile file);

}
