package ma.zs.univ.service.impl.admin.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import ma.zs.univ.dao.criteria.core.doc.DocumentFieldStateCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentFieldStateDao;
import ma.zs.univ.dao.specification.core.doc.DocumentFieldStateSpecification;
import ma.zs.univ.service.facade.admin.doc.DocumentFieldStateAdminService;
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
public class DocumentFieldStateAdminServiceImpl implements DocumentFieldStateAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentFieldState update(DocumentFieldState t) {
        DocumentFieldState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentFieldState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentFieldState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentFieldState findOrSave(DocumentFieldState t) {
        if (t != null) {
            DocumentFieldState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentFieldState> importData(List<DocumentFieldState> items) {
        List<DocumentFieldState> list = new ArrayList<>();
        for (DocumentFieldState t : items) {
            DocumentFieldState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentFieldState> findAll() {
        return dao.findAll();
    }

    public List<DocumentFieldState> findByCriteria(DocumentFieldStateCriteria criteria) {
        List<DocumentFieldState> content = null;
        if (criteria != null) {
            DocumentFieldStateSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentFieldStateSpecification constructSpecification(DocumentFieldStateCriteria criteria) {
        DocumentFieldStateSpecification mySpecification =  (DocumentFieldStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentFieldStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentFieldState> findPaginatedByCriteria(DocumentFieldStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentFieldStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentFieldStateCriteria criteria) {
        DocumentFieldStateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(DocumentFieldState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentFieldState> delete(List<DocumentFieldState> list) {
		List<DocumentFieldState> result = new ArrayList();
        if (list != null) {
            for (DocumentFieldState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentFieldState create(DocumentFieldState t) {
        DocumentFieldState loaded = findByReferenceEntity(t);
        DocumentFieldState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentFieldState> create(List<DocumentFieldState> ts) {
        List<DocumentFieldState> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentFieldState t : ts) {
				DocumentFieldState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentFieldState findWithAssociatedLists(Long id){
        DocumentFieldState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentFieldState> update(List<DocumentFieldState> ts, boolean createIfNotExist) {
        List<DocumentFieldState> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentFieldState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentFieldState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentFieldState findByReferenceEntity(DocumentFieldState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<DocumentFieldState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DocumentFieldState>> getToBeSavedAndToBeDeleted(List<DocumentFieldState> oldList, List<DocumentFieldState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentFieldState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DocumentFieldStateDao dao;


}
