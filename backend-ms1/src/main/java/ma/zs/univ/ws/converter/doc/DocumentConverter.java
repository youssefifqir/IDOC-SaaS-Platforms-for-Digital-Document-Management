package  ma.zs.univ.ws.converter.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.univ.zynerator.util.ListUtil;

import ma.zs.univ.ws.converter.doc.DocumentStateConverter;
import ma.zs.univ.ws.converter.doc.DocumentTagConverter;
import ma.zs.univ.ws.converter.referetiel.AcessShareConverter;
import ma.zs.univ.ws.converter.doc.DocumentFieldConverter;
import ma.zs.univ.ws.converter.user.GroupeConverter;
import ma.zs.univ.ws.converter.doc.DocumentTypeConverter;
import ma.zs.univ.ws.converter.doc.DocumentFieldStateConverter;
import ma.zs.univ.ws.converter.doc.FieldConverter;
import ma.zs.univ.ws.converter.purchase.DocumentPartageGroupeConverter;
import ma.zs.univ.ws.converter.doc.DocumentCategorieConverter;
import ma.zs.univ.ws.converter.purchase.DocumentPartageUtilisateurConverter;
import ma.zs.univ.ws.converter.user.EntiteAdministrativeConverter;
import ma.zs.univ.ws.converter.doc.TagConverter;
import ma.zs.univ.ws.converter.user.UtilisateurConverter;



import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.ws.dto.doc.DocumentDto;

@Component
public class DocumentConverter {

    @Autowired
    private DocumentStateConverter documentStateConverter ;
    @Autowired
    private DocumentTagConverter documentTagConverter ;
    @Autowired
    private AcessShareConverter acessShareConverter ;
    @Autowired
    private DocumentFieldConverter documentFieldConverter ;
    @Autowired
    private GroupeConverter groupeConverter ;
    @Autowired
    private DocumentTypeConverter documentTypeConverter ;
    @Autowired
    private DocumentFieldStateConverter documentFieldStateConverter ;
    @Autowired
    private FieldConverter fieldConverter ;
    @Autowired
    private DocumentPartageGroupeConverter documentPartageGroupeConverter ;
    @Autowired
    private DocumentCategorieConverter documentCategorieConverter ;
    @Autowired
    private DocumentPartageUtilisateurConverter documentPartageUtilisateurConverter ;
    @Autowired
    private EntiteAdministrativeConverter entiteAdministrativeConverter ;
    @Autowired
    private TagConverter tagConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    private boolean documentType;
    private boolean documentState;
    private boolean documentCategorie;
    private boolean proprietaire;
    private boolean entiteAdministrative;
    private boolean entiteAdministrativeProprietaire;
    private boolean documentFields;
    private boolean documentPartageGroupes;
    private boolean documentPartageUtilisateurs;
    private boolean documentTags;

    public  DocumentConverter() {
        init(true);
    }


