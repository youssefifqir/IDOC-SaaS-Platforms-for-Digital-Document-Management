package ma.zs.univ.service.impl.admin.purchase;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur;
import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageUtilisateurCriteria;
import ma.zs.univ.dao.facade.core.purchase.DocumentPartageUtilisateurDao;
import ma.zs.univ.dao.specification.core.purchase.DocumentPartageUtilisateurSpecification;
import ma.zs.univ.service.facade.admin.purchase.DocumentPartageUtilisateurAdminService;
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

import ma.zs.univ.service.facade.admin.referetiel.AcessShareAdminService ;
import ma.zs.univ.bean.core.referetiel.AcessShare ;
import ma.zs.univ.service.facade.admin.doc.DocumentAdminService ;
import ma.zs.univ.bean.core.doc.Document ;
import ma.zs.univ.service.facade.admin.user.UtilisateurAdminService ;
import ma.zs.univ.bean.core.user.Utilisateur ;

import java.util.List;
@Service
public class DocumentPartageUtilisateurAdminServiceImpl implements DocumentPartageUtilisateurAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentPartageUtilisateur update(DocumentPartageUtilisateur t) {
        DocumentPartageUtilisateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentPartageUtilisateur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentPartageUtilisateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentPartageUtilisateur findOrSave(DocumentPartageUtilisateur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DocumentPartageUtilisateur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentPartageUtilisateur> importData(List<DocumentPartageUtilisateur> items) {
        List<DocumentPartageUtilisateur> list = new ArrayList<>();
        for (DocumentPartageUtilisateur t : items) {
            DocumentPartageUtilisateur founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentPartageUtilisateur> findAll() {
        return dao.findAll();
    }

    public List<DocumentPartageUtilisateur> findByCriteria(DocumentPartageUtilisateurCriteria criteria) {
        List<DocumentPartageUtilisateur> content = null;
        if (criteria != null) {
            DocumentPartageUtilisateurSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentPartageUtilisateurSpecification constructSpecification(DocumentPartageUtilisateurCriteria criteria) {
        DocumentPartageUtilisateurSpecification mySpecification =  (DocumentPartageUtilisateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentPartageUtilisateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentPartageUtilisateur> findPaginatedByCriteria(DocumentPartageUtilisateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentPartageUtilisateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentPartageUtilisateurCriteria criteria) {
        DocumentPartageUtilisateurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DocumentPartageUtilisateur> findByDocumentId(Long id){
        return dao.findByDocumentId(id);
    }
    public int deleteByDocumentId(Long id){
        return dao.deleteByDocumentId(id);
    }
    public long countByDocumentReference(String reference){
        return dao.countByDocumentReference(reference);
    }
    public List<DocumentPartageUtilisateur> findByUtilisateurId(Long id){
        return dao.findByUtilisateurId(id);
    }
    public int deleteByUtilisateurId(Long id){
        return dao.deleteByUtilisateurId(id);
    }
    public long countByUtilisateurId(Long id){
        return dao.countByUtilisateurId(id);
    }
    public List<DocumentPartageUtilisateur> findByAcessShareId(Long id){
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
    public int delete(DocumentPartageUtilisateur t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentPartageUtilisateur> delete(List<DocumentPartageUtilisateur> list) {
		List<DocumentPartageUtilisateur> result = new ArrayList();
        if (list != null) {
            for (DocumentPartageUtilisateur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentPartageUtilisateur create(DocumentPartageUtilisateur t) {
        DocumentPartageUtilisateur loaded = findByReferenceEntity(t);
        DocumentPartageUtilisateur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentPartageUtilisateur> create(List<DocumentPartageUtilisateur> ts) {
        List<DocumentPartageUtilisateur> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentPartageUtilisateur t : ts) {
				DocumentPartageUtilisateur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentPartageUtilisateur findWithAssociatedLists(Long id){
        DocumentPartageUtilisateur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentPartageUtilisateur> update(List<DocumentPartageUtilisateur> ts, boolean createIfNotExist) {
        List<DocumentPartageUtilisateur> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentPartageUtilisateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentPartageUtilisateur loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentPartageUtilisateur findByReferenceEntity(DocumentPartageUtilisateur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DocumentPartageUtilisateur t){
        if( t != null) {
            t.setDocument(documentService.findOrSave(t.getDocument()));
            t.setUtilisateur(utilisateurService.findOrSave(t.getUtilisateur()));
            t.setAcessShare(acessShareService.findOrSave(t.getAcessShare()));
        }
    }



    public List<DocumentPartageUtilisateur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DocumentPartageUtilisateur>> getToBeSavedAndToBeDeleted(List<DocumentPartageUtilisateur> oldList, List<DocumentPartageUtilisateur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentPartageUtilisateur> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private AcessShareAdminService acessShareService ;
    @Autowired
    private DocumentAdminService documentService ;
    @Autowired
    private UtilisateurAdminService utilisateurService ;

    private @Autowired DocumentPartageUtilisateurDao dao;


}
