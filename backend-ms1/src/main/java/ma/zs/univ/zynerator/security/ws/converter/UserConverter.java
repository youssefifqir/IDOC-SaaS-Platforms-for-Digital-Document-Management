package ma.zs.univ.zynerator.security.ws.converter;

import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.security.bean.User;
import ma.zs.univ.zynerator.security.ws.dto.UserDto;
import ma.zs.univ.zynerator.util.ListUtil;
import ma.zs.univ.zynerator.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserDto> {

    @Autowired
    private RoleUserConverter roleUserConverter ;
    @Autowired
    private ModelPermissionConverter modelPermissionConverter ;
    @Autowired
    private RoleConverter roleConverter ;
    @Autowired
    private ModelPermissionUserConverter modelPermissionUserConverter ;
    @Autowired
    private ActionPermissionConverter actionPermissionConverter ;
    private boolean modelPermissionUsers;
    private boolean roleUsers;

    public  UserConverter(){//RoleUser roleUserModelPermission modelPermissionRole roleModelPermissionUser modelPermissionUserActionPermission actionPermission,){
        super(User.class, UserDto.class);
        init(true);
        //this.roleUser =  roleUser ;
        //this.modelPermission =  modelPermission ;
        //this.role =  role ;
        //this.modelPermissionUser =  modelPermissionUser ;
        //this.actionPermission =  actionPermission ;
    }

    @Override
    public User toItem(UserDto dto) {
        if (dto == null) {
            return null;
        } else {
        User item = new User();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getCredentialsNonExpired() != null)
                item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            if(dto.getEnabled() != null)
                item.setEnabled(dto.getEnabled());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(dto.getAccountNonExpired() != null)
                item.setAccountNonExpired(dto.getAccountNonExpired());
            if(dto.getAccountNonLocked() != null)
                item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(dto.getPasswordChanged() != null)
                item.setPasswordChanged(dto.getPasswordChanged());

            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());

            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());

            if(StringUtil.isNotEmpty(dto.getPhone()))
                item.setPhone(dto.getPhone());


            if(this.modelPermissionUsers && ListUtil.isNotEmpty(dto.getModelPermissionUsers()))
                item.setModelPermissionUsers(modelPermissionUserConverter.toItem(dto.getModelPermissionUsers()));
            if(this.roleUsers && ListUtil.isNotEmpty(dto.getRoleUsers()))
                item.setRoleUsers(roleUserConverter.toItem(dto.getRoleUsers()));

        return item;
        }
    }

    @Override
    public UserDto toDto(User item) {
        if (item == null) {
            return null;
        } else {
            UserDto dto = new UserDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.isCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.isCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.isEnabled()))
                dto.setEnabled(item.isEnabled());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.isAccountNonExpired()))
                dto.setAccountNonExpired(item.isAccountNonExpired());
            if(StringUtil.isNotEmpty(item.isAccountNonLocked()))
                dto.setAccountNonLocked(item.isAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPassword()))
                dto.setPassword(item.getPassword());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
        if(this.modelPermissionUsers && ListUtil.isNotEmpty(item.getModelPermissionUsers())){
            modelPermissionUserConverter.init(true);
            modelPermissionUserConverter.setUser(false);
            dto.setModelPermissionUsers(modelPermissionUserConverter.toDto(item.getModelPermissionUsers()));
            modelPermissionUserConverter.setUser(true);

        }
        if(this.roleUsers && ListUtil.isNotEmpty(item.getRoleUsers())){
            roleUserConverter.init(true);
            roleUserConverter.setUser(false);
            dto.setRoleUsers(roleUserConverter.toDto(item.getRoleUsers()));
            roleUserConverter.setUser(true);
        }
        return dto;
        }
    }

    public void initList(boolean value) {
        this.modelPermissionUsers = value;
        this.roleUsers = value;
    }

    public void initObject(boolean value) {
    }


    public RoleUserConverter getRoleUserConverter(){
        return this.roleUserConverter;
    }
    public void setRoleUserConverter(RoleUserConverter roleUserConverter ){
        this.roleUserConverter = roleUserConverter;
    }
    public ModelPermissionConverter getModelPermissionConverter(){
        return this.modelPermissionConverter;
    }
    public void setModelPermissionConverter(ModelPermissionConverter modelPermissionConverter ){
        this.modelPermissionConverter = modelPermissionConverter;
    }
    public RoleConverter getRoleConverter(){
        return this.roleConverter;
    }
    public void setRoleConverter(RoleConverter roleConverter ){
        this.roleConverter = roleConverter;
    }
    public ModelPermissionUserConverter getModelPermissionUserConverter(){
        return this.modelPermissionUserConverter;
    }
    public void setModelPermissionUserConverter(ModelPermissionUserConverter modelPermissionUserConverter ){
        this.modelPermissionUserConverter = modelPermissionUserConverter;
    }
    public ActionPermissionConverter getActionPermissionConverter(){
        return this.actionPermissionConverter;
    }
    public void setActionPermissionConverter(ActionPermissionConverter actionPermissionConverter ){
        this.actionPermissionConverter = actionPermissionConverter;
    }
    public boolean  isModelPermissionUsers(){
        return this.modelPermissionUsers ;
    }
    public void  setModelPermissionUsers(boolean modelPermissionUsers ){
        this.modelPermissionUsers  = modelPermissionUsers ;
    }
    public boolean  isRoleUsers(){
        return this.roleUsers ;
    }
    public void  setRoleUsers(boolean roleUsers ){
        this.roleUsers  = roleUsers ;
    }
}
