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
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import ma.zs.univ.ws.dto.doc.DocumentCategorieDto;

@Component
public class DocumentCategorieConverter {


    public  DocumentCategorieConverter() {
    }


    public DocumentCategorie toItem(DocumentCategorieDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentCategorie item = new DocumentCategorie();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public DocumentCategorieDto toDto(DocumentCategorie item) {
        if (item == null) {
            return null;
        } else {
            DocumentCategorieDto dto = new DocumentCategorieDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<DocumentCategorie> toItem(List<DocumentCategorieDto> dtos) {
        List<DocumentCategorie> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentCategorieDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentCategorieDto> toDto(List<DocumentCategorie> items) {
        List<DocumentCategorieDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentCategorie item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentCategorieDto dto, DocumentCategorie t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DocumentCategorie> copy(List<DocumentCategorieDto> dtos) {
        List<DocumentCategorie> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentCategorieDto dto : dtos) {
                DocumentCategorie instance = new DocumentCategorie();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
