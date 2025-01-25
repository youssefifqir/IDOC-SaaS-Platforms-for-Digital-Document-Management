package  ma.zs.univ.ws.dto.purchase;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.univ.ws.dto.referetiel.AcessShareDto;
import ma.zs.univ.ws.dto.user.GroupeDto;
import ma.zs.univ.ws.dto.doc.DocumentDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentPartageGroupeDto  extends AuditBaseDto {

    private String dateShare ;

    private DocumentDto document ;
    private GroupeDto groupe ;
    private AcessShareDto acessShare ;



    public DocumentPartageGroupeDto(){
        super();
    }



    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateShare(){
        return this.dateShare;
    }
    public void setDateShare(String dateShare){
        this.dateShare = dateShare;
    }


    public DocumentDto getDocument(){
        return this.document;
    }

    public void setDocument(DocumentDto document){
        this.document = document;
    }
    public GroupeDto getGroupe(){
        return this.groupe;
    }

    public void setGroupe(GroupeDto groupe){
        this.groupe = groupe;
    }
    public AcessShareDto getAcessShare(){
        return this.acessShare;
    }

    public void setAcessShare(AcessShareDto acessShare){
        this.acessShare = acessShare;
    }






}
