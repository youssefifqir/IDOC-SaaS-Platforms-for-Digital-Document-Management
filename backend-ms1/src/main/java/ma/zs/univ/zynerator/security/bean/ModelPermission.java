package ma.zs.univ.zynerator.security.bean;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "model_permission")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="model_permission_seq",sequenceName="model_permission_seq",allocationSize=1, initialValue = 1)
public class ModelPermission   extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String reference;
    @Column(length = 500)
    private String libelle;



    public ModelPermission(){
    super();
    }

    public ModelPermission(Long id,String reference){
    this.id = id;
    this.reference = reference ;
    }
    public ModelPermission(String reference){
    this.reference = reference ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="model_permission_seq")
    public Long getId(){
    return this.id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public String getReference(){
    return this.reference;
    }
    public void setReference(String reference){
    this.reference = reference;
    }
    public String getLibelle(){
    return this.libelle;
    }
    public void setLibelle(String libelle){
    this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
    label = reference;
    return label;
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ModelPermission modelPermission = (ModelPermission) o;
    return id != null && id.equals(modelPermission.id);
    }

    @Override
    public int hashCode() {
    return Objects.hash(id);
    }

}

