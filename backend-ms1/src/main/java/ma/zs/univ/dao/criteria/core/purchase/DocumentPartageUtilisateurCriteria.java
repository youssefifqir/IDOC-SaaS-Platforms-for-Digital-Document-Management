package  ma.zs.univ.dao.criteria.core.purchase;


import ma.zs.univ.dao.criteria.core.referetiel.AcessShareCriteria;
import ma.zs.univ.dao.criteria.core.doc.DocumentCriteria;
import ma.zs.univ.dao.criteria.core.user.UtilisateurCriteria;

import ma.zs.univ.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DocumentPartageUtilisateurCriteria extends  BaseCriteria  {

    private LocalDateTime dateShare;
    private LocalDateTime dateShareFrom;
    private LocalDateTime dateShareTo;

    private DocumentCriteria document ;
    private List<DocumentCriteria> documents ;
    private UtilisateurCriteria utilisateur ;
    private List<UtilisateurCriteria> utilisateurs ;
    private AcessShareCriteria acessShare ;
    private List<AcessShareCriteria> acessShares ;


    public DocumentPartageUtilisateurCriteria(){}

    public LocalDateTime getDateShare(){
        return this.dateShare;
    }
    public void setDateShare(LocalDateTime dateShare){
        this.dateShare = dateShare;
    }
    public LocalDateTime getDateShareFrom(){
        return this.dateShareFrom;
    }
    public void setDateShareFrom(LocalDateTime dateShareFrom){
        this.dateShareFrom = dateShareFrom;
    }
    public LocalDateTime getDateShareTo(){
        return this.dateShareTo;
    }
    public void setDateShareTo(LocalDateTime dateShareTo){
        this.dateShareTo = dateShareTo;
    }

    public DocumentCriteria getDocument(){
        return this.document;
    }

    public void setDocument(DocumentCriteria document){
        this.document = document;
    }
    public List<DocumentCriteria> getDocuments(){
        return this.documents;
    }

    public void setDocuments(List<DocumentCriteria> documents){
        this.documents = documents;
    }
    public UtilisateurCriteria getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurCriteria utilisateur){
        this.utilisateur = utilisateur;
    }
    public List<UtilisateurCriteria> getUtilisateurs(){
        return this.utilisateurs;
    }

    public void setUtilisateurs(List<UtilisateurCriteria> utilisateurs){
        this.utilisateurs = utilisateurs;
    }
    public AcessShareCriteria getAcessShare(){
        return this.acessShare;
    }

    public void setAcessShare(AcessShareCriteria acessShare){
        this.acessShare = acessShare;
    }
    public List<AcessShareCriteria> getAcessShares(){
        return this.acessShares;
    }

    public void setAcessShares(List<AcessShareCriteria> acessShares){
        this.acessShares = acessShares;
    }
}
