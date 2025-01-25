package ma.zs.univ.zynerator.security.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.univ.zynerator.bean.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "role_app_user_app")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="role_utilisateur_seq",sequenceName="role_app_user_app_seq",allocationSize=1, initialValue = 1)
public class RoleUser   extends BaseEntity {

    private Long id;
    private Role role ;
    private User user ;


    public RoleUser(){
    super();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="role_app_user_app_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public Role getRole(){
        return this.role;
    }
    public void setRole(Role role){
        this.role = role;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUser roleUser = (RoleUser) o;
        return id != null && id.equals(roleUser.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

