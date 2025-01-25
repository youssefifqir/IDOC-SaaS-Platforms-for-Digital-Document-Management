package ma.zs.univ.bean.core.doc;

import java.util.Objects;





import ma.zs.univ.bean.core.referetiel.AcessShare;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_acess_share")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="document_acess_share_seq",sequenceName="document_acess_share_seq",allocationSize=1, initialValue = 1)
public class DocumentAcessShare  extends BaseEntity     {

    private Long id;



    private Document document ;
    private AcessShare acessShare ;


    public DocumentAcessShare(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="document_acess_share_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document")
    public Document getDocument(){
        return this.document;
    }
    public void setDocument(Document document){
        this.document = document;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acess_share")
    public AcessShare getAcessShare(){
        return this.acessShare;
    }
    public void setAcessShare(AcessShare acessShare){
        this.acessShare = acessShare;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentAcessShare documentAcessShare = (DocumentAcessShare) o;
        return id != null && id.equals(documentAcessShare.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

