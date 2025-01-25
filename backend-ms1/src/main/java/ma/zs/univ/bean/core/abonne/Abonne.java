package ma.zs.univ.bean.core.abonne;

import java.util.Objects;





import ma.zs.univ.bean.core.user.EntiteAdministrative;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.univ.zynerator.security.bean.User;

@Entity
@Table(name = "abonne")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="abonne_seq",sequenceName="abonne_seq",allocationSize=1, initialValue = 1)
public class Abonne  extends User    {


    public Abonne(String username) {
        super(username);
    }


    @Column(length = 500)
    private String description;








    private EntiteAdministrative entiteAdministrative ;


    public Abonne(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="abonne_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_administrative")
    public EntiteAdministrative getEntiteAdministrative(){
        return this.entiteAdministrative;
    }
    public void setEntiteAdministrative(EntiteAdministrative entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonne abonne = (Abonne) o;
        return id != null && id.equals(abonne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

