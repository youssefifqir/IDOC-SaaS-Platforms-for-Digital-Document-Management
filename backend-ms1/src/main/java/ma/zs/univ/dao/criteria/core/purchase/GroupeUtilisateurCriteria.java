package  ma.zs.univ.dao.criteria.core.purchase;


import ma.zs.univ.dao.criteria.core.user.GroupeCriteria;
import ma.zs.univ.dao.criteria.core.user.UtilisateurCriteria;

import ma.zs.univ.zynerator.criteria.BaseCriteria;
import java.util.List;

public class GroupeUtilisateurCriteria extends  BaseCriteria  {


    private GroupeCriteria groupe ;
    private List<GroupeCriteria> groupes ;
    private UtilisateurCriteria utilisateur ;
    private List<UtilisateurCriteria> utilisateurs ;


    public GroupeUtilisateurCriteria(){}


    public GroupeCriteria getGroupe(){
        return this.groupe;
    }

    public void setGroupe(GroupeCriteria groupe){
        this.groupe = groupe;
    }
    public List<GroupeCriteria> getGroupes(){
        return this.groupes;
    }

    public void setGroupes(List<GroupeCriteria> groupes){
        this.groupes = groupes;
    }
    public UtilisateurCriteria getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurCriteria utilisateur){
        this.utilisateur = utilisateur;
    }
    public List<UtilisateurCriteria> getUtilisateurs(){
        return this.utilisateurs;
    }

    public void setUtilisateurs(List<UtilisateurCriteria> utilisateurs){
        this.utilisateurs = utilisateurs;
    }
}
