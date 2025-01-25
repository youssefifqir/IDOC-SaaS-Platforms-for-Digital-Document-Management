package  ma.zs.univ.ws.dto.purchase;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.univ.ws.dto.referetiel.AcessShareDto;
import ma.zs.univ.ws.dto.doc.DocumentDto;
import ma.zs.univ.ws.dto.user.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentPartageUtilisateurDto  extends AuditBaseDto {

    private String dateShare ;

    private DocumentDto document ;
    private UtilisateurDto utilisateur ;
    private AcessShareDto acessShare ;



    public DocumentPartageUtilisateurDto(){
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
    public UtilisateurDto getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur){
        this.utilisateur = utilisateur;
    }
    public AcessShareDto getAcessShare(){
        return this.acessShare;
    }

    public void setAcessShare(AcessShareDto acessShare){
        this.acessShare = acessShare;
    }






}
