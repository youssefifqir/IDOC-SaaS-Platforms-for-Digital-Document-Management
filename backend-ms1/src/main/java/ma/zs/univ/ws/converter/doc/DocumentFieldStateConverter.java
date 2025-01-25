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
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import ma.zs.univ.ws.dto.doc.DocumentFieldStateDto;

@Component
public class DocumentFieldStateConverter {


    public  DocumentFieldStateConverter() {
    }


    public DocumentFieldState toItem(DocumentFieldStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentFieldState item = new DocumentFieldState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public DocumentFieldStateDto toDto(DocumentFieldState item) {
        if (item == null) {
            return null;
        } else {
            DocumentFieldStateDto dto = new DocumentFieldStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<DocumentFieldState> toItem(List<DocumentFieldStateDto> dtos) {
        List<DocumentFieldState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentFieldStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentFieldStateDto> toDto(List<DocumentFieldState> items) {
        List<DocumentFieldStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentFieldState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentFieldStateDto dto, DocumentFieldState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DocumentFieldState> copy(List<DocumentFieldStateDto> dtos) {
        List<DocumentFieldState> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentFieldStateDto dto : dtos) {
                DocumentFieldState instance = new DocumentFieldState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
