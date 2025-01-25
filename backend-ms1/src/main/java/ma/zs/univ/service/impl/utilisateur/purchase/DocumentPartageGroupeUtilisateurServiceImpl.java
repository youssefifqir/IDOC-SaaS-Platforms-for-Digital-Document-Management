package ma.zs.univ.service.impl.utilisateur.purchase;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe;
import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageGroupeCriteria;
import ma.zs.univ.dao.facade.core.purchase.DocumentPartageGroupeDao;
import ma.zs.univ.dao.specification.core.purchase.DocumentPartageGroupeSpecification;
import ma.zs.univ.service.facade.utilisateur.purchase.DocumentPartageGroupeUtilisateurService;
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

import ma.zs.univ.service.facade.utilisateur.referetiel.AcessShareUtilisateurService ;
import ma.zs.univ.bean.core.referetiel.AcessShare ;
import ma.zs.univ.service.facade.utilisateur.user.GroupeUtilisateurService ;
import ma.zs.univ.bean.core.user.Groupe ;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentUtilisateurService ;
import ma.zs.univ.bean.core.doc.Document ;

import java.util.List;
@Service
public class DocumentPartageGroupeUtilisateurServiceImpl implements DocumentPartageGroupeUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentPartageGroupe update(DocumentPartageGroupe t) {
        DocumentPartageGroupe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentPartageGroupe.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentPartageGroupe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentPartageGroupe findOrSave(DocumentPartageGroupe t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DocumentPartageGroupe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentPartageGroupe> importData(List<DocumentPartageGroupe> items) {
        List<DocumentPartageGroupe> list = new ArrayList<>();
        for (DocumentPartageGroupe t : items) {
            DocumentPartageGroupe founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentPartageGroupe> findAll() {
        return dao.findAll();
    }

    public List<DocumentPartageGroupe> findByCriteria(DocumentPartageGroupeCriteria criteria) {
        List<DocumentPartageGroupe> content = null;
        if (criteria != null) {
            DocumentPartageGroupeSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentPartageGroupeSpecification constructSpecification(DocumentPartageGroupeCriteria criteria) {
        DocumentPartageGroupeSpecification mySpecification =  (DocumentPartageGroupeSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentPartageGroupeSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentPartageGroupe> findPaginatedByCriteria(DocumentPartageGroupeCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentPartageGroupeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentPartageGroupeCriteria criteria) {
        DocumentPartageGroupeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DocumentPartageGroupe> findByDocumentId(Long id){
        return dao.findByDocumentId(id);
    }
    public int deleteByDocumentId(Long id){
        return dao.deleteByDocumentId(id);
    }
    public long countByDocumentReference(String reference){
        return dao.countByDocumentReference(reference);
    }
    public List<DocumentPartageGroupe> findByGroupeId(Long id){
        return dao.findByGroupeId(id);
    }
    public int deleteByGroupeId(Long id){
        return dao.deleteByGroupeId(id);
    }
    public long countByGroupeCode(String code){
        return dao.countByGroupeCode(code);
    }
    public List<DocumentPartageGroupe> findByAcessShareId(Long id){
        return dao.findByAcessShareId(id);
    }
    public int deleteByAcessShareId(Long id){
        return dao.deleteByAcessShareId(id);
    }
    public long countByAcessShareCode(String code){
        return dao.countByAcessShareCode(code);
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
    public int delete(DocumentPartageGroupe t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentPartageGroupe> delete(List<DocumentPartageGroupe> list) {
		List<DocumentPartageGroupe> result = new ArrayList();
        if (list != null) {
            for (DocumentPartageGroupe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentPartageGroupe create(DocumentPartageGroupe t) {
        DocumentPartageGroupe loaded = findByReferenceEntity(t);
        DocumentPartageGroupe saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentPartageGroupe> create(List<DocumentPartageGroupe> ts) {
        List<DocumentPartageGroupe> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentPartageGroupe t : ts) {
				DocumentPartageGroupe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentPartageGroupe findWithAssociatedLists(Long id){
        DocumentPartageGroupe result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentPartageGroupe> update(List<DocumentPartageGroupe> ts, boolean createIfNotExist) {
        List<DocumentPartageGroupe> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentPartageGroupe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentPartageGroupe loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentPartageGroupe findByReferenceEntity(DocumentPartageGroupe t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DocumentPartageGroupe t){
        if( t != null) {
            t.setDocument(documentService.findOrSave(t.getDocument()));
            t.setGroupe(groupeService.findOrSave(t.getGroupe()));
            t.setAcessShare(acessShareService.findOrSave(t.getAcessShare()));
        }
    }



    public List<DocumentPartageGroupe> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DocumentPartageGroupe>> getToBeSavedAndToBeDeleted(List<DocumentPartageGroupe> oldList, List<DocumentPartageGroupe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentPartageGroupe> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private AcessShareUtilisateurService acessShareService ;
    @Autowired
    private GroupeUtilisateurService groupeService ;
    @Autowired
    private DocumentUtilisateurService documentService ;

    private @Autowired DocumentPartageGroupeDao dao;


}
