package  ma.zs.univ.ws.converter.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.doc.TagConverter;
import ma.zs.univ.ws.converter.doc.DocumentConverter;

import ma.zs.univ.bean.core.doc.Document;


import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.doc.DocumentTag;
import ma.zs.univ.ws.dto.doc.DocumentTagDto;

@Component
public class DocumentTagConverter {

    @Autowired
    private TagConverter tagConverter ;
    @Autowired
    private DocumentConverter documentConverter ;
    private boolean document;
    private boolean tag;

    public  DocumentTagConverter() {
        initObject(true);
    }


    public DocumentTag toItem(DocumentTagDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentTag item = new DocumentTag();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getDocument() != null && dto.getDocument().getId() != null){
                item.setDocument(new Document());
                item.getDocument().setId(dto.getDocument().getId());
                item.getDocument().setReference(dto.getDocument().getReference());
            }

            if(this.tag && dto.getTag()!=null)
                item.setTag(tagConverter.toItem(dto.getTag())) ;




        return item;
        }
    }


    public DocumentTagDto toDto(DocumentTag item) {
        if (item == null) {
            return null;
        } else {
            DocumentTagDto dto = new DocumentTagDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.document && item.getDocument()!=null) {
                dto.setDocument(documentConverter.toDto(item.getDocument())) ;

            }
            if(this.tag && item.getTag()!=null) {
                dto.setTag(tagConverter.toDto(item.getTag())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.document = value;
        this.tag = value;
    }
	
    public List<DocumentTag> toItem(List<DocumentTagDto> dtos) {
        List<DocumentTag> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentTagDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentTagDto> toDto(List<DocumentTag> items) {
        List<DocumentTagDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentTag item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentTagDto dto, DocumentTag t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDocument() != null)
        documentConverter.copy(dto.getDocument(), t.getDocument());
        if (dto.getTag() != null)
        tagConverter.copy(dto.getTag(), t.getTag());
    }

    public List<DocumentTag> copy(List<DocumentTagDto> dtos) {
        List<DocumentTag> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentTagDto dto : dtos) {
                DocumentTag instance = new DocumentTag();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TagConverter getTagConverter(){
        return this.tagConverter;
    }
    public void setTagConverter(TagConverter tagConverter ){
        this.tagConverter = tagConverter;
    }
    public DocumentConverter getDocumentConverter(){
        return this.documentConverter;
    }
    public void setDocumentConverter(DocumentConverter documentConverter ){
        this.documentConverter = documentConverter;
    }
    public boolean  isDocument(){
        return this.document;
    }
    public void  setDocument(boolean document){
        this.document = document;
    }
    public boolean  isTag(){
        return this.tag;
    }
    public void  setTag(boolean tag){
        this.tag = tag;
    }
}
