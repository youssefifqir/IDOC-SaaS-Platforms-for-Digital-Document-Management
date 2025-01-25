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
import ma.zs.univ.bean.core.doc.Tag;
import ma.zs.univ.ws.dto.doc.TagDto;

@Component
public class TagConverter {


    public  TagConverter() {
    }


    public Tag toItem(TagDto dto) {
        if (dto == null) {
            return null;
        } else {
        Tag item = new Tag();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TagDto toDto(Tag item) {
        if (item == null) {
            return null;
        } else {
            TagDto dto = new TagDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Tag> toItem(List<TagDto> dtos) {
        List<Tag> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TagDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TagDto> toDto(List<Tag> items) {
        List<TagDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Tag item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TagDto dto, Tag t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Tag> copy(List<TagDto> dtos) {
        List<Tag> result = new ArrayList<>();
        if (dtos != null) {
            for (TagDto dto : dtos) {
                Tag instance = new Tag();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
