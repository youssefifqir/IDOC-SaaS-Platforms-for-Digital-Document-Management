package  ma.zs.univ.ws.converter.referetiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.ws.dto.referetiel.AcessShareDto;

@Component
public class AcessShareConverter {


    public  AcessShareConverter() {
    }


    public AcessShare toItem(AcessShareDto dto) {
        if (dto == null) {
            return null;
        } else {
        AcessShare item = new AcessShare();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public AcessShareDto toDto(AcessShare item) {
        if (item == null) {
            return null;
        } else {
            AcessShareDto dto = new AcessShareDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<AcessShare> toItem(List<AcessShareDto> dtos) {
        List<AcessShare> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AcessShareDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AcessShareDto> toDto(List<AcessShare> items) {
        List<AcessShareDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AcessShare item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AcessShareDto dto, AcessShare t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<AcessShare> copy(List<AcessShareDto> dtos) {
        List<AcessShare> result = new ArrayList<>();
        if (dtos != null) {
            for (AcessShareDto dto : dtos) {
                AcessShare instance = new AcessShare();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
