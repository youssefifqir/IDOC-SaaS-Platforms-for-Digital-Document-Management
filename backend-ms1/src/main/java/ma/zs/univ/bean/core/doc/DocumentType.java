package ma.zs.univ.bean.core.doc;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="document_type_seq",sequenceName="document_type_seq",allocationSize=1, initialValue = 1)
public class DocumentType  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public DocumentType(){
        super();
    }

    public DocumentType(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public DocumentType(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="document_type_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentType documentType = (DocumentType) o;
        return id != null && id.equals(documentType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

