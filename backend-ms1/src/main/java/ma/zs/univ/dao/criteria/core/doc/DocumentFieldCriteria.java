package  ma.zs.univ.dao.criteria.core.doc;



import ma.zs.univ.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DocumentFieldCriteria extends  BaseCriteria  {

    private String value;
    private String valueLike;

    private FieldCriteria field ;
    private List<FieldCriteria> fields ;
    private DocumentCriteria document ;
    private List<DocumentCriteria> documents ;
    private DocumentFieldStateCriteria documentFieldState ;
    private List<DocumentFieldStateCriteria> documentFieldStates ;


    public DocumentFieldCriteria(){}

    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValueLike(){
        return this.valueLike;
    }
    public void setValueLike(String valueLike){
        this.valueLike = valueLike;
    }


    public FieldCriteria getField(){
        return this.field;
    }

    public void setField(FieldCriteria field){
        this.field = field;
    }
    public List<FieldCriteria> getFields(){
        return this.fields;
    }

    public void setFields(List<FieldCriteria> fields){
        this.fields = fields;
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
    public DocumentFieldStateCriteria getDocumentFieldState(){
        return this.documentFieldState;
    }

    public void setDocumentFieldState(DocumentFieldStateCriteria documentFieldState){
        this.documentFieldState = documentFieldState;
    }
    public List<DocumentFieldStateCriteria> getDocumentFieldStates(){
        return this.documentFieldStates;
    }

    public void setDocumentFieldStates(List<DocumentFieldStateCriteria> documentFieldStates){
        this.documentFieldStates = documentFieldStates;
    }
}
