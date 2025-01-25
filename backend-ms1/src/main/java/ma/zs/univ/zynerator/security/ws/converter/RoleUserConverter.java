package ma.zs.univ.zynerator.security.ws.converter;

import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.security.bean.RoleUser;
import ma.zs.univ.zynerator.security.bean.User;
import ma.zs.univ.zynerator.security.ws.dto.RoleUserDto;
import ma.zs.univ.zynerator.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleUserConverter extends AbstractConverter<RoleUser, RoleUserDto> {

    @Autowired
    private RoleConverter roleConverter ;
    @Autowired
    private UserConverter utilisateurConverter ;
    private boolean role;
    private boolean user;

    public  RoleUserConverter(){//Role roleUser user,){
        super(RoleUser.class, RoleUserDto.class);
        //this.role =  role ;
        //this.user =  user ;
    }

    @Override
    public RoleUser toItem(RoleUserDto dto) {
        if (dto == null) {
            return null;
        } else {
        RoleUser item = new RoleUser();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.role && dto.getRole()!=null &&  dto.getRole().getId() != null)
                item.setRole(roleConverter.toItem(dto.getRole())) ;

            if(dto.getUser() != null && dto.getUser().getId() != null){
                item.setUser(new User());
                item.getUser().setId(dto.getUser().getId());
                item.getUser().setEmail(dto.getUser().getEmail());
            }




        return item;
        }
    }

    @Override
    public RoleUserDto toDto(RoleUser item) {
        if (item == null) {
            return null;
        } else {
            RoleUserDto dto = new RoleUserDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
        if(this.role && item.getRole()!=null) {
            dto.setRole(roleConverter.toDto(item.getRole())) ;
        }
        if(this.user && item.getUser()!=null) {
            dto.setUser(utilisateurConverter.toDto(item.getUser())) ;
        }


        return dto;
        }
    }


    public void initObject(boolean value) {
        this.role = value;
        this.user = value;
    }


    public RoleConverter getRoleConverter(){
        return this.roleConverter;
    }
    public void setRoleConverter(RoleConverter roleConverter ){
        this.roleConverter = roleConverter;
    }
    public UserConverter getUserConverter(){
        return this.utilisateurConverter;
    }
    public void setUserConverter(UserConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public boolean  isRole(){
        return this.role;
    }
    public void  setRole(boolean role){
        this.role = role;
    }
    public boolean  isUser(){
        return this.user;
    }
    public void  setUser(boolean user){
        this.user = user;
    }
}
