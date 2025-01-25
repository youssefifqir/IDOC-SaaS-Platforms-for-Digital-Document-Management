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
import ma.zs.univ.bean.core.doc.DocumentState;
import ma.zs.univ.ws.dto.doc.DocumentStateDto;

@Component
public class DocumentStateConverter {


    public  DocumentStateConverter() {
    }


    public DocumentState toItem(DocumentStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentState item = new DocumentState();
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


    public DocumentStateDto toDto(DocumentState item) {
        if (item == null) {
            return null;
        } else {
            DocumentStateDto dto = new DocumentStateDto();
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


	
    public List<DocumentState> toItem(List<DocumentStateDto> dtos) {
        List<DocumentState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentStateDto> toDto(List<DocumentState> items) {
        List<DocumentStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentStateDto dto, DocumentState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DocumentState> copy(List<DocumentStateDto> dtos) {
        List<DocumentState> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentStateDto dto : dtos) {
                DocumentState instance = new DocumentState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
