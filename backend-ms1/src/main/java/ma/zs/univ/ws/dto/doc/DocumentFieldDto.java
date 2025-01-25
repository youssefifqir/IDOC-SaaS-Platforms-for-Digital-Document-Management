package  ma.zs.univ.ws.dto.doc;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentFieldDto  extends AuditBaseDto {

    private String value  ;

    private FieldDto field ;
    private DocumentDto document ;
    private DocumentFieldStateDto documentFieldState ;



    public DocumentFieldDto(){
        super();
    }



    @Log
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }


    public FieldDto getField(){
        return this.field;
    }

    public void setField(FieldDto field){
        this.field = field;
    }
    public DocumentDto getDocument(){
        return this.document;
    }

    public void setDocument(DocumentDto document){
        this.document = document;
    }
    public DocumentFieldStateDto getDocumentFieldState(){
        return this.documentFieldState;
    }

    public void setDocumentFieldState(DocumentFieldStateDto documentFieldState){
        this.documentFieldState = documentFieldState;
    }






}
