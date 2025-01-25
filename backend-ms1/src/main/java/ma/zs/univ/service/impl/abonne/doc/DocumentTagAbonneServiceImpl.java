package ma.zs.univ.service.impl.abonne.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentTag;
import ma.zs.univ.dao.criteria.core.doc.DocumentTagCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentTagDao;
import ma.zs.univ.dao.specification.core.doc.DocumentTagSpecification;
import ma.zs.univ.service.facade.abonne.doc.DocumentTagAbonneService;
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

import ma.zs.univ.service.facade.abonne.doc.TagAbonneService ;
import ma.zs.univ.bean.core.doc.Tag ;
import ma.zs.univ.service.facade.abonne.doc.DocumentAbonneService ;
import ma.zs.univ.bean.core.doc.Document ;

import java.util.List;
@Service
public class DocumentTagAbonneServiceImpl implements DocumentTagAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentTag update(DocumentTag t) {
        DocumentTag loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentTag.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentTag findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentTag findOrSave(DocumentTag t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DocumentTag result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentTag> importData(List<DocumentTag> items) {
        List<DocumentTag> list = new ArrayList<>();
        for (DocumentTag t : items) {
            DocumentTag founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentTag> findAll() {
        return dao.findAll();
    }

    public List<DocumentTag> findByCriteria(DocumentTagCriteria criteria) {
        List<DocumentTag> content = null;
        if (criteria != null) {
            DocumentTagSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentTagSpecification constructSpecification(DocumentTagCriteria criteria) {
        DocumentTagSpecification mySpecification =  (DocumentTagSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentTagSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentTag> findPaginatedByCriteria(DocumentTagCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentTagSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentTagCriteria criteria) {
        DocumentTagSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DocumentTag> findByDocumentId(Long id){
        return dao.findByDocumentId(id);
    }
    public int deleteByDocumentId(Long id){
        return dao.deleteByDocumentId(id);
    }
    public long countByDocumentReference(String reference){
        return dao.countByDocumentReference(reference);
    }
    public List<DocumentTag> findByTagId(Long id){
        return dao.findByTagId(id);
    }
    public int deleteByTagId(Long id){
        return dao.deleteByTagId(id);
    }
    public long countByTagCode(String code){
        return dao.countByTagCode(code);
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
    public int delete(DocumentTag t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentTag> delete(List<DocumentTag> list) {
		List<DocumentTag> result = new ArrayList();
        if (list != null) {
            for (DocumentTag t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentTag create(DocumentTag t) {
        DocumentTag loaded = findByReferenceEntity(t);
        DocumentTag saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentTag> create(List<DocumentTag> ts) {
        List<DocumentTag> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentTag t : ts) {
				DocumentTag created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentTag findWithAssociatedLists(Long id){
        DocumentTag result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentTag> update(List<DocumentTag> ts, boolean createIfNotExist) {
        List<DocumentTag> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentTag t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentTag loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentTag findByReferenceEntity(DocumentTag t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DocumentTag t){
        if( t != null) {
            t.setDocument(documentService.findOrSave(t.getDocument()));
            t.setTag(tagService.findOrSave(t.getTag()));
        }
    }



    public List<DocumentTag> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DocumentTag>> getToBeSavedAndToBeDeleted(List<DocumentTag> oldList, List<DocumentTag> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentTag> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TagAbonneService tagService ;
    @Autowired
    private DocumentAbonneService documentService ;

    private @Autowired DocumentTagDao dao;


}
