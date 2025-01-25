package ma.zs.univ.bean.core.purchase;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.bean.core.user.Utilisateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_partage_utilisateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="document_partage_utilisateur_seq",sequenceName="document_partage_utilisateur_seq",allocationSize=1, initialValue = 1)
public class DocumentPartageUtilisateur  extends BaseEntity     {

    private Long id;



    private LocalDateTime dateShare ;

    private Document document ;
    private Utilisateur utilisateur ;
    private AcessShare acessShare ;


    public DocumentPartageUtilisateur(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="document_partage_utilisateur_seq")
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
    @JoinColumn(name = "utilisateur")
    public Utilisateur getUtilisateur(){
        return this.utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }
    public LocalDateTime getDateShare(){
        return this.dateShare;
    }
    public void setDateShare(LocalDateTime dateShare){
        this.dateShare = dateShare;
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
        DocumentPartageUtilisateur documentPartageUtilisateur = (DocumentPartageUtilisateur) o;
        return id != null && id.equals(documentPartageUtilisateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

