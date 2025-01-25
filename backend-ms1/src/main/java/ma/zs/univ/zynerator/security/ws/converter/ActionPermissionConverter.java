package ma.zs.univ.zynerator.security.ws.converter;

import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.security.bean.ActionPermission;
import ma.zs.univ.zynerator.security.ws.dto.ActionPermissionDto;
import ma.zs.univ.zynerator.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class ActionPermissionConverter extends AbstractConverter<ActionPermission, ActionPermissionDto> {


    public  ActionPermissionConverter(){//){
        super(ActionPermission.class, ActionPermissionDto.class);
    }

    @Override
    public ActionPermission toItem(ActionPermissionDto dto) {
        if (dto == null) {
            return null;
        } else {
        ActionPermission item = new ActionPermission();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }

    @Override
    public ActionPermissionDto toDto(ActionPermission item) {
        if (item == null) {
            return null;
        } else {
            ActionPermissionDto dto = new ActionPermissionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


    public void initObject(boolean value) {
    }


}
