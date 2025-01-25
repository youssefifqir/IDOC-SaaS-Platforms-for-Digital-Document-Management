package ma.zs.univ.service.impl.utilisateur.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import ma.zs.univ.dao.criteria.core.doc.DocumentCategorieCriteria;
import ma.zs.univ.dao.facade.core.doc.DocumentCategorieDao;
import ma.zs.univ.dao.specification.core.doc.DocumentCategorieSpecification;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentCategorieUtilisateurService;
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
public class DocumentCategorieUtilisateurServiceImpl implements DocumentCategorieUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentCategorie update(DocumentCategorie t) {
        DocumentCategorie loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DocumentCategorie.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DocumentCategorie findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DocumentCategorie findOrSave(DocumentCategorie t) {
        if (t != null) {
            DocumentCategorie result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DocumentCategorie> importData(List<DocumentCategorie> items) {
        List<DocumentCategorie> list = new ArrayList<>();
        for (DocumentCategorie t : items) {
            DocumentCategorie founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DocumentCategorie> findAll() {
        return dao.findAll();
    }

    public List<DocumentCategorie> findByCriteria(DocumentCategorieCriteria criteria) {
        List<DocumentCategorie> content = null;
        if (criteria != null) {
            DocumentCategorieSpecification mySpecification = constructSpecification(criteria);
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


    private DocumentCategorieSpecification constructSpecification(DocumentCategorieCriteria criteria) {
        DocumentCategorieSpecification mySpecification =  (DocumentCategorieSpecification) RefelexivityUtil.constructObjectUsingOneParam(DocumentCategorieSpecification.class, criteria);
        return mySpecification;
    }

    public List<DocumentCategorie> findPaginatedByCriteria(DocumentCategorieCriteria criteria, int page, int pageSize, String order, String sortField) {
        DocumentCategorieSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DocumentCategorieCriteria criteria) {
        DocumentCategorieSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(DocumentCategorie t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentCategorie> delete(List<DocumentCategorie> list) {
		List<DocumentCategorie> result = new ArrayList();
        if (list != null) {
            for (DocumentCategorie t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DocumentCategorie create(DocumentCategorie t) {
        DocumentCategorie loaded = findByReferenceEntity(t);
        DocumentCategorie saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentCategorie> create(List<DocumentCategorie> ts) {
        List<DocumentCategorie> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentCategorie t : ts) {
				DocumentCategorie created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DocumentCategorie findWithAssociatedLists(Long id){
        DocumentCategorie result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DocumentCategorie> update(List<DocumentCategorie> ts, boolean createIfNotExist) {
        List<DocumentCategorie> result = new ArrayList<>();
        if (ts != null) {
            for (DocumentCategorie t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DocumentCategorie loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DocumentCategorie findByReferenceEntity(DocumentCategorie t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<DocumentCategorie> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DocumentCategorie>> getToBeSavedAndToBeDeleted(List<DocumentCategorie> oldList, List<DocumentCategorie> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DocumentCategorie> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DocumentCategorieDao dao;


}
