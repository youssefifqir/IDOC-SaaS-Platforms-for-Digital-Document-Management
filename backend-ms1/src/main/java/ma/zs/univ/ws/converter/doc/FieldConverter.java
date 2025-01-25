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
import ma.zs.univ.bean.core.doc.Field;
import ma.zs.univ.ws.dto.doc.FieldDto;

@Component
public class FieldConverter {


    public  FieldConverter() {
    }


    public Field toItem(FieldDto dto) {
        if (dto == null) {
            return null;
        } else {
        Field item = new Field();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public FieldDto toDto(Field item) {
        if (item == null) {
            return null;
        } else {
            FieldDto dto = new FieldDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Field> toItem(List<FieldDto> dtos) {
        List<Field> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FieldDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FieldDto> toDto(List<Field> items) {
        List<FieldDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Field item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FieldDto dto, Field t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Field> copy(List<FieldDto> dtos) {
        List<Field> result = new ArrayList<>();
        if (dtos != null) {
            for (FieldDto dto : dtos) {
                Field instance = new Field();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
