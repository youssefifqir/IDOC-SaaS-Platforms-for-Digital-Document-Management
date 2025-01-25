package ma.zs.univ.zynerator.security.dao.criteria.core;


import ma.zs.univ.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ModelPermissionUserCriteria extends  BaseCriteria  {

    private Boolean value;
    private String subAttribute;
    private String subAttributeLike;

    private ActionPermissionCriteria actionPermission ;
    private List<ActionPermissionCriteria> actionPermissions ;
    private ModelPermissionCriteria modelPermission ;
    private List<ModelPermissionCriteria> modelPermissions ;
    private UserCriteria user ;
    private List<UserCriteria> utilisateurs ;


    public ModelPermissionUserCriteria(){}

    public Boolean getValue(){
        return this.value;
    }
    public void setValue(Boolean value){
        this.value = value;
    }
    public String getSubAttribute(){
        return this.subAttribute;
    }
    public void setSubAttribute(String subAttribute){
        this.subAttribute = subAttribute;
    }
    public String getSubAttributeLike(){
        return this.subAttributeLike;
    }
    public void setSubAttributeLike(String subAttributeLike){
        this.subAttributeLike = subAttributeLike;
    }


    public ActionPermissionCriteria getActionPermission(){
        return this.actionPermission;
    }

    public void setActionPermission(ActionPermissionCriteria actionPermission){
        this.actionPermission = actionPermission;
    }
    public List<ActionPermissionCriteria> getActionPermissions(){
        return this.actionPermissions;
    }

    public void setActionPermissions(List<ActionPermissionCriteria> actionPermissions){
        this.actionPermissions = actionPermissions;
    }
    public ModelPermissionCriteria getModelPermission(){
        return this.modelPermission;
    }

    public void setModelPermission(ModelPermissionCriteria modelPermission){
        this.modelPermission = modelPermission;
    }
    public List<ModelPermissionCriteria> getModelPermissions(){
        return this.modelPermissions;
    }

    public void setModelPermissions(List<ModelPermissionCriteria> modelPermissions){
        this.modelPermissions = modelPermissions;
    }
    public UserCriteria getUser(){
        return this.user;
    }

    public void setUser(UserCriteria user){
        this.user = user;
    }
    public List<UserCriteria> getUsers(){
        return this.utilisateurs;
    }

    public void setUsers(List<UserCriteria> utilisateurs){
        this.utilisateurs = utilisateurs;
    }
}
