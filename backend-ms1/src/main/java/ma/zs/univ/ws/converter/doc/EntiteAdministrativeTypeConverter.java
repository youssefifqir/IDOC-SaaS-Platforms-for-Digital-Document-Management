package  ma.zs.univ.ws.converter.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import ma.zs.univ.ws.dto.doc.EntiteAdministrativeTypeDto;

@Component
public class EntiteAdministrativeTypeConverter {


    public  EntiteAdministrativeTypeConverter() {
    }


    public EntiteAdministrativeType toItem(EntiteAdministrativeTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        EntiteAdministrativeType item = new EntiteAdministrativeType();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public EntiteAdministrativeTypeDto toDto(EntiteAdministrativeType item) {
        if (item == null) {
            return null;
        } else {
            EntiteAdministrativeTypeDto dto = new EntiteAdministrativeTypeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<EntiteAdministrativeType> toItem(List<EntiteAdministrativeTypeDto> dtos) {
        List<EntiteAdministrativeType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntiteAdministrativeTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntiteAdministrativeTypeDto> toDto(List<EntiteAdministrativeType> items) {
        List<EntiteAdministrativeTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntiteAdministrativeType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntiteAdministrativeTypeDto dto, EntiteAdministrativeType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EntiteAdministrativeType> copy(List<EntiteAdministrativeTypeDto> dtos) {
        List<EntiteAdministrativeType> result = new ArrayList<>();
        if (dtos != null) {
            for (EntiteAdministrativeTypeDto dto : dtos) {
                EntiteAdministrativeType instance = new EntiteAdministrativeType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
