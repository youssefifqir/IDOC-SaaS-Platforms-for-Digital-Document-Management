package  ma.zs.univ.dao.criteria.core.doc;


import ma.zs.univ.dao.criteria.core.referetiel.AcessShareCriteria;

import ma.zs.univ.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DocumentAcessShareCriteria extends  BaseCriteria  {


    private DocumentCriteria document ;
    private List<DocumentCriteria> documents ;
    private AcessShareCriteria acessShare ;
    private List<AcessShareCriteria> acessShares ;


    public DocumentAcessShareCriteria(){}


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
