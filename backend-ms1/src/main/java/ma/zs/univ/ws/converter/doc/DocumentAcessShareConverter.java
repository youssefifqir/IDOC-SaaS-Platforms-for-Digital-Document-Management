package  ma.zs.univ.ws.converter.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.referetiel.AcessShareConverter;
import ma.zs.univ.ws.converter.doc.DocumentConverter;

import ma.zs.univ.bean.core.doc.Document;


import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.doc.DocumentAcessShare;
import ma.zs.univ.ws.dto.doc.DocumentAcessShareDto;

@Component
public class DocumentAcessShareConverter {

    @Autowired
    private AcessShareConverter acessShareConverter ;
    @Autowired
    private DocumentConverter documentConverter ;
    private boolean document;
    private boolean acessShare;

    public  DocumentAcessShareConverter() {
        initObject(true);
    }


    public DocumentAcessShare toItem(DocumentAcessShareDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentAcessShare item = new DocumentAcessShare();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getDocument() != null && dto.getDocument().getId() != null){
                item.setDocument(new Document());
                item.getDocument().setId(dto.getDocument().getId());
                item.getDocument().setReference(dto.getDocument().getReference());
            }

            if(this.acessShare && dto.getAcessShare()!=null)
                item.setAcessShare(acessShareConverter.toItem(dto.getAcessShare())) ;




        return item;
        }
    }


    public DocumentAcessShareDto toDto(DocumentAcessShare item) {
        if (item == null) {
            return null;
        } else {
            DocumentAcessShareDto dto = new DocumentAcessShareDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.document && item.getDocument()!=null) {
                dto.setDocument(documentConverter.toDto(item.getDocument())) ;

            }
            if(this.acessShare && item.getAcessShare()!=null) {
                dto.setAcessShare(acessShareConverter.toDto(item.getAcessShare())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.document = value;
        this.acessShare = value;
    }
	
    public List<DocumentAcessShare> toItem(List<DocumentAcessShareDto> dtos) {
        List<DocumentAcessShare> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentAcessShareDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentAcessShareDto> toDto(List<DocumentAcessShare> items) {
        List<DocumentAcessShareDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentAcessShare item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentAcessShareDto dto, DocumentAcessShare t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDocument() != null)
        documentConverter.copy(dto.getDocument(), t.getDocument());
        if (dto.getAcessShare() != null)
        acessShareConverter.copy(dto.getAcessShare(), t.getAcessShare());
    }

    public List<DocumentAcessShare> copy(List<DocumentAcessShareDto> dtos) {
        List<DocumentAcessShare> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentAcessShareDto dto : dtos) {
                DocumentAcessShare instance = new DocumentAcessShare();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AcessShareConverter getAcessShareConverter(){
        return this.acessShareConverter;
    }
    public void setAcessShareConverter(AcessShareConverter acessShareConverter ){
        this.acessShareConverter = acessShareConverter;
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
    public boolean  isAcessShare(){
        return this.acessShare;
    }
    public void  setAcessShare(boolean acessShare){
        this.acessShare = acessShare;
    }
}
