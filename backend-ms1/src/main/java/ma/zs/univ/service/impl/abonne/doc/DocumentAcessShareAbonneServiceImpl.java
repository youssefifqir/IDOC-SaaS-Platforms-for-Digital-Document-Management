package ma.zs.univ.service.impl.abonne.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentAcessShare;
import ma.zs.univ.dao.criteria.core.doc.DocumentAcessShareCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentAcessShareDao;
import ma.zs.univ.dao.specification.core.doc.DocumentAcessShareSpecification;
import ma.zs.univ.service.facade.abonne.doc.DocumentAcessShareAbonneService;
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

import ma.zs.univ.service.facade.abonne.referetiel.AcessShareAbonneService ;
import ma.zs.univ.bean.core.referetiel.AcessShare ;
import ma.zs.univ.service.facade.abonne.doc.DocumentAbonneService ;
import ma.zs.univ.bean.core.doc.Document ;

import java.util.List;
@Service
public class DocumentAcessShareAbonneServiceImpl implements DocumentAcessShareAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentAcessShare update(DocumentAcessShare t) {
        DocumentAcessShare loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentAcessShare.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentAcessShare findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentAcessShare findOrSave(DocumentAcessShare t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DocumentAcessShare result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentAcessShare> importData(List<DocumentAcessShare> items) {
        List<DocumentAcessShare> list = new ArrayList<>();
        for (DocumentAcessShare t : items) {
            DocumentAcessShare founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentAcessShare> findAll() {
        return dao.findAll();
    }

    public List<DocumentAcessShare> findByCriteria(DocumentAcessShareCriteria criteria) {
        List<DocumentAcessShare> content = null;
        if (criteria != null) {
            DocumentAcessShareSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentAcessShareSpecification constructSpecification(DocumentAcessShareCriteria criteria) {
        DocumentAcessShareSpecification mySpecification =  (DocumentAcessShareSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentAcessShareSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentAcessShare> findPaginatedByCriteria(DocumentAcessShareCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentAcessShareSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentAcessShareCriteria criteria) {
        DocumentAcessShareSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DocumentAcessShare> findByDocumentId(Long id){
        return dao.findByDocumentId(id);
    }
    public int deleteByDocumentId(Long id){
        return dao.deleteByDocumentId(id);
    }
    public long countByDocumentReference(String reference){
        return dao.countByDocumentReference(reference);
    }
    public List<DocumentAcessShare> findByAcessShareId(Long id){
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
    public int delete(DocumentAcessShare t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentAcessShare> delete(List<DocumentAcessShare> list) {
		List<DocumentAcessShare> result = new ArrayList();
        if (list != null) {
            for (DocumentAcessShare t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentAcessShare create(DocumentAcessShare t) {
        DocumentAcessShare loaded = findByReferenceEntity(t);
        DocumentAcessShare saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentAcessShare> create(List<DocumentAcessShare> ts) {
        List<DocumentAcessShare> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentAcessShare t : ts) {
				DocumentAcessShare created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentAcessShare findWithAssociatedLists(Long id){
        DocumentAcessShare result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentAcessShare> update(List<DocumentAcessShare> ts, boolean createIfNotExist) {
        List<DocumentAcessShare> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentAcessShare t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentAcessShare loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentAcessShare findByReferenceEntity(DocumentAcessShare t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DocumentAcessShare t){
        if( t != null) {
            t.setDocument(documentService.findOrSave(t.getDocument()));
            t.setAcessShare(acessShareService.findOrSave(t.getAcessShare()));
        }
    }



    public List<DocumentAcessShare> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DocumentAcessShare>> getToBeSavedAndToBeDeleted(List<DocumentAcessShare> oldList, List<DocumentAcessShare> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentAcessShare> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private AcessShareAbonneService acessShareService ;
    @Autowired
    private DocumentAbonneService documentService ;

    private @Autowired DocumentAcessShareDao dao;


}
