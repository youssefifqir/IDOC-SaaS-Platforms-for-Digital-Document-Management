package ma.zs.univ.zynerator.security.bean;


import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;
import ma.zs.univ.zynerator.bean.BaseEntity;


@Entity
@Table(name = "role_app")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1, initialValue = 1)
public class Role extends BaseEntity implements GrantedAuthority {

    private Long id;


    private LocalDateTime updatedAt;
    private String label;


    private LocalDateTime createdAt;

    @Column(length = 500)
    private String authority;

    public Role() {
    super();
    }

    public Role(Long id, String authority) {
    this.id = id;
    this.authority = authority;
    }

    public Role(String authority) {
    this.authority = authority;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    public Long getId() {
    return this.id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getAuthority() {
    return this.authority;
    }

    public void setAuthority(String authority) {
    this.authority = authority;
    }

    public LocalDateTime getUpdatedAt() {
    return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
    }

    public void setLabel(String label) {
    this.label = label;
    }

    public LocalDateTime getCreatedAt() {
    return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    }


    @Transient
    public String getLabel() {
    label = authority;
    return label;
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return id != null && id.equals(role.id);
    }

    @Override
    public int hashCode() {
    return Objects.hash(id);
    }

}
