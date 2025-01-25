package ma.zs.univ.zynerator.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import ma.zs.univ.zynerator.bean.BusinessObject;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(EntityListener.class)
public class AuditBusinessObject extends BusinessObject {


    protected LocalDateTime createdOn;
    protected LocalDateTime updatedOn;
    protected String createdBy;
    protected String updatedBy;
    public AuditBusinessObject() {
        super();
    }

    public AuditBusinessObject(Long id) {
        super(id);
    }

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "createdon", updatable = false)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }


    public void setCreatedOn(LocalDateTime createOn) {
        this.createdOn = createOn;
    }

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "updatedon")
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "createdby", updatable = false)
    public String getCreatedBy() {
        return createdBy != null ? createdBy : "";
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "updatedby")
    public String getUpdatedBy() {
        return updatedBy != null ? updatedBy : "";
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
