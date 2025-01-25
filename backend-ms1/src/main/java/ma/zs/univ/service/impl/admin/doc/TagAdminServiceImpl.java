package ma.zs.univ.service.impl.admin.doc;


import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.doc.Tag;
import ma.zs.univ.dao.criteria.core.doc.TagCriteria;
import ma.zs.univ.dao.facade.core.doc.TagDao;
import ma.zs.univ.dao.specification.core.doc.TagSpecification;
import ma.zs.univ.service.facade.admin.doc.TagAdminService;
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
public class TagAdminServiceImpl implements TagAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Tag update(Tag t) {
        Tag loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Tag.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Tag findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Tag findOrSave(Tag t) {
        if (t != null) {
            Tag result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Tag> importData(List<Tag> items) {
        List<Tag> list = new ArrayList<>();
        for (Tag t : items) {
            Tag founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Tag> findAll() {
        return dao.findAll();
    }

    public List<Tag> findByCriteria(TagCriteria criteria) {
        List<Tag> content = null;
        if (criteria != null) {
            TagSpecification mySpecification = constructSpecification(criteria);
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


    private TagSpecification constructSpecification(TagCriteria criteria) {
        TagSpecification mySpecification =  (TagSpecification) RefelexivityUtil.constructObjectUsingOneParam(TagSpecification.class, criteria);
        return mySpecification;
    }

    public List<Tag> findPaginatedByCriteria(TagCriteria criteria, int page, int pageSize, String order, String sortField) {
        TagSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TagCriteria criteria) {
        TagSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Tag t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tag> delete(List<Tag> list) {
		List<Tag> result = new ArrayList();
        if (list != null) {
            for (Tag t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Tag create(Tag t) {
        Tag loaded = findByReferenceEntity(t);
        Tag saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tag> create(List<Tag> ts) {
        List<Tag> result = new ArrayList<>();
        if (ts != null) {
            for (Tag t : ts) {
				Tag created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Tag findWithAssociatedLists(Long id){
        Tag result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tag> update(List<Tag> ts, boolean createIfNotExist) {
        List<Tag> result = new ArrayList<>();
        if (ts != null) {
            for (Tag t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Tag loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Tag findByReferenceEntity(Tag t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Tag> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Tag>> getToBeSavedAndToBeDeleted(List<Tag> oldList, List<Tag> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Tag> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TagDao dao;


}
