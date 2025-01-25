package ma.zs.univ.service.impl.admin.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.dao.criteria.core.doc.DocumentCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentDao;
import ma.zs.univ.dao.specification.core.doc.DocumentSpecification;
import ma.zs.univ.service.facade.admin.doc.DocumentAdminService;
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

import ma.zs.univ.service.facade.admin.doc.DocumentStateAdminService ;
import ma.zs.univ.bean.core.doc.DocumentState ;
import ma.zs.univ.service.facade.admin.purchase.DocumentPartageGroupeAdminService ;
import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe ;
import ma.zs.univ.service.facade.admin.doc.DocumentTagAdminService ;
import ma.zs.univ.bean.core.doc.DocumentTag ;
import ma.zs.univ.service.facade.admin.doc.DocumentCategorieAdminService ;
import ma.zs.univ.bean.core.doc.DocumentCategorie ;
import ma.zs.univ.service.facade.admin.doc.DocumentFieldAdminService ;
import ma.zs.univ.bean.core.doc.DocumentField ;
import ma.zs.univ.service.facade.admin.purchase.DocumentPartageUtilisateurAdminService ;
import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur ;
import ma.zs.univ.service.facade.admin.doc.DocumentTypeAdminService ;
import ma.zs.univ.bean.core.doc.DocumentType ;
import ma.zs.univ.service.facade.admin.user.EntiteAdministrativeAdminService ;
import ma.zs.univ.bean.core.user.EntiteAdministrative ;
import ma.zs.univ.service.facade.admin.user.UtilisateurAdminService ;
import ma.zs.univ.bean.core.user.Utilisateur ;

