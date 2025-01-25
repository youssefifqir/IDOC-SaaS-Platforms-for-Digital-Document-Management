package ma.zs.univ.service.impl.abonne.referetiel;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import ma.zs.univ.dao.criteria.core.referetiel.AcessManagementCriteria;
import ma.zs.univ.dao.facade.core.referetiel.AcessManagementDao;
import ma.zs.univ.dao.specification.core.referetiel.AcessManagementSpecification;
import ma.zs.univ.service.facade.abonne.referetiel.AcessManagementAbonneService;
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
public class AcessManagementAbonneServiceImpl implements AcessManagementAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AcessManagement update(AcessManagement t) {
        AcessManagement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AcessManagement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public AcessManagement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AcessManagement findOrSave(AcessManagement t) {
        if (t != null) {
            AcessManagement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<AcessManagement> importData(List<AcessManagement> items) {
        List<AcessManagement> list = new ArrayList<>();
        for (AcessManagement t : items) {
            AcessManagement founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<AcessManagement> findAll() {
        return dao.findAll();
    }

    public List<AcessManagement> findByCriteria(AcessManagementCriteria criteria) {
        List<AcessManagement> content = null;
        if (criteria != null) {
            AcessManagementSpecification mySpecification = constructSpecification(criteria);
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


    private AcessManagementSpecification constructSpecification(AcessManagementCriteria criteria) {
        AcessManagementSpecification mySpecification =  (AcessManagementSpecification) RefelexivityUtil.constructObjectUsingOneParam(AcessManagementSpecification.class, criteria);
        return mySpecification;
    }

    public List<AcessManagement> findPaginatedByCriteria(AcessManagementCriteria criteria, int page, int pageSize, String order, String sortField) {
        AcessManagementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AcessManagementCriteria criteria) {
        AcessManagementSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(AcessManagement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AcessManagement> delete(List<AcessManagement> list) {
		List<AcessManagement> result = new ArrayList();
        if (list != null) {
            for (AcessManagement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AcessManagement create(AcessManagement t) {
        AcessManagement loaded = findByReferenceEntity(t);
        AcessManagement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AcessManagement> create(List<AcessManagement> ts) {
        List<AcessManagement> result = new ArrayList<>();
        if (ts != null) {
            for (AcessManagement t : ts) {
				AcessManagement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public AcessManagement findWithAssociatedLists(Long id){
        AcessManagement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AcessManagement> update(List<AcessManagement> ts, boolean createIfNotExist) {
        List<AcessManagement> result = new ArrayList<>();
        if (ts != null) {
            for (AcessManagement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AcessManagement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public AcessManagement findByReferenceEntity(AcessManagement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<AcessManagement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<AcessManagement>> getToBeSavedAndToBeDeleted(List<AcessManagement> oldList, List<AcessManagement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<AcessManagement> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired AcessManagementDao dao;


}
