package  ma.zs.univ.ws.converter.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.referetiel.AcessShareConverter;
import ma.zs.univ.ws.converter.doc.DocumentConverter;
import ma.zs.univ.ws.converter.user.UtilisateurConverter;

import ma.zs.univ.bean.core.doc.Document;


import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur;
import ma.zs.univ.ws.dto.purchase.DocumentPartageUtilisateurDto;

@Component
public class DocumentPartageUtilisateurConverter {

    @Autowired
    private AcessShareConverter acessShareConverter ;
    @Autowired
    private DocumentConverter documentConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    private boolean document;
    private boolean utilisateur;
    private boolean acessShare;

    public  DocumentPartageUtilisateurConverter() {
        initObject(true);
    }


    public DocumentPartageUtilisateur toItem(DocumentPartageUtilisateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        DocumentPartageUtilisateur item = new DocumentPartageUtilisateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDateShare()))
                item.setDateShare(DateUtil.stringEnToDate(dto.getDateShare()));
            if(dto.getDocument() != null && dto.getDocument().getId() != null){
                item.setDocument(new Document());
                item.getDocument().setId(dto.getDocument().getId());
                item.getDocument().setReference(dto.getDocument().getReference());
            }

            if(this.utilisateur && dto.getUtilisateur()!=null)
                item.setUtilisateur(utilisateurConverter.toItem(dto.getUtilisateur())) ;

            if(this.acessShare && dto.getAcessShare()!=null)
                item.setAcessShare(acessShareConverter.toItem(dto.getAcessShare())) ;




        return item;
        }
    }


    public DocumentPartageUtilisateurDto toDto(DocumentPartageUtilisateur item) {
        if (item == null) {
            return null;
        } else {
            DocumentPartageUtilisateurDto dto = new DocumentPartageUtilisateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDateShare()!=null)
                dto.setDateShare(DateUtil.dateTimeToString(item.getDateShare()));
            if(this.document && item.getDocument()!=null) {
                dto.setDocument(documentConverter.toDto(item.getDocument())) ;

            }
            if(this.utilisateur && item.getUtilisateur()!=null) {
                dto.setUtilisateur(utilisateurConverter.toDto(item.getUtilisateur())) ;

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
        this.utilisateur = value;
        this.acessShare = value;
    }
	
    public List<DocumentPartageUtilisateur> toItem(List<DocumentPartageUtilisateurDto> dtos) {
        List<DocumentPartageUtilisateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentPartageUtilisateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentPartageUtilisateurDto> toDto(List<DocumentPartageUtilisateur> items) {
        List<DocumentPartageUtilisateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DocumentPartageUtilisateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentPartageUtilisateurDto dto, DocumentPartageUtilisateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDocument() != null)
        documentConverter.copy(dto.getDocument(), t.getDocument());
        if (dto.getUtilisateur() != null)
        utilisateurConverter.copy(dto.getUtilisateur(), t.getUtilisateur());
        if (dto.getAcessShare() != null)
        acessShareConverter.copy(dto.getAcessShare(), t.getAcessShare());
    }

    public List<DocumentPartageUtilisateur> copy(List<DocumentPartageUtilisateurDto> dtos) {
        List<DocumentPartageUtilisateur> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentPartageUtilisateurDto dto : dtos) {
                DocumentPartageUtilisateur instance = new DocumentPartageUtilisateur();
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
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public boolean  isDocument(){
        return this.document;
    }
    public void  setDocument(boolean document){
        this.document = document;
    }
    public boolean  isUtilisateur(){
        return this.utilisateur;
    }
    public void  setUtilisateur(boolean utilisateur){
        this.utilisateur = utilisateur;
    }
    public boolean  isAcessShare(){
        return this.acessShare;
    }
    public void  setAcessShare(boolean acessShare){
        this.acessShare = acessShare;
    }
}
