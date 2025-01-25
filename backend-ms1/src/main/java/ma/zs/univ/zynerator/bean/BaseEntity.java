package ma.zs.univ.zynerator.bean;


import jakarta.persistence.Transient;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;



public class BaseEntity {


    
    protected Long id;
    protected String label;


    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object object) {
        if (this.id != null && object instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) object;
            return this.id.equals(baseEntity.getId());
        }
        return false;

    }


    public int hashCode() {
        Serializable pk = id;
        if (pk == null) {
            return 0;
        }
        return pk.toString().hashCode();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        label = id != null ? id.toString() : null;
        return label;
    }

    public String toString() {

        return this.getId() != null ? this.getId().toString() : null;
    }
}
