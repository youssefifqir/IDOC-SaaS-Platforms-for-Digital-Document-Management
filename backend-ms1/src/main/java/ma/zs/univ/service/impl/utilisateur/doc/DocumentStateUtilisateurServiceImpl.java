package ma.zs.univ.service.impl.utilisateur.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentState;
import ma.zs.univ.dao.criteria.core.doc.DocumentStateCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentStateDao;
import ma.zs.univ.dao.specification.core.doc.DocumentStateSpecification;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentStateUtilisateurService;
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
public class DocumentStateUtilisateurServiceImpl implements DocumentStateUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentState update(DocumentState t) {
        DocumentState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentState findOrSave(DocumentState t) {
        if (t != null) {
            DocumentState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentState> importData(List<DocumentState> items) {
        List<DocumentState> list = new ArrayList<>();
        for (DocumentState t : items) {
            DocumentState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentState> findAll() {
        return dao.findAll();
    }

    public List<DocumentState> findByCriteria(DocumentStateCriteria criteria) {
        List<DocumentState> content = null;
        if (criteria != null) {
            DocumentStateSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentStateSpecification constructSpecification(DocumentStateCriteria criteria) {
        DocumentStateSpecification mySpecification =  (DocumentStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentState> findPaginatedByCriteria(DocumentStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentStateCriteria criteria) {
        DocumentStateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(DocumentState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentState> delete(List<DocumentState> list) {
		List<DocumentState> result = new ArrayList();
        if (list != null) {
            for (DocumentState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentState create(DocumentState t) {
        DocumentState loaded = findByReferenceEntity(t);
        DocumentState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentState> create(List<DocumentState> ts) {
        List<DocumentState> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentState t : ts) {
				DocumentState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentState findWithAssociatedLists(Long id){
        DocumentState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentState> update(List<DocumentState> ts, boolean createIfNotExist) {
        List<DocumentState> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentState findByReferenceEntity(DocumentState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<DocumentState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DocumentState>> getToBeSavedAndToBeDeleted(List<DocumentState> oldList, List<DocumentState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DocumentStateDao dao;


}
