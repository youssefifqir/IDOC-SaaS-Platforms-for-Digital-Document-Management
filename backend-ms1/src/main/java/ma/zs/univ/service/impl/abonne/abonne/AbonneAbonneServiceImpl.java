package ma.zs.univ.service.impl.abonne.abonne;


import ma.zs.univ.bean.core.user.Utilisateur;
import ma.zs.univ.minioFolder.service.facade.admin.MinIOService;
import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.dao.criteria.core.abonne.AbonneCriteria;
import ma.zs.univ.dao.facade.core.abonne.AbonneDao;
import ma.zs.univ.dao.specification.core.abonne.AbonneSpecification;
import ma.zs.univ.service.facade.abonne.abonne.AbonneAbonneService;
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

import ma.zs.univ.service.facade.abonne.user.EntiteAdministrativeAbonneService ;
import ma.zs.univ.bean.core.user.EntiteAdministrative ;

import java.time.LocalDateTime;
import ma.zs.univ.zynerator.security.service.facade.UserService;
import ma.zs.univ.zynerator.security.service.facade.RoleService;
import ma.zs.univ.zynerator.security.service.facade.RoleUserService;
import ma.zs.univ.zynerator.security.bean.Role;
import ma.zs.univ.zynerator.security.bean.RoleUser;
import ma.zs.univ.zynerator.security.common.AuthoritiesConstants;
import ma.zs.univ.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class AbonneAbonneServiceImpl implements AbonneAbonneService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Abonne update(Abonne t) {
        Abonne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Abonne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Abonne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Abonne findOrSave(Abonne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Abonne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Abonne> importData(List<Abonne> items) {
        List<Abonne> list = new ArrayList<>();
        for (Abonne t : items) {
            Abonne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Abonne> findAll() {
        return dao.findAll();
    }

    public List<Abonne> findByCriteria(AbonneCriteria criteria) {
        List<Abonne> content = null;
        if (criteria != null) {
            AbonneSpecification mySpecification = constructSpecification(criteria);
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


    private AbonneSpecification constructSpecification(AbonneCriteria criteria) {
        AbonneSpecification mySpecification =  (AbonneSpecification) RefelexivityUtil.constructObjectUsingOneParam(AbonneSpecification.class, criteria);
        return mySpecification;
    }

    public List<Abonne> findPaginatedByCriteria(AbonneCriteria criteria, int page, int pageSize, String order, String sortField) {
        AbonneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AbonneCriteria criteria) {
        AbonneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Abonne> findByEntiteAdministrativeId(Long id){
        return dao.findByEntiteAdministrativeId(id);
    }
    public int deleteByEntiteAdministrativeId(Long id){
        return dao.deleteByEntiteAdministrativeId(id);
    }
    public long countByEntiteAdministrativeCode(String code){
        return dao.countByEntiteAdministrativeCode(code);
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
    public int delete(Abonne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Abonne> delete(List<Abonne> list) {
		List<Abonne> result = new ArrayList();
        if (list != null) {
            for (Abonne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Abonne> create(List<Abonne> ts) {
        List<Abonne> result = new ArrayList<>();
        if (ts != null) {
            for (Abonne t : ts) {
				Abonne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Abonne findWithAssociatedLists(Long id){
        Abonne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Abonne> update(List<Abonne> ts, boolean createIfNotExist) {
        List<Abonne> result = new ArrayList<>();
        if (ts != null) {
            for (Abonne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Abonne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Abonne findByReferenceEntity(Abonne t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Abonne t){
        if( t != null) {
            t.setEntiteAdministrative(entiteAdministrativeService.findOrSave(t.getEntiteAdministrative()));
        }
    }



    public List<Abonne> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Abonne>> getToBeSavedAndToBeDeleted(List<Abonne> oldList, List<Abonne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Abonne> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public Abonne create(Abonne t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.ABONNE);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Abonne mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }
        if (mySaved.getEntiteAdministrative() != null && mySaved.getEntiteAdministrative().getCode()!= null) {
            String path = minIOService.generatePath(mySaved.getEntiteAdministrative().getCode(),mySaved.getUsername());
            minIOService.createFolderInBucket(path, "yarbi");  // Replace "your-bucket-name" with your actual bucket name
        }

        return mySaved;
     }

    public Abonne findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }





    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;
    private @Autowired MinIOService minIOService;

    @Autowired
    private EntiteAdministrativeAbonneService entiteAdministrativeService ;

    private @Autowired AbonneDao dao;


}
