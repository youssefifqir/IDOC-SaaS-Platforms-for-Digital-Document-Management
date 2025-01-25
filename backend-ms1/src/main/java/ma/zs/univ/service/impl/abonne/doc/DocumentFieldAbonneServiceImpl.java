package ma.zs.univ.service.impl.abonne.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentField;
import ma.zs.univ.dao.criteria.core.doc.DocumentFieldCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentFieldDao;
import ma.zs.univ.dao.specification.core.doc.DocumentFieldSpecification;
import ma.zs.univ.service.facade.abonne.doc.DocumentFieldAbonneService;
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

import ma.zs.univ.service.facade.abonne.doc.FieldAbonneService ;
import ma.zs.univ.bean.core.doc.Field ;
import ma.zs.univ.service.facade.abonne.doc.DocumentFieldStateAbonneService ;
import ma.zs.univ.bean.core.doc.DocumentFieldState ;
import ma.zs.univ.service.facade.abonne.doc.DocumentAbonneService ;
import ma.zs.univ.bean.core.doc.Document ;

import java.util.List;
@Service
public class DocumentFieldAbonneServiceImpl implements DocumentFieldAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentField update(DocumentField t) {
        DocumentField loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentField.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentField findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentField findOrSave(DocumentField t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DocumentField result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentField> importData(List<DocumentField> items) {
        List<DocumentField> list = new ArrayList<>();
        for (DocumentField t : items) {
            DocumentField founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentField> findAll() {
        return dao.findAll();
    }

    public List<DocumentField> findByCriteria(DocumentFieldCriteria criteria) {
        List<DocumentField> content = null;
        if (criteria != null) {
            DocumentFieldSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentFieldSpecification constructSpecification(DocumentFieldCriteria criteria) {
        DocumentFieldSpecification mySpecification =  (DocumentFieldSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentFieldSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentField> findPaginatedByCriteria(DocumentFieldCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentFieldSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentFieldCriteria criteria) {
        DocumentFieldSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DocumentField> findByFieldId(Long id){
        return dao.findByFieldId(id);
    }
    public int deleteByFieldId(Long id){
        return dao.deleteByFieldId(id);
    }
    public long countByFieldCode(String code){
        return dao.countByFieldCode(code);
    }
    public List<DocumentField> findByDocumentId(Long id){
        return dao.findByDocumentId(id);
    }
    public int deleteByDocumentId(Long id){
        return dao.deleteByDocumentId(id);
    }
    public long countByDocumentReference(String reference){
        return dao.countByDocumentReference(reference);
    }
    public List<DocumentField> findByDocumentFieldStateId(Long id){
        return dao.findByDocumentFieldStateId(id);
    }
    public int deleteByDocumentFieldStateId(Long id){
        return dao.deleteByDocumentFieldStateId(id);
    }
    public long countByDocumentFieldStateCode(String code){
        return dao.countByDocumentFieldStateCode(code);
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
    public int delete(DocumentField t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentField> delete(List<DocumentField> list) {
		List<DocumentField> result = new ArrayList();
        if (list != null) {
            for (DocumentField t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentField create(DocumentField t) {
        DocumentField loaded = findByReferenceEntity(t);
        DocumentField saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentField> create(List<DocumentField> ts) {
        List<DocumentField> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentField t : ts) {
				DocumentField created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentField findWithAssociatedLists(Long id){
        DocumentField result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentField> update(List<DocumentField> ts, boolean createIfNotExist) {
        List<DocumentField> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentField t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentField loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentField findByReferenceEntity(DocumentField t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DocumentField t){
        if( t != null) {
            t.setField(fieldService.findOrSave(t.getField()));
            t.setDocument(documentService.findOrSave(t.getDocument()));
            t.setDocumentFieldState(documentFieldStateService.findOrSave(t.getDocumentFieldState()));
        }
    }



    public List<DocumentField> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DocumentField>> getToBeSavedAndToBeDeleted(List<DocumentField> oldList, List<DocumentField> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentField> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private FieldAbonneService fieldService ;
    @Autowired
    private DocumentFieldStateAbonneService documentFieldStateService ;
    @Autowired
    private DocumentAbonneService documentService ;

    private @Autowired DocumentFieldDao dao;


}
