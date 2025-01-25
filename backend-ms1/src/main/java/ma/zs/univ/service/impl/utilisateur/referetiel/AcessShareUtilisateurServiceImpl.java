package ma.zs.univ.service.impl.utilisateur.referetiel;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.dao.criteria.core.referetiel.AcessShareCriteria;
import ma.zs.univ.dao.facade.core.referetiel.AcessShareDao;
import ma.zs.univ.dao.specification.core.referetiel.AcessShareSpecification;
import ma.zs.univ.service.facade.utilisateur.referetiel.AcessShareUtilisateurService;
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
public class AcessShareUtilisateurServiceImpl implements AcessShareUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AcessShare update(AcessShare t) {
        AcessShare loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AcessShare.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public AcessShare findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AcessShare findOrSave(AcessShare t) {
        if (t != null) {
            AcessShare result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<AcessShare> importData(List<AcessShare> items) {
        List<AcessShare> list = new ArrayList<>();
        for (AcessShare t : items) {
            AcessShare founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<AcessShare> findAll() {
        return dao.findAll();
    }

    public List<AcessShare> findByCriteria(AcessShareCriteria criteria) {
        List<AcessShare> content = null;
        if (criteria != null) {
            AcessShareSpecification mySpecification = constructSpecification(criteria);
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


    private AcessShareSpecification constructSpecification(AcessShareCriteria criteria) {
        AcessShareSpecification mySpecification =  (AcessShareSpecification) RefelexivityUtil.constructObjectUsingOneParam(AcessShareSpecification.class, criteria);
        return mySpecification;
    }

    public List<AcessShare> findPaginatedByCriteria(AcessShareCriteria criteria, int page, int pageSize, String order, String sortField) {
        AcessShareSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AcessShareCriteria criteria) {
        AcessShareSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(AcessShare t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AcessShare> delete(List<AcessShare> list) {
		List<AcessShare> result = new ArrayList();
        if (list != null) {
            for (AcessShare t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AcessShare create(AcessShare t) {
        AcessShare loaded = findByReferenceEntity(t);
        AcessShare saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AcessShare> create(List<AcessShare> ts) {
        List<AcessShare> result = new ArrayList<>();
        if (ts != null) {
            for (AcessShare t : ts) {
				AcessShare created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public AcessShare findWithAssociatedLists(Long id){
        AcessShare result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AcessShare> update(List<AcessShare> ts, boolean createIfNotExist) {
        List<AcessShare> result = new ArrayList<>();
        if (ts != null) {
            for (AcessShare t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AcessShare loadedItem = dao.findById(t.getId()).orElse(null);
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





    public AcessShare findByReferenceEntity(AcessShare t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<AcessShare> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<AcessShare>> getToBeSavedAndToBeDeleted(List<AcessShare> oldList, List<AcessShare> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<AcessShare> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired AcessShareDao dao;


}
