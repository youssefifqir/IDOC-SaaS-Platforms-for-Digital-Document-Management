package  ma.zs.univ.ws.dto.purchase;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.univ.ws.dto.user.GroupeDto;
import ma.zs.univ.ws.dto.user.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupeUtilisateurDto  extends AuditBaseDto {


    private GroupeDto groupe ;
    private UtilisateurDto utilisateur ;



    public GroupeUtilisateurDto(){
        super();
    }




    public GroupeDto getGroupe(){
        return this.groupe;
    }

    public void setGroupe(GroupeDto groupe){
        this.groupe = groupe;
    }
    public UtilisateurDto getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur){
        this.utilisateur = utilisateur;
    }






}
