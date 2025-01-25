package  ma.zs.univ.ws.dto.user;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zs.univ.ws.dto.purchase.GroupeUtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupeDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;

    private UtilisateurDto utilisateur ;

    private List<GroupeUtilisateurDto> groupeUtilisateurs ;


    public GroupeDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public UtilisateurDto getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur){
        this.utilisateur = utilisateur;
    }



    public List<GroupeUtilisateurDto> getGroupeUtilisateurs(){
        return this.groupeUtilisateurs;
    }

    public void setGroupeUtilisateurs(List<GroupeUtilisateurDto> groupeUtilisateurs){
        this.groupeUtilisateurs = groupeUtilisateurs;
    }



}
