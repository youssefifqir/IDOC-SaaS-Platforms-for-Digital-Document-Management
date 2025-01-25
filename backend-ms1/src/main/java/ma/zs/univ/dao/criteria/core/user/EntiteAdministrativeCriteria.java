package  ma.zs.univ.dao.criteria.core.user;


import ma.zs.univ.dao.criteria.core.abonne.AbonneCriteria;
import ma.zs.univ.dao.criteria.core.doc.EntiteAdministrativeTypeCriteria;

import ma.zs.univ.zynerator.criteria.BaseCriteria;
import java.util.List;

public class EntiteAdministrativeCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String description;
    private String descriptionLike;
    private String libelle;
    private String libelleLike;

    private UtilisateurCriteria chef ;
    private List<UtilisateurCriteria> chefs ;
    private EntiteAdministrativeTypeCriteria entiteAdministrativeType ;
    private List<EntiteAdministrativeTypeCriteria> entiteAdministrativeTypes ;
    private AbonneCriteria abonne ;
    private List<AbonneCriteria> abonnes ;


    public EntiteAdministrativeCriteria(){}

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }


    public UtilisateurCriteria getChef(){
        return this.chef;
    }

    public void setChef(UtilisateurCriteria chef){
        this.chef = chef;
    }
    public List<UtilisateurCriteria> getChefs(){
        return this.chefs;
    }

    public void setChefs(List<UtilisateurCriteria> chefs){
        this.chefs = chefs;
    }
    public EntiteAdministrativeTypeCriteria getEntiteAdministrativeType(){
        return this.entiteAdministrativeType;
    }

    public void setEntiteAdministrativeType(EntiteAdministrativeTypeCriteria entiteAdministrativeType){
        this.entiteAdministrativeType = entiteAdministrativeType;
    }
    public List<EntiteAdministrativeTypeCriteria> getEntiteAdministrativeTypes(){
        return this.entiteAdministrativeTypes;
    }

    public void setEntiteAdministrativeTypes(List<EntiteAdministrativeTypeCriteria> entiteAdministrativeTypes){
        this.entiteAdministrativeTypes = entiteAdministrativeTypes;
    }
    public AbonneCriteria getAbonne(){
        return this.abonne;
    }

    public void setAbonne(AbonneCriteria abonne){
        this.abonne = abonne;
    }
    public List<AbonneCriteria> getAbonnes(){
        return this.abonnes;
    }

    public void setAbonnes(List<AbonneCriteria> abonnes){
        this.abonnes = abonnes;
    }
}
