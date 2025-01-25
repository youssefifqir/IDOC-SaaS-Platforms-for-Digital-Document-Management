package ma.zs.univ.bean.core.user;

import java.util.Objects;





import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entite_administrative")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="entite_administrative_seq",sequenceName="entite_administrative_seq",allocationSize=1, initialValue = 1)
public class EntiteAdministrative  extends BaseEntity     {

    private Long id;
    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String codeEntiteAdminParent;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String libelle;

    private Utilisateur chef ;
    private EntiteAdministrativeType entiteAdministrativeType ;
    private Abonne abonne ;


    public EntiteAdministrative(){
        super();
    }

    public EntiteAdministrative(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public EntiteAdministrative(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="entite_administrative_seq")
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
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef")
    public Utilisateur getChef(){
        return this.chef;
    }
    public void setChef(Utilisateur chef){
        this.chef = chef;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_administrative_type")
    public EntiteAdministrativeType getEntiteAdministrativeType(){
        return this.entiteAdministrativeType;
    }
    public void setEntiteAdministrativeType(EntiteAdministrativeType entiteAdministrativeType){
        this.entiteAdministrativeType = entiteAdministrativeType;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abonne")
    public Abonne getAbonne(){
        return this.abonne;
    }
    public void setAbonne(Abonne abonne){
        this.abonne = abonne;
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
        EntiteAdministrative entiteAdministrative = (EntiteAdministrative) o;
        return id != null && id.equals(entiteAdministrative.id);
    }

    public String getCodeEntiteAdminParent() {
        return codeEntiteAdminParent;
    }

    public void setCodeEntiteAdminParent(String codeEntiteAdminParent) {
        this.codeEntiteAdminParent = codeEntiteAdminParent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

