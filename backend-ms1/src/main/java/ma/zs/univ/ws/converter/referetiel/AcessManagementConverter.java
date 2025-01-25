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
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import ma.zs.univ.ws.dto.referetiel.AcessManagementDto;

@Component
public class AcessManagementConverter {


    public  AcessManagementConverter() {
    }


    public AcessManagement toItem(AcessManagementDto dto) {
        if (dto == null) {
            return null;
        } else {
        AcessManagement item = new AcessManagement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public AcessManagementDto toDto(AcessManagement item) {
        if (item == null) {
            return null;
        } else {
            AcessManagementDto dto = new AcessManagementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<AcessManagement> toItem(List<AcessManagementDto> dtos) {
        List<AcessManagement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AcessManagementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AcessManagementDto> toDto(List<AcessManagement> items) {
        List<AcessManagementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AcessManagement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AcessManagementDto dto, AcessManagement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<AcessManagement> copy(List<AcessManagementDto> dtos) {
        List<AcessManagement> result = new ArrayList<>();
        if (dtos != null) {
            for (AcessManagementDto dto : dtos) {
                AcessManagement instance = new AcessManagement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
