package  ma.zs.univ.dao.criteria.core.doc;



import ma.zs.univ.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DocumentTagCriteria extends  BaseCriteria  {


    private DocumentCriteria document ;
    private List<DocumentCriteria> documents ;
    private TagCriteria tag ;
    private List<TagCriteria> tags ;


    public DocumentTagCriteria(){}


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
    public TagCriteria getTag(){
        return this.tag;
    }

    public void setTag(TagCriteria tag){
        this.tag = tag;
    }
    public List<TagCriteria> getTags(){
        return this.tags;
    }

    public void setTags(List<TagCriteria> tags){
        this.tags = tags;
    }
}