import java.util.List;
@Service
public class DocumentAdminServiceImpl implements DocumentAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Document update(Document t) {
        Document loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Document.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Document findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Document findOrSave(Document t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Document result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Document> importData(List<Document> items) {
        List<Document> list = new ArrayList<>();
        for (Document t : items) {
            Document founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Document> findAll() {
        return dao.findAll();
    }

    public List<Document> findByCriteria(DocumentCriteria criteria) {
        List<Document> content = null;
        if (criteria != null) {
            DocumentSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentSpecification constructSpecification(DocumentCriteria criteria) {
        DocumentSpecification mySpecification =  (DocumentSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentSpecification.class, criteria);
        return mySpecification;
    }

    public List<Document> findPaginatedByCriteria(DocumentCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentCriteria criteria) {
        DocumentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Document> findByDocumentTypeId(Long id){
        return dao.findByDocumentTypeId(id);
    }
    public int deleteByDocumentTypeId(Long id){
        return dao.deleteByDocumentTypeId(id);
    }
    public long countByDocumentTypeCode(String code){
        return dao.countByDocumentTypeCode(code);
    }
    public List<Document> findByDocumentStateId(Long id){
        return dao.findByDocumentStateId(id);
    }
    public int deleteByDocumentStateId(Long id){
        return dao.deleteByDocumentStateId(id);
    }
    public long countByDocumentStateCode(String code){
        return dao.countByDocumentStateCode(code);
    }
    public List<Document> findByDocumentCategorieId(Long id){
        return dao.findByDocumentCategorieId(id);
    }
    public int deleteByDocumentCategorieId(Long id){
        return dao.deleteByDocumentCategorieId(id);
    }
    public long countByDocumentCategorieCode(String code){
        return dao.countByDocumentCategorieCode(code);
    }
    public List<Document> findByProprietaireId(Long id){
        return dao.findByProprietaireId(id);
    }
    public int deleteByProprietaireId(Long id){
        return dao.deleteByProprietaireId(id);
    }
    public long countByProprietaireId(Long id){
        return dao.countByProprietaireId(id);
    }
    public List<Document> findByEntiteAdministrativeId(Long id){
        return dao.findByEntiteAdministrativeId(id);
    }
    public int deleteByEntiteAdministrativeId(Long id){
        return dao.deleteByEntiteAdministrativeId(id);
    }
    public long countByEntiteAdministrativeCode(String code){
        return dao.countByEntiteAdministrativeCode(code);
    }
    public List<Document> findByEntiteAdministrativeProprietaireId(Long id){
        return dao.findByEntiteAdministrativeProprietaireId(id);
    }
    public int deleteByEntiteAdministrativeProprietaireId(Long id){
        return dao.deleteByEntiteAdministrativeProprietaireId(id);
    }
    public long countByEntiteAdministrativeProprietaireCode(String code){
        return dao.countByEntiteAdministrativeProprietaireCode(code);
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
    public int delete(Document t) {
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
        documentFieldService.deleteByDocumentId(id);
        documentPartageGroupeService.deleteByDocumentId(id);
        documentPartageUtilisateurService.deleteByDocumentId(id);
        documentTagService.deleteByDocumentId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Document> delete(List<Document> list) {
		List<Document> result = new ArrayList();
        if (list != null) {
            for (Document t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Document create(Document t) {
        Document loaded = findByReferenceEntity(t);
        Document saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getDocumentFields() != null) {
                t.getDocumentFields().forEach(element-> {
                    element.setDocument(saved);
                    documentFieldService.create(element);
                });
            }
            if (t.getDocumentPartageGroupes() != null) {
                t.getDocumentPartageGroupes().forEach(element-> {
                    element.setDocument(saved);
                    documentPartageGroupeService.create(element);
                });
            }
            if (t.getDocumentPartageUtilisateurs() != null) {
                t.getDocumentPartageUtilisateurs().forEach(element-> {
                    element.setDocument(saved);
                    documentPartageUtilisateurService.create(element);
                });
            }
            if (t.getDocumentTags() != null) {
                t.getDocumentTags().forEach(element-> {
                    element.setDocument(saved);
                    documentTagService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Document> create(List<Document> ts) {
        List<Document> result = new ArrayList<>();
        if (ts != null) {
            for (Document t : ts) {
				Document created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Document findWithAssociatedLists(Long id){
        Document result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setDocumentFields(documentFieldService.findByDocumentId(id));
            result.setDocumentPartageGroupes(documentPartageGroupeService.findByDocumentId(id));
            result.setDocumentPartageUtilisateurs(documentPartageUtilisateurService.findByDocumentId(id));
            result.setDocumentTags(documentTagService.findByDocumentId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Document> update(List<Document> ts, boolean createIfNotExist) {
        List<Document> result = new ArrayList<>();
        if (ts != null) {
            for (Document t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Document loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Document document){
    if(document !=null && document.getId() != null){
        List<List<DocumentField>> resultDocumentFields= documentFieldService.getToBeSavedAndToBeDeleted(documentFieldService.findByDocumentId(document.getId()),document.getDocumentFields());
            documentFieldService.delete(resultDocumentFields.get(1));
        ListUtil.emptyIfNull(resultDocumentFields.get(0)).forEach(e -> e.setDocument(document));
        documentFieldService.update(resultDocumentFields.get(0),true);
        List<List<DocumentPartageGroupe>> resultDocumentPartageGroupes= documentPartageGroupeService.getToBeSavedAndToBeDeleted(documentPartageGroupeService.findByDocumentId(document.getId()),document.getDocumentPartageGroupes());
            documentPartageGroupeService.delete(resultDocumentPartageGroupes.get(1));
        ListUtil.emptyIfNull(resultDocumentPartageGroupes.get(0)).forEach(e -> e.setDocument(document));
        documentPartageGroupeService.update(resultDocumentPartageGroupes.get(0),true);
        List<List<DocumentPartageUtilisateur>> resultDocumentPartageUtilisateurs= documentPartageUtilisateurService.getToBeSavedAndToBeDeleted(documentPartageUtilisateurService.findByDocumentId(document.getId()),document.getDocumentPartageUtilisateurs());
            documentPartageUtilisateurService.delete(resultDocumentPartageUtilisateurs.get(1));
        ListUtil.emptyIfNull(resultDocumentPartageUtilisateurs.get(0)).forEach(e -> e.setDocument(document));
        documentPartageUtilisateurService.update(resultDocumentPartageUtilisateurs.get(0),true);
        List<List<DocumentTag>> resultDocumentTags= documentTagService.getToBeSavedAndToBeDeleted(documentTagService.findByDocumentId(document.getId()),document.getDocumentTags());
            documentTagService.delete(resultDocumentTags.get(1));
        ListUtil.emptyIfNull(resultDocumentTags.get(0)).forEach(e -> e.setDocument(document));
        documentTagService.update(resultDocumentTags.get(0),true);
        }
    }




    public Document findByReferenceEntity(Document t){
        return t==null? null : dao.findByReference(t.getReference());
    }
    public void findOrSaveAssociatedObject(Document t){
        if( t != null) {
            t.setDocumentType(documentTypeService.findOrSave(t.getDocumentType()));
            t.setDocumentState(documentStateService.findOrSave(t.getDocumentState()));
            t.setDocumentCategorie(documentCategorieService.findOrSave(t.getDocumentCategorie()));
            t.setProprietaire(utilisateurService.findOrSave(t.getProprietaire()));
            t.setEntiteAdministrative(entiteAdministrativeService.findOrSave(t.getEntiteAdministrative()));
            t.setEntiteAdministrativeProprietaire(entiteAdministrativeService.findOrSave(t.getEntiteAdministrativeProprietaire()));
        }
    }



    public List<Document> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Document>> getToBeSavedAndToBeDeleted(List<Document> oldList, List<Document> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Document> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private DocumentStateAdminService documentStateService ;
    @Autowired
    private DocumentPartageGroupeAdminService documentPartageGroupeService ;
    @Autowired
    private DocumentTagAdminService documentTagService ;
    @Autowired
    private DocumentCategorieAdminService documentCategorieService ;
    @Autowired
    private DocumentFieldAdminService documentFieldService ;
    @Autowired
    private DocumentPartageUtilisateurAdminService documentPartageUtilisateurService ;
    @Autowired
    private DocumentTypeAdminService documentTypeService ;
    @Autowired
    private EntiteAdministrativeAdminService entiteAdministrativeService ;
    @Autowired
    private UtilisateurAdminService utilisateurService ;

    private @Autowired DocumentDao dao;


}
