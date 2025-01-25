package ma.zs.univ.zynerator.security.ws.converter;

import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.security.bean.Role;
import ma.zs.univ.zynerator.security.ws.dto.RoleDto;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.zynerator.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter<Role, RoleDto> {


    public  RoleConverter(){//){
        super(Role.class, RoleDto.class);
    }

    @Override
    public Role toItem(RoleDto dto) {
        if (dto == null) {
            return null;
        } else {
        Role item = new Role();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getAuthority()))
                item.setAuthority(dto.getAuthority());
            if(StringUtil.isNotEmpty(dto.getCreatedAt()))
                item.setCreatedAt(DateUtil.stringEnToDate(dto.getCreatedAt()));
            if(StringUtil.isNotEmpty(dto.getUpdatedAt()))
                item.setUpdatedAt(DateUtil.stringEnToDate(dto.getUpdatedAt()));



        return item;
        }
    }

    @Override
    public RoleDto toDto(Role item) {
        if (item == null) {
            return null;
        } else {
            RoleDto dto = new RoleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getAuthority()))
                dto.setAuthority(item.getAuthority());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(item.getCreatedAt()!=null)
                dto.setCreatedAt(DateUtil.dateTimeToString(item.getCreatedAt()));
            if(item.getUpdatedAt()!=null)
                dto.setUpdatedAt(DateUtil.dateTimeToString(item.getUpdatedAt()));


        return dto;
        }
    }


    public void initObject(boolean value) {
    }


}
