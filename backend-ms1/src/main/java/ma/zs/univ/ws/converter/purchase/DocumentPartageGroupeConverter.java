package  ma.zs.univ.ws.converter.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.referetiel.AcessShareConverter;
import ma.zs.univ.ws.converter.user.GroupeConverter;
import ma.zs.univ.ws.converter.doc.DocumentConverter;

import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.bean.core.user.Groupe;


import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe;
import ma.zs.univ.ws.dto.purchase.DocumentPartageGroupeDto;

@Component
public class DocumentPartageGroupeConverter {

    @Autowired
    private AcessShareConverter acessShareConverter ;
    @Autowired
    private GroupeConverter groupeConverter ;
    @Autowired
    private DocumentConverter documentConverter ;
    private boolean document;
    private boolean groupe;
    private boolean acessShare;

    public  DocumentPartageGroupeConverter() {
        initObject(true);
    }


    public DocumentPartageGroupe toItem(DocumentPartageGroupeDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentPartageGroupe item = new DocumentPartageGroupe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDateShare()))
                item.setDateShare(DateUtil.stringEnToDate(dto.getDateShare()));
            if(dto.getDocument() != null && dto.getDocument().getId() != null){
                item.setDocument(new Document());
                item.getDocument().setId(dto.getDocument().getId());
                item.getDocument().setReference(dto.getDocument().getReference());
            }

            if(dto.getGroupe() != null && dto.getGroupe().getId() != null){
                item.setGroupe(new Groupe());
                item.getGroupe().setId(dto.getGroupe().getId());
                item.getGroupe().setLibelle(dto.getGroupe().getLibelle());
            }

            if(this.acessShare && dto.getAcessShare()!=null)
                item.setAcessShare(acessShareConverter.toItem(dto.getAcessShare())) ;




        return item;
        }
    }


    public DocumentPartageGroupeDto toDto(DocumentPartageGroupe item) {
        if (item == null) {
            return null;
        } else {
            DocumentPartageGroupeDto dto = new DocumentPartageGroupeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDateShare()!=null)
                dto.setDateShare(DateUtil.dateTimeToString(item.getDateShare()));
            if(this.document && item.getDocument()!=null) {
                dto.setDocument(documentConverter.toDto(item.getDocument())) ;

            }
            if(this.groupe && item.getGroupe()!=null) {
                dto.setGroupe(groupeConverter.toDto(item.getGroupe())) ;

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
        this.groupe = value;
        this.acessShare = value;
    }
	
    public List<DocumentPartageGroupe> toItem(List<DocumentPartageGroupeDto> dtos) {
        List<DocumentPartageGroupe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentPartageGroupeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentPartageGroupeDto> toDto(List<DocumentPartageGroupe> items) {
        List<DocumentPartageGroupeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentPartageGroupe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentPartageGroupeDto dto, DocumentPartageGroupe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDocument() != null)
        documentConverter.copy(dto.getDocument(), t.getDocument());
        if (dto.getGroupe() != null)
        groupeConverter.copy(dto.getGroupe(), t.getGroupe());
        if (dto.getAcessShare() != null)
        acessShareConverter.copy(dto.getAcessShare(), t.getAcessShare());
    }

    public List<DocumentPartageGroupe> copy(List<DocumentPartageGroupeDto> dtos) {
        List<DocumentPartageGroupe> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentPartageGroupeDto dto : dtos) {
                DocumentPartageGroupe instance = new DocumentPartageGroupe();
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
    public GroupeConverter getGroupeConverter(){
        return this.groupeConverter;
    }
    public void setGroupeConverter(GroupeConverter groupeConverter ){
        this.groupeConverter = groupeConverter;
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
    public boolean  isGroupe(){
        return this.groupe;
    }
    public void  setGroupe(boolean groupe){
        this.groupe = groupe;
    }
    public boolean  isAcessShare(){
        return this.acessShare;
    }
    public void  setAcessShare(boolean acessShare){
        this.acessShare = acessShare;
    }
}
