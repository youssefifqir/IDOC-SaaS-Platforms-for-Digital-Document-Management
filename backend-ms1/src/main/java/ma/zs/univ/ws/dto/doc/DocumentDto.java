package  ma.zs.univ.ws.dto.doc;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.univ.ws.dto.referetiel.AcessShareDto;
import ma.zs.univ.ws.dto.user.GroupeDto;
import ma.zs.univ.ws.dto.purchase.DocumentPartageGroupeDto;
import ma.zs.univ.ws.dto.purchase.DocumentPartageUtilisateurDto;
import ma.zs.univ.ws.dto.user.EntiteAdministrativeDto;
import ma.zs.univ.ws.dto.user.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentDto  extends AuditBaseDto {
    private String reference  ;
    private String uploadDate ;
    private String dateLastUpdate ;
    private String content ;
    private Boolean folder ;
    private BigDecimal size ;
    private String description  ;
    private Boolean archive  ;
    private Boolean versionne  ;

    private DocumentTypeDto documentType ;
    private DocumentStateDto documentState ;
    private DocumentCategorieDto documentCategorie ;
    private UtilisateurDto proprietaire ;
    private EntiteAdministrativeDto entiteAdministrative ;
    private EntiteAdministrativeDto entiteAdministrativeProprietaire ;

    private List<DocumentFieldDto> documentFields ;
    private List<DocumentPartageGroupeDto> documentPartageGroupes ;
    private List<DocumentPartageUtilisateurDto> documentPartageUtilisateurs ;
    private List<DocumentTagDto> documentTags ;


    public DocumentDto(){
        super();
    }



    @Log
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getUploadDate(){
        return this.uploadDate;
    }
    public void setUploadDate(String uploadDate){
        this.uploadDate = uploadDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateLastUpdate(){
        return this.dateLastUpdate;
    }
    public void setDateLastUpdate(String dateLastUpdate){
        this.dateLastUpdate = dateLastUpdate;
    }

    @Log
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }

    @Log
    public Boolean getFolder(){
        return this.folder;
    }
    public void setFolder(Boolean folder){
        this.folder = folder;
    }
    @Log
    public BigDecimal getSize(){
        return this.size;
    }
    public void setSize(BigDecimal size){
        this.size = size;
    }
    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @Log
    public Boolean getArchive(){
        return this.archive;
    }
    public void setArchive(Boolean archive){
        this.archive = archive;
    }
    @Log
    public Boolean getVersionne(){
        return this.versionne;
    }
    public void setVersionne(Boolean versionne){
        this.versionne = versionne;
    }


    public DocumentTypeDto getDocumentType(){
        return this.documentType;
    }
    public void setDocumentType(DocumentTypeDto documentType){
        this.documentType = documentType;
    }
    public DocumentStateDto getDocumentState(){
        return this.documentState;
    }
    public void setDocumentState(DocumentStateDto documentState){
        this.documentState = documentState;
    }
    public DocumentCategorieDto getDocumentCategorie(){
        return this.documentCategorie;
    }

    public void setDocumentCategorie(DocumentCategorieDto documentCategorie){
        this.documentCategorie = documentCategorie;
    }
    public UtilisateurDto getProprietaire(){
        return this.proprietaire;
    }

    public void setProprietaire(UtilisateurDto proprietaire){
        this.proprietaire = proprietaire;
    }
    public EntiteAdministrativeDto getEntiteAdministrative(){
        return this.entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrativeDto entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }
    public EntiteAdministrativeDto getEntiteAdministrativeProprietaire(){
        return this.entiteAdministrativeProprietaire;
    }

    public void setEntiteAdministrativeProprietaire(EntiteAdministrativeDto entiteAdministrativeProprietaire){
        this.entiteAdministrativeProprietaire = entiteAdministrativeProprietaire;
    }



    public List<DocumentFieldDto> getDocumentFields(){
        return this.documentFields;
    }

    public void setDocumentFields(List<DocumentFieldDto> documentFields){
        this.documentFields = documentFields;
    }
    public List<DocumentPartageGroupeDto> getDocumentPartageGroupes(){
        return this.documentPartageGroupes;
    }

    public void setDocumentPartageGroupes(List<DocumentPartageGroupeDto> documentPartageGroupes){
        this.documentPartageGroupes = documentPartageGroupes;
    }
    public List<DocumentPartageUtilisateurDto> getDocumentPartageUtilisateurs(){
        return this.documentPartageUtilisateurs;
    }

    public void setDocumentPartageUtilisateurs(List<DocumentPartageUtilisateurDto> documentPartageUtilisateurs){
        this.documentPartageUtilisateurs = documentPartageUtilisateurs;
    }
    public List<DocumentTagDto> getDocumentTags(){
        return this.documentTags;
    }

    public void setDocumentTags(List<DocumentTagDto> documentTags){
        this.documentTags = documentTags;
    }



}
