package  ma.zs.univ.ws.converter.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.doc.FieldConverter;
import ma.zs.univ.ws.converter.doc.DocumentFieldStateConverter;
import ma.zs.univ.ws.converter.doc.DocumentConverter;

import ma.zs.univ.bean.core.doc.Document;


import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.doc.DocumentField;
import ma.zs.univ.ws.dto.doc.DocumentFieldDto;

@Component
public class DocumentFieldConverter {

    @Autowired
    private FieldConverter fieldConverter ;
    @Autowired
    private DocumentFieldStateConverter documentFieldStateConverter ;
    @Autowired
    private DocumentConverter documentConverter ;
    private boolean field;
    private boolean document;
    private boolean documentFieldState;

    public  DocumentFieldConverter() {
        initObject(true);
    }


    public DocumentField toItem(DocumentFieldDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentField item = new DocumentField();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getValue()))
                item.setValue(dto.getValue());
            if(this.field && dto.getField()!=null)
                item.setField(fieldConverter.toItem(dto.getField())) ;

            if(dto.getDocument() != null && dto.getDocument().getId() != null){
                item.setDocument(new Document());
                item.getDocument().setId(dto.getDocument().getId());
                item.getDocument().setReference(dto.getDocument().getReference());
            }

            if(this.documentFieldState && dto.getDocumentFieldState()!=null)
                item.setDocumentFieldState(documentFieldStateConverter.toItem(dto.getDocumentFieldState())) ;




        return item;
        }
    }


    public DocumentFieldDto toDto(DocumentField item) {
        if (item == null) {
            return null;
        } else {
            DocumentFieldDto dto = new DocumentFieldDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getValue()))
                dto.setValue(item.getValue());
            if(this.field && item.getField()!=null) {
                dto.setField(fieldConverter.toDto(item.getField())) ;

            }
            if(this.document && item.getDocument()!=null) {
                dto.setDocument(documentConverter.toDto(item.getDocument())) ;

            }
            if(this.documentFieldState && item.getDocumentFieldState()!=null) {
                dto.setDocumentFieldState(documentFieldStateConverter.toDto(item.getDocumentFieldState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.field = value;
        this.document = value;
        this.documentFieldState = value;
    }
	
    public List<DocumentField> toItem(List<DocumentFieldDto> dtos) {
        List<DocumentField> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentFieldDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentFieldDto> toDto(List<DocumentField> items) {
        List<DocumentFieldDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentField item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentFieldDto dto, DocumentField t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getField() != null)
        fieldConverter.copy(dto.getField(), t.getField());
        if (dto.getDocument() != null)
        documentConverter.copy(dto.getDocument(), t.getDocument());
        if (dto.getDocumentFieldState() != null)
        documentFieldStateConverter.copy(dto.getDocumentFieldState(), t.getDocumentFieldState());
    }

    public List<DocumentField> copy(List<DocumentFieldDto> dtos) {
        List<DocumentField> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentFieldDto dto : dtos) {
                DocumentField instance = new DocumentField();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public FieldConverter getFieldConverter(){
        return this.fieldConverter;
    }
    public void setFieldConverter(FieldConverter fieldConverter ){
        this.fieldConverter = fieldConverter;
    }
    public DocumentFieldStateConverter getDocumentFieldStateConverter(){
        return this.documentFieldStateConverter;
    }
    public void setDocumentFieldStateConverter(DocumentFieldStateConverter documentFieldStateConverter ){
        this.documentFieldStateConverter = documentFieldStateConverter;
    }
    public DocumentConverter getDocumentConverter(){
        return this.documentConverter;
    }
    public void setDocumentConverter(DocumentConverter documentConverter ){
        this.documentConverter = documentConverter;
    }
    public boolean  isField(){
        return this.field;
    }
    public void  setField(boolean field){
        this.field = field;
    }
    public boolean  isDocument(){
        return this.document;
    }
    public void  setDocument(boolean document){
        this.document = document;
    }
    public boolean  isDocumentFieldState(){
        return this.documentFieldState;
    }
    public void  setDocumentFieldState(boolean documentFieldState){
        this.documentFieldState = documentFieldState;
    }
}
