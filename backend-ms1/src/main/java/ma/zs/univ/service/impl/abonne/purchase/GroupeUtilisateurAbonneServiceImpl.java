package ma.zs.univ.service.impl.abonne.purchase;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.purchase.GroupeUtilisateur;
import ma.zs.univ.dao.criteria.core.purchase.GroupeUtilisateurCriteria;
import ma.zs.univ.dao.facade.core.purchase.GroupeUtilisateurDao;
import ma.zs.univ.dao.specification.core.purchase.GroupeUtilisateurSpecification;
import ma.zs.univ.service.facade.abonne.purchase.GroupeUtilisateurAbonneService;
import ma.zs.univ.zynerator.service.AbstractServiceImpl;
import ma.zs.univ.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.univ.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.univ.service.facade.abonne.user.GroupeAbonneService ;
import ma.zs.univ.bean.core.user.Groupe ;
import ma.zs.univ.service.facade.abonne.user.UtilisateurAbonneService ;
import ma.zs.univ.bean.core.user.Utilisateur ;

import java.util.List;
@Service
public class GroupeUtilisateurAbonneServiceImpl implements GroupeUtilisateurAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public GroupeUtilisateur update(GroupeUtilisateur t) {
        GroupeUtilisateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{GroupeUtilisateur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public GroupeUtilisateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public GroupeUtilisateur findOrSave(GroupeUtilisateur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            GroupeUtilisateur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<GroupeUtilisateur> importData(List<GroupeUtilisateur> items) {
        List<GroupeUtilisateur> list = new ArrayList<>();
        for (GroupeUtilisateur t : items) {
            GroupeUtilisateur founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<GroupeUtilisateur> findAll() {
        return dao.findAll();
    }

    public List<GroupeUtilisateur> findByCriteria(GroupeUtilisateurCriteria criteria) {
        List<GroupeUtilisateur> content = null;
        if (criteria != null) {
            GroupeUtilisateurSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private GroupeUtilisateurSpecification constructSpecification(GroupeUtilisateurCriteria criteria) {
        GroupeUtilisateurSpecification mySpecification =  (GroupeUtilisateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(GroupeUtilisateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<GroupeUtilisateur> findPaginatedByCriteria(GroupeUtilisateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        GroupeUtilisateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GroupeUtilisateurCriteria criteria) {
        GroupeUtilisateurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<GroupeUtilisateur> findByGroupeId(Long id){
        return dao.findByGroupeId(id);
    }
    public int deleteByGroupeId(Long id){
        return dao.deleteByGroupeId(id);
    }
    public long countByGroupeCode(String code){
        return dao.countByGroupeCode(code);
    }
    public List<GroupeUtilisateur> findByUtilisateurId(Long id){
        return dao.findByUtilisateurId(id);
    }
    public int deleteByUtilisateurId(Long id){
        return dao.deleteByUtilisateurId(id);
    }
    public long countByUtilisateurId(Long id){
        return dao.countByUtilisateurId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(GroupeUtilisateur t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<GroupeUtilisateur> delete(List<GroupeUtilisateur> list) {
		List<GroupeUtilisateur> result = new ArrayList();
        if (list != null) {
            for (GroupeUtilisateur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public GroupeUtilisateur create(GroupeUtilisateur t) {
        GroupeUtilisateur loaded = findByReferenceEntity(t);
        GroupeUtilisateur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<GroupeUtilisateur> create(List<GroupeUtilisateur> ts) {
        List<GroupeUtilisateur> result = new ArrayList<>();
        if (ts != null) {
            for (GroupeUtilisateur t : ts) {
				GroupeUtilisateur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public GroupeUtilisateur findWithAssociatedLists(Long id){
        GroupeUtilisateur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<GroupeUtilisateur> update(List<GroupeUtilisateur> ts, boolean createIfNotExist) {
        List<GroupeUtilisateur> result = new ArrayList<>();
        if (ts != null) {
            for (GroupeUtilisateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    GroupeUtilisateur loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }





    public GroupeUtilisateur findByReferenceEntity(GroupeUtilisateur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(GroupeUtilisateur t){
        if( t != null) {
            t.setGroupe(groupeService.findOrSave(t.getGroupe()));
            t.setUtilisateur(utilisateurService.findOrSave(t.getUtilisateur()));
        }
    }



    public List<GroupeUtilisateur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<GroupeUtilisateur>> getToBeSavedAndToBeDeleted(List<GroupeUtilisateur> oldList, List<GroupeUtilisateur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<GroupeUtilisateur> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private GroupeAbonneService groupeService ;
    @Autowired
    private UtilisateurAbonneService utilisateurService ;

    private @Autowired GroupeUtilisateurDao dao;


}
