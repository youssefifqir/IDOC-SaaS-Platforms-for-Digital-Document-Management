package  ma.zs.univ.ws.dto.user;

import ma.zs.univ.zynerator.audit.Log;
import ma.zs.univ.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.univ.ws.dto.abonne.AbonneDto;
import ma.zs.univ.ws.dto.doc.EntiteAdministrativeTypeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntiteAdministrativeDto  extends AuditBaseDto {

    private String code  ;
    private String description  ;
    private String libelle  ;
    private String codeEntiteAdminParent;

    private UtilisateurDto chef ;
    private EntiteAdministrativeTypeDto entiteAdministrativeType ;
    private AbonneDto abonne ;



    public EntiteAdministrativeDto(){
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
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public UtilisateurDto getChef(){
        return this.chef;
    }

    public void setChef(UtilisateurDto chef){
        this.chef = chef;
    }
    public EntiteAdministrativeTypeDto getEntiteAdministrativeType(){
        return this.entiteAdministrativeType;
    }

    public void setEntiteAdministrativeType(EntiteAdministrativeTypeDto entiteAdministrativeType){
        this.entiteAdministrativeType = entiteAdministrativeType;
    }
    public AbonneDto getAbonne(){
        return this.abonne;
    }

    public void setAbonne(AbonneDto abonne){
        this.abonne = abonne;
    }

    public String getCodeEntiteAdminParent() {
        return codeEntiteAdminParent;
    }

    public void setCodeEntiteAdminParent(String codeEntiteAdminParent) {
        this.codeEntiteAdminParent = codeEntiteAdminParent;
    }
}
