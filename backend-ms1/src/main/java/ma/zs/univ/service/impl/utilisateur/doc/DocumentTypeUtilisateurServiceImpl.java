package ma.zs.univ.service.impl.utilisateur.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentType;
import ma.zs.univ.dao.criteria.core.doc.DocumentTypeCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentTypeDao;
import ma.zs.univ.dao.specification.core.doc.DocumentTypeSpecification;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentTypeUtilisateurService;
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


import java.util.List;
@Service
public class DocumentTypeUtilisateurServiceImpl implements DocumentTypeUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentType update(DocumentType t) {
        DocumentType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentType findOrSave(DocumentType t) {
        if (t != null) {
            DocumentType result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentType> importData(List<DocumentType> items) {
        List<DocumentType> list = new ArrayList<>();
        for (DocumentType t : items) {
            DocumentType founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentType> findAll() {
        return dao.findAll();
    }

    public List<DocumentType> findByCriteria(DocumentTypeCriteria criteria) {
        List<DocumentType> content = null;
        if (criteria != null) {
            DocumentTypeSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentTypeSpecification constructSpecification(DocumentTypeCriteria criteria) {
        DocumentTypeSpecification mySpecification =  (DocumentTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentType> findPaginatedByCriteria(DocumentTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentTypeCriteria criteria) {
        DocumentTypeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(DocumentType t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentType> delete(List<DocumentType> list) {
		List<DocumentType> result = new ArrayList();
        if (list != null) {
            for (DocumentType t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentType create(DocumentType t) {
        DocumentType loaded = findByReferenceEntity(t);
        DocumentType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentType> create(List<DocumentType> ts) {
        List<DocumentType> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentType t : ts) {
				DocumentType created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentType findWithAssociatedLists(Long id){
        DocumentType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentType> update(List<DocumentType> ts, boolean createIfNotExist) {
        List<DocumentType> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentType loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentType findByReferenceEntity(DocumentType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<DocumentType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DocumentType>> getToBeSavedAndToBeDeleted(List<DocumentType> oldList, List<DocumentType> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentType> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DocumentTypeDao dao;


}