    public Document toItem(DocumentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Document item = new Document();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getUploadDate()))
                item.setUploadDate(DateUtil.stringEnToDate(dto.getUploadDate()));
            if(StringUtil.isNotEmpty(dto.getDateLastUpdate()))
                item.setDateLastUpdate(DateUtil.stringEnToDate(dto.getDateLastUpdate()));
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(dto.getFolder() != null)
                item.setFolder(dto.getFolder());
            if(StringUtil.isNotEmpty(dto.getSize()))
                item.setSize(dto.getSize());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getArchive() != null)
                item.setArchive(dto.getArchive());
            if(dto.getVersionne() != null)
                item.setVersionne(dto.getVersionne());
            if(this.documentType && dto.getDocumentType()!=null)
                item.setDocumentType(documentTypeConverter.toItem(dto.getDocumentType())) ;

            if(this.documentState && dto.getDocumentState()!=null)
                item.setDocumentState(documentStateConverter.toItem(dto.getDocumentState())) ;

            if(this.documentCategorie && dto.getDocumentCategorie()!=null)
                item.setDocumentCategorie(documentCategorieConverter.toItem(dto.getDocumentCategorie())) ;

            if(this.proprietaire && dto.getProprietaire()!=null)
                item.setProprietaire(utilisateurConverter.toItem(dto.getProprietaire())) ;

            if(this.entiteAdministrative && dto.getEntiteAdministrative()!=null)
                item.setEntiteAdministrative(entiteAdministrativeConverter.toItem(dto.getEntiteAdministrative())) ;

            if(this.entiteAdministrativeProprietaire && dto.getEntiteAdministrativeProprietaire()!=null)
                item.setEntiteAdministrativeProprietaire(entiteAdministrativeConverter.toItem(dto.getEntiteAdministrativeProprietaire())) ;


            if(this.documentFields && ListUtil.isNotEmpty(dto.getDocumentFields()))
                item.setDocumentFields(documentFieldConverter.toItem(dto.getDocumentFields()));
            if(this.documentPartageGroupes && ListUtil.isNotEmpty(dto.getDocumentPartageGroupes()))
                item.setDocumentPartageGroupes(documentPartageGroupeConverter.toItem(dto.getDocumentPartageGroupes()));
            if(this.documentPartageUtilisateurs && ListUtil.isNotEmpty(dto.getDocumentPartageUtilisateurs()))
                item.setDocumentPartageUtilisateurs(documentPartageUtilisateurConverter.toItem(dto.getDocumentPartageUtilisateurs()));
            if(this.documentTags && ListUtil.isNotEmpty(dto.getDocumentTags()))
                item.setDocumentTags(documentTagConverter.toItem(dto.getDocumentTags()));


        return item;
        }
    }


    public DocumentDto toDto(Document item) {
        if (item == null) {
            return null;
        } else {
            DocumentDto dto = new DocumentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(item.getUploadDate()!=null)
                dto.setUploadDate(DateUtil.dateTimeToString(item.getUploadDate()));
            if(item.getDateLastUpdate()!=null)
                dto.setDateLastUpdate(DateUtil.dateTimeToString(item.getDateLastUpdate()));
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
                dto.setFolder(item.getFolder());
            if(StringUtil.isNotEmpty(item.getSize()))
                dto.setSize(item.getSize());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
                dto.setArchive(item.getArchive());
                dto.setVersionne(item.getVersionne());
            if(this.documentType && item.getDocumentType()!=null) {
                dto.setDocumentType(documentTypeConverter.toDto(item.getDocumentType())) ;

            }
            if(this.documentState && item.getDocumentState()!=null) {
                dto.setDocumentState(documentStateConverter.toDto(item.getDocumentState())) ;

            }
            if(this.documentCategorie && item.getDocumentCategorie()!=null) {
                dto.setDocumentCategorie(documentCategorieConverter.toDto(item.getDocumentCategorie())) ;

            }
            if(this.proprietaire && item.getProprietaire()!=null) {
                dto.setProprietaire(utilisateurConverter.toDto(item.getProprietaire())) ;

            }
            if(this.entiteAdministrative && item.getEntiteAdministrative()!=null) {
                dto.setEntiteAdministrative(entiteAdministrativeConverter.toDto(item.getEntiteAdministrative())) ;

            }
            if(this.entiteAdministrativeProprietaire && item.getEntiteAdministrativeProprietaire()!=null) {
                dto.setEntiteAdministrativeProprietaire(entiteAdministrativeConverter.toDto(item.getEntiteAdministrativeProprietaire())) ;

            }
        if(this.documentFields && ListUtil.isNotEmpty(item.getDocumentFields())){
            documentFieldConverter.init(true);
            documentFieldConverter.setDocument(false);
            dto.setDocumentFields(documentFieldConverter.toDto(item.getDocumentFields()));
            documentFieldConverter.setDocument(true);

        }
        if(this.documentPartageGroupes && ListUtil.isNotEmpty(item.getDocumentPartageGroupes())){
            documentPartageGroupeConverter.init(true);
            documentPartageGroupeConverter.setDocument(false);
            dto.setDocumentPartageGroupes(documentPartageGroupeConverter.toDto(item.getDocumentPartageGroupes()));
            documentPartageGroupeConverter.setDocument(true);

        }
        if(this.documentPartageUtilisateurs && ListUtil.isNotEmpty(item.getDocumentPartageUtilisateurs())){
            documentPartageUtilisateurConverter.init(true);
            documentPartageUtilisateurConverter.setDocument(false);
            dto.setDocumentPartageUtilisateurs(documentPartageUtilisateurConverter.toDto(item.getDocumentPartageUtilisateurs()));
            documentPartageUtilisateurConverter.setDocument(true);

        }
        if(this.documentTags && ListUtil.isNotEmpty(item.getDocumentTags())){
            documentTagConverter.init(true);
            documentTagConverter.setDocument(false);
            dto.setDocumentTags(documentTagConverter.toDto(item.getDocumentTags()));
            documentTagConverter.setDocument(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.documentFields = value;
        this.documentPartageGroupes = value;
        this.documentPartageUtilisateurs = value;
        this.documentTags = value;
    }
    public void initObject(boolean value) {
        this.documentType = value;
        this.documentState = value;
        this.documentCategorie = value;
        this.proprietaire = value;
        this.entiteAdministrative = value;
        this.entiteAdministrativeProprietaire = value;
    }
	
    public List<Document> toItem(List<DocumentDto> dtos) {
        List<Document> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DocumentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DocumentDto> toDto(List<Document> items) {
        List<DocumentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Document item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DocumentDto dto, Document t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDocumentType() != null)
        documentTypeConverter.copy(dto.getDocumentType(), t.getDocumentType());
        if (dto.getDocumentState() != null)
        documentStateConverter.copy(dto.getDocumentState(), t.getDocumentState());
        if (dto.getDocumentCategorie() != null)
        documentCategorieConverter.copy(dto.getDocumentCategorie(), t.getDocumentCategorie());
        if (dto.getDocumentFields() != null)
            t.setDocumentFields(documentFieldConverter.copy(dto.getDocumentFields()));
        if (dto.getProprietaire() != null)
        utilisateurConverter.copy(dto.getProprietaire(), t.getProprietaire());
        if (dto.getEntiteAdministrative() != null)
        entiteAdministrativeConverter.copy(dto.getEntiteAdministrative(), t.getEntiteAdministrative());
        if (dto.getEntiteAdministrativeProprietaire() != null)
        entiteAdministrativeConverter.copy(dto.getEntiteAdministrativeProprietaire(), t.getEntiteAdministrativeProprietaire());
        if (dto.getDocumentPartageGroupes() != null)
            t.setDocumentPartageGroupes(documentPartageGroupeConverter.copy(dto.getDocumentPartageGroupes()));
        if (dto.getDocumentPartageUtilisateurs() != null)
            t.setDocumentPartageUtilisateurs(documentPartageUtilisateurConverter.copy(dto.getDocumentPartageUtilisateurs()));
        if (dto.getDocumentTags() != null)
            t.setDocumentTags(documentTagConverter.copy(dto.getDocumentTags()));
    }

    public List<Document> copy(List<DocumentDto> dtos) {
        List<Document> result = new ArrayList<>();
        if (dtos != null) {
            for (DocumentDto dto : dtos) {
                Document instance = new Document();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DocumentStateConverter getDocumentStateConverter(){
        return this.documentStateConverter;
    }
    public void setDocumentStateConverter(DocumentStateConverter documentStateConverter ){
        this.documentStateConverter = documentStateConverter;
    }
    public DocumentTagConverter getDocumentTagConverter(){
        return this.documentTagConverter;
    }
    public void setDocumentTagConverter(DocumentTagConverter documentTagConverter ){
        this.documentTagConverter = documentTagConverter;
    }
    public AcessShareConverter getAcessShareConverter(){
        return this.acessShareConverter;
    }
    public void setAcessShareConverter(AcessShareConverter acessShareConverter ){
        this.acessShareConverter = acessShareConverter;
    }
    public DocumentFieldConverter getDocumentFieldConverter(){
        return this.documentFieldConverter;
    }
    public void setDocumentFieldConverter(DocumentFieldConverter documentFieldConverter ){
        this.documentFieldConverter = documentFieldConverter;
    }
    public GroupeConverter getGroupeConverter(){
        return this.groupeConverter;
    }
    public void setGroupeConverter(GroupeConverter groupeConverter ){
        this.groupeConverter = groupeConverter;
    }
    public DocumentTypeConverter getDocumentTypeConverter(){
        return this.documentTypeConverter;
    }
    public void setDocumentTypeConverter(DocumentTypeConverter documentTypeConverter ){
        this.documentTypeConverter = documentTypeConverter;
    }
    public DocumentFieldStateConverter getDocumentFieldStateConverter(){
        return this.documentFieldStateConverter;
    }
    public void setDocumentFieldStateConverter(DocumentFieldStateConverter documentFieldStateConverter ){
        this.documentFieldStateConverter = documentFieldStateConverter;
    }
    public FieldConverter getFieldConverter(){
        return this.fieldConverter;
    }
    public void setFieldConverter(FieldConverter fieldConverter ){
        this.fieldConverter = fieldConverter;
    }
    public DocumentPartageGroupeConverter getDocumentPartageGroupeConverter(){
        return this.documentPartageGroupeConverter;
    }
    public void setDocumentPartageGroupeConverter(DocumentPartageGroupeConverter documentPartageGroupeConverter ){
        this.documentPartageGroupeConverter = documentPartageGroupeConverter;
    }
    public DocumentCategorieConverter getDocumentCategorieConverter(){
        return this.documentCategorieConverter;
    }
    public void setDocumentCategorieConverter(DocumentCategorieConverter documentCategorieConverter ){
        this.documentCategorieConverter = documentCategorieConverter;
    }
    public DocumentPartageUtilisateurConverter getDocumentPartageUtilisateurConverter(){
        return this.documentPartageUtilisateurConverter;
    }
    public void setDocumentPartageUtilisateurConverter(DocumentPartageUtilisateurConverter documentPartageUtilisateurConverter ){
        this.documentPartageUtilisateurConverter = documentPartageUtilisateurConverter;
    }
    public EntiteAdministrativeConverter getEntiteAdministrativeConverter(){
        return this.entiteAdministrativeConverter;
    }
    public void setEntiteAdministrativeConverter(EntiteAdministrativeConverter entiteAdministrativeConverter ){
        this.entiteAdministrativeConverter = entiteAdministrativeConverter;
    }
    public TagConverter getTagConverter(){
        return this.tagConverter;
    }
    public void setTagConverter(TagConverter tagConverter ){
        this.tagConverter = tagConverter;
    }
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public boolean  isDocumentType(){
        return this.documentType;
    }
    public void  setDocumentType(boolean documentType){
        this.documentType = documentType;
    }
    public boolean  isDocumentState(){
        return this.documentState;
    }
    public void  setDocumentState(boolean documentState){
        this.documentState = documentState;
    }
    public boolean  isDocumentCategorie(){
        return this.documentCategorie;
    }
    public void  setDocumentCategorie(boolean documentCategorie){
        this.documentCategorie = documentCategorie;
    }
    public boolean  isProprietaire(){
        return this.proprietaire;
    }
    public void  setProprietaire(boolean proprietaire){
        this.proprietaire = proprietaire;
    }
    public boolean  isEntiteAdministrative(){
        return this.entiteAdministrative;
    }
    public void  setEntiteAdministrative(boolean entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }
    public boolean  isEntiteAdministrativeProprietaire(){
        return this.entiteAdministrativeProprietaire;
    }
    public void  setEntiteAdministrativeProprietaire(boolean entiteAdministrativeProprietaire){
        this.entiteAdministrativeProprietaire = entiteAdministrativeProprietaire;
    }
    public boolean  isDocumentFields(){
        return this.documentFields ;
    }
    public void  setDocumentFields(boolean documentFields ){
        this.documentFields  = documentFields ;
    }
    public boolean  isDocumentPartageGroupes(){
        return this.documentPartageGroupes ;
    }
    public void  setDocumentPartageGroupes(boolean documentPartageGroupes ){
        this.documentPartageGroupes  = documentPartageGroupes ;
    }
    public boolean  isDocumentPartageUtilisateurs(){
        return this.documentPartageUtilisateurs ;
    }
    public void  setDocumentPartageUtilisateurs(boolean documentPartageUtilisateurs ){
        this.documentPartageUtilisateurs  = documentPartageUtilisateurs ;
    }
    public boolean  isDocumentTags(){
        return this.documentTags ;
    }
    public void  setDocumentTags(boolean documentTags ){
        this.documentTags  = documentTags ;
    }
}
