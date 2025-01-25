package ma.zs.univ.service.impl.abonne.user;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.user.Groupe;
import ma.zs.univ.dao.criteria.core.user.GroupeCriteria;
import ma.zs.univ.dao.facade.core.user.GroupeDao;
import ma.zs.univ.dao.specification.core.user.GroupeSpecification;
import ma.zs.univ.service.facade.abonne.user.GroupeAbonneService;
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

import ma.zs.univ.service.facade.abonne.purchase.GroupeUtilisateurAbonneService ;
import ma.zs.univ.bean.core.purchase.GroupeUtilisateur ;
import ma.zs.univ.service.facade.abonne.user.UtilisateurAbonneService ;
import ma.zs.univ.bean.core.user.Utilisateur ;

import java.util.List;
@Service
public class GroupeAbonneServiceImpl implements GroupeAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Groupe update(Groupe t) {
        Groupe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Groupe.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Groupe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Groupe findOrSave(Groupe t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Groupe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Groupe> importData(List<Groupe> items) {
        List<Groupe> list = new ArrayList<>();
        for (Groupe t : items) {
            Groupe founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Groupe> findAll() {
        return dao.findAll();
    }

    public List<Groupe> findByCriteria(GroupeCriteria criteria) {
        List<Groupe> content = null;
        if (criteria != null) {
            GroupeSpecification mySpecification = constructSpecification(criteria);
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


    private GroupeSpecification constructSpecification(GroupeCriteria criteria) {
        GroupeSpecification mySpecification =  (GroupeSpecification) RefelexivityUtil.constructObjectUsingOneParam(GroupeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Groupe> findPaginatedByCriteria(GroupeCriteria criteria, int page, int pageSize, String order, String sortField) {
        GroupeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GroupeCriteria criteria) {
        GroupeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Groupe> findByUtilisateurId(Long id){
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
            deleteAssociatedLists(id);
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
    public int delete(Groupe t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        groupeUtilisateurService.deleteByGroupeId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Groupe> delete(List<Groupe> list) {
		List<Groupe> result = new ArrayList();
        if (list != null) {
            for (Groupe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Groupe create(Groupe t) {
        Groupe loaded = findByReferenceEntity(t);
        Groupe saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getGroupeUtilisateurs() != null) {
                t.getGroupeUtilisateurs().forEach(element-> {
                    element.setGroupe(saved);
                    groupeUtilisateurService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Groupe> create(List<Groupe> ts) {
        List<Groupe> result = new ArrayList<>();
        if (ts != null) {
            for (Groupe t : ts) {
				Groupe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Groupe findWithAssociatedLists(Long id){
        Groupe result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setGroupeUtilisateurs(groupeUtilisateurService.findByGroupeId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Groupe> update(List<Groupe> ts, boolean createIfNotExist) {
        List<Groupe> result = new ArrayList<>();
        if (ts != null) {
            for (Groupe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Groupe loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Groupe groupe){
    if(groupe !=null && groupe.getId() != null){
        List<List<GroupeUtilisateur>> resultGroupeUtilisateurs= groupeUtilisateurService.getToBeSavedAndToBeDeleted(groupeUtilisateurService.findByGroupeId(groupe.getId()),groupe.getGroupeUtilisateurs());
            groupeUtilisateurService.delete(resultGroupeUtilisateurs.get(1));
        ListUtil.emptyIfNull(resultGroupeUtilisateurs.get(0)).forEach(e -> e.setGroupe(groupe));
        groupeUtilisateurService.update(resultGroupeUtilisateurs.get(0),true);
        }
    }




    public Groupe findByReferenceEntity(Groupe t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Groupe t){
        if( t != null) {
            t.setUtilisateur(utilisateurService.findOrSave(t.getUtilisateur()));
        }
    }



    public List<Groupe> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Groupe>> getToBeSavedAndToBeDeleted(List<Groupe> oldList, List<Groupe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Groupe> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private GroupeUtilisateurAbonneService groupeUtilisateurService ;
    @Autowired
    private UtilisateurAbonneService utilisateurService ;

    private @Autowired GroupeDao dao;


}
