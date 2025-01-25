package ma.zs.univ.service.impl.admin.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import ma.zs.univ.dao.criteria.core.doc.EntiteAdministrativeTypeCriteria;
import ma.zs.univ.dao.facade.core.doc.EntiteAdministrativeTypeDao;
import ma.zs.univ.dao.specification.core.doc.EntiteAdministrativeTypeSpecification;
import ma.zs.univ.service.facade.admin.doc.EntiteAdministrativeTypeAdminService;
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
public class EntiteAdministrativeTypeAdminServiceImpl implements EntiteAdministrativeTypeAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntiteAdministrativeType update(EntiteAdministrativeType t) {
        EntiteAdministrativeType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EntiteAdministrativeType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EntiteAdministrativeType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EntiteAdministrativeType findOrSave(EntiteAdministrativeType t) {
        if (t != null) {
            EntiteAdministrativeType result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EntiteAdministrativeType> importData(List<EntiteAdministrativeType> items) {
        List<EntiteAdministrativeType> list = new ArrayList<>();
        for (EntiteAdministrativeType t : items) {
            EntiteAdministrativeType founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EntiteAdministrativeType> findAll() {
        return dao.findAll();
    }

    public List<EntiteAdministrativeType> findByCriteria(EntiteAdministrativeTypeCriteria criteria) {
        List<EntiteAdministrativeType> content = null;
        if (criteria != null) {
            EntiteAdministrativeTypeSpecification mySpecification = constructSpecification(criteria);
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


    private EntiteAdministrativeTypeSpecification constructSpecification(EntiteAdministrativeTypeCriteria criteria) {
        EntiteAdministrativeTypeSpecification mySpecification =  (EntiteAdministrativeTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(EntiteAdministrativeTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<EntiteAdministrativeType> findPaginatedByCriteria(EntiteAdministrativeTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        EntiteAdministrativeTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EntiteAdministrativeTypeCriteria criteria) {
        EntiteAdministrativeTypeSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(EntiteAdministrativeType t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteAdministrativeType> delete(List<EntiteAdministrativeType> list) {
		List<EntiteAdministrativeType> result = new ArrayList();
        if (list != null) {
            for (EntiteAdministrativeType t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntiteAdministrativeType create(EntiteAdministrativeType t) {
        EntiteAdministrativeType loaded = findByReferenceEntity(t);
        EntiteAdministrativeType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteAdministrativeType> create(List<EntiteAdministrativeType> ts) {
        List<EntiteAdministrativeType> result = new ArrayList<>();
        if (ts != null) {
            for (EntiteAdministrativeType t : ts) {
				EntiteAdministrativeType created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EntiteAdministrativeType findWithAssociatedLists(Long id){
        EntiteAdministrativeType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteAdministrativeType> update(List<EntiteAdministrativeType> ts, boolean createIfNotExist) {
        List<EntiteAdministrativeType> result = new ArrayList<>();
        if (ts != null) {
            for (EntiteAdministrativeType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EntiteAdministrativeType loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EntiteAdministrativeType findByReferenceEntity(EntiteAdministrativeType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EntiteAdministrativeType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EntiteAdministrativeType>> getToBeSavedAndToBeDeleted(List<EntiteAdministrativeType> oldList, List<EntiteAdministrativeType> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EntiteAdministrativeType> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired EntiteAdministrativeTypeDao dao;


}
