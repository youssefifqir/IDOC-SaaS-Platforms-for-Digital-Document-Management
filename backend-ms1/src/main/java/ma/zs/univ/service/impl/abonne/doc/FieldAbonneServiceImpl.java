package ma.zs.univ.service.impl.abonne.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.Field;
import ma.zs.univ.dao.criteria.core.doc.FieldCriteria;
import ma.zs.univ.dao.facade.core.doc.FieldDao;
import ma.zs.univ.dao.specification.core.doc.FieldSpecification;
import ma.zs.univ.service.facade.abonne.doc.FieldAbonneService;
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
public class FieldAbonneServiceImpl implements FieldAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Field update(Field t) {
        Field loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Field.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Field findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Field findOrSave(Field t) {
        if (t != null) {
            Field result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Field> importData(List<Field> items) {
        List<Field> list = new ArrayList<>();
        for (Field t : items) {
            Field founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Field> findAll() {
        return dao.findAll();
    }

    public List<Field> findByCriteria(FieldCriteria criteria) {
        List<Field> content = null;
        if (criteria != null) {
            FieldSpecification mySpecification = constructSpecification(criteria);
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


    private FieldSpecification constructSpecification(FieldCriteria criteria) {
        FieldSpecification mySpecification =  (FieldSpecification) RefelexivityUtil.constructObjectUsingOneParam(FieldSpecification.class, criteria);
        return mySpecification;
    }

    public List<Field> findPaginatedByCriteria(FieldCriteria criteria, int page, int pageSize, String order, String sortField) {
        FieldSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FieldCriteria criteria) {
        FieldSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Field t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Field> delete(List<Field> list) {
		List<Field> result = new ArrayList();
        if (list != null) {
            for (Field t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Field create(Field t) {
        Field loaded = findByReferenceEntity(t);
        Field saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Field> create(List<Field> ts) {
        List<Field> result = new ArrayList<>();
        if (ts != null) {
            for (Field t : ts) {
				Field created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Field findWithAssociatedLists(Long id){
        Field result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Field> update(List<Field> ts, boolean createIfNotExist) {
        List<Field> result = new ArrayList<>();
        if (ts != null) {
            for (Field t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Field loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Field findByReferenceEntity(Field t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Field> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Field>> getToBeSavedAndToBeDeleted(List<Field> oldList, List<Field> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Field> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired FieldDao dao;


}
