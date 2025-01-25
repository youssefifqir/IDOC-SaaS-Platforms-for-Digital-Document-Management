package ma.zs.univ.bean.core.doc;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_field")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="document_field_seq",sequenceName="document_field_seq",allocationSize=1, initialValue = 1)
public class DocumentField  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String value;

    private Field field ;
    private Document document ;
    private DocumentFieldState documentFieldState ;


    public DocumentField(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="document_field_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field")
    public Field getField(){
        return this.field;
    }
    public void setField(Field field){
        this.field = field;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document")
    public Document getDocument(){
        return this.document;
    }
    public void setDocument(Document document){
        this.document = document;
    }
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_field_state")
    public DocumentFieldState getDocumentFieldState(){
        return this.documentFieldState;
    }
    public void setDocumentFieldState(DocumentFieldState documentFieldState){
        this.documentFieldState = documentFieldState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentField documentField = (DocumentField) o;
        return id != null && id.equals(documentField.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

