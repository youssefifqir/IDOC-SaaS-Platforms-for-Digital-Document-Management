package ma.zs.univ.zynerator.security.service.impl;


import ma.zs.univ.zynerator.security.bean.ActionPermission;
import ma.zs.univ.zynerator.security.bean.ModelPermission;
import ma.zs.univ.zynerator.security.bean.ModelPermissionUser;
import ma.zs.univ.zynerator.security.dao.criteria.core.ModelPermissionUserCriteria;
import ma.zs.univ.zynerator.security.dao.facade.core.ModelPermissionUserDao;
import ma.zs.univ.zynerator.security.dao.specification.core.ModelPermissionUserSpecification;
import ma.zs.univ.zynerator.security.service.facade.ActionPermissionService;
import ma.zs.univ.zynerator.security.service.facade.ModelPermissionService;
import ma.zs.univ.zynerator.security.service.facade.ModelPermissionUserService;
import ma.zs.univ.zynerator.security.service.facade.UserService;
import ma.zs.univ.zynerator.service.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelPermissionUserServiceImpl extends AbstractServiceImpl<ModelPermissionUser, ModelPermissionUserCriteria, ModelPermissionUserDao> implements ModelPermissionUserService {


    @Override
    public List<ModelPermissionUser> findByUserUsername(String username) {
        return dao.findByUserUsername(username);
    }

    public List<ModelPermissionUser> findByActionPermissionId(Long id){
        return dao.findByActionPermissionId(id);
    }
    public int deleteByActionPermissionId(Long id){
        return dao.deleteByActionPermissionId(id);
    }
    public long countByActionPermissionReference(String reference){
        return dao.countByActionPermissionReference(reference);
    }
    public List<ModelPermissionUser> findByModelPermissionId(Long id){
        return dao.findByModelPermissionId(id);
    }
    public int deleteByModelPermissionId(Long id){
        return dao.deleteByModelPermissionId(id);
    }
    public long countByModelPermissionReference(String reference){
        return dao.countByModelPermissionReference(reference);
    }
    public List<ModelPermissionUser> findByUserId(Long id){
        return dao.findByUserId(id);
    }
    public int deleteByUserId(Long id){
        return dao.deleteByUserId(id);
    }
    public long countByUserEmail(String email){
        return dao.countByUserEmail(email);
    }

     public List<ModelPermissionUser> findModelPermissionUser() {
        List<ModelPermissionUser> modelPermissionUsers = new ArrayList<>();

        List<ModelPermission> models = modelPermissionService.findAllOptimized();
        List<ActionPermission> actions = actionPermissionService.findAllOptimized();

        for (ModelPermission model : models) {
            for (ActionPermission action : actions) {
                ModelPermissionUser permissionUser = new ModelPermissionUser();
                permissionUser.setModelPermission(model);
                permissionUser.setActionPermission(action);
                permissionUser.setValue(true);
                modelPermissionUsers.add(permissionUser);
            }
        }

        return modelPermissionUsers;
    }
	
    public List<ModelPermissionUser> initModelPermissionUser() {
        List<ModelPermissionUser> modelPermissionUsers = new ArrayList<>();

        for (ModelPermissionUser modelPermissionUser : findModelPermissionUser()) {
            boolean result = checkModelPermissionUser(modelPermissionUser);
            if (result) {
                modelPermissionUsers.add(modelPermissionUser);
            }
        }

        return modelPermissionUsers;
    }
	
	public List<ModelPermissionUser> initSecurityModelPermissionUser() {
		List<ModelPermissionUser> modelPermissionUsers = new ArrayList<>();
		
		for (ModelPermissionUser modelPermissionUser : findModelPermissionUser()) {
			boolean result = checkModelPermissionUser(modelPermissionUser);
			if (!result) {
				modelPermissionUsers.add(modelPermissionUser);
			}
		}
    
		return modelPermissionUsers;
	}


    public boolean checkModelPermissionUser(ModelPermissionUser modelPermissionUser) {
        return !(modelPermissionUser.getModelPermission().getReference().equals("User") ||
                modelPermissionUser.getModelPermission().getReference().equals("Role") ||
                modelPermissionUser.getModelPermission().getReference().equals("ModelPermissionUser") ||
                modelPermissionUser.getModelPermission().getReference().equals("ActionPermission") ||
                modelPermissionUser.getModelPermission().getReference().equals("ModelPermission"));
    }

    public Boolean findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference( String username ,  String modelReference,  String actionReference){
        ModelPermissionUser modelPermissionUser = dao.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(username, modelReference, actionReference);
        if (modelPermissionUser!=null) return modelPermissionUser.getValue();
        return false;
    }




    public void configure() {
        super.configure(ModelPermissionUser.class, ModelPermissionUserSpecification.class);
    }


    @Autowired
    private ModelPermissionService modelPermissionService ;
    @Autowired
    private UserService utilisateurService ;
    @Autowired
    private ActionPermissionService actionPermissionService ;

    public ModelPermissionUserServiceImpl(ModelPermissionUserDao dao) {
        super(dao);
    }

}
