package  ma.zs.univ.ws.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.univ.zynerator.util.ListUtil;

import ma.zs.univ.ws.converter.purchase.GroupeUtilisateurConverter;
import ma.zs.univ.ws.converter.user.UtilisateurConverter;



import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.user.Groupe;
import ma.zs.univ.ws.dto.user.GroupeDto;

@Component
public class GroupeConverter {

    @Autowired
    private GroupeUtilisateurConverter groupeUtilisateurConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    private boolean utilisateur;
    private boolean groupeUtilisateurs;

    public  GroupeConverter() {
        init(true);
    }


    public Groupe toItem(GroupeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Groupe item = new Groupe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.utilisateur && dto.getUtilisateur()!=null)
                item.setUtilisateur(utilisateurConverter.toItem(dto.getUtilisateur())) ;


            if(this.groupeUtilisateurs && ListUtil.isNotEmpty(dto.getGroupeUtilisateurs()))
                item.setGroupeUtilisateurs(groupeUtilisateurConverter.toItem(dto.getGroupeUtilisateurs()));


        return item;
        }
    }


    public GroupeDto toDto(Groupe item) {
        if (item == null) {
            return null;
        } else {
            GroupeDto dto = new GroupeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.utilisateur && item.getUtilisateur()!=null) {
                dto.setUtilisateur(utilisateurConverter.toDto(item.getUtilisateur())) ;

            }
        if(this.groupeUtilisateurs && ListUtil.isNotEmpty(item.getGroupeUtilisateurs())){
            groupeUtilisateurConverter.init(true);
            groupeUtilisateurConverter.setGroupe(false);
            dto.setGroupeUtilisateurs(groupeUtilisateurConverter.toDto(item.getGroupeUtilisateurs()));
            groupeUtilisateurConverter.setGroupe(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.groupeUtilisateurs = value;
    }
    public void initObject(boolean value) {
        this.utilisateur = value;
    }
	
    public List<Groupe> toItem(List<GroupeDto> dtos) {
        List<Groupe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GroupeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GroupeDto> toDto(List<Groupe> items) {
        List<GroupeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Groupe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GroupeDto dto, Groupe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getUtilisateur() != null)
        utilisateurConverter.copy(dto.getUtilisateur(), t.getUtilisateur());
        if (dto.getGroupeUtilisateurs() != null)
            t.setGroupeUtilisateurs(groupeUtilisateurConverter.copy(dto.getGroupeUtilisateurs()));
    }

    public List<Groupe> copy(List<GroupeDto> dtos) {
        List<Groupe> result = new ArrayList<>();
        if (dtos != null) {
            for (GroupeDto dto : dtos) {
                Groupe instance = new Groupe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public GroupeUtilisateurConverter getGroupeUtilisateurConverter(){
        return this.groupeUtilisateurConverter;
    }
    public void setGroupeUtilisateurConverter(GroupeUtilisateurConverter groupeUtilisateurConverter ){
        this.groupeUtilisateurConverter = groupeUtilisateurConverter;
    }
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public boolean  isUtilisateur(){
        return this.utilisateur;
    }
    public void  setUtilisateur(boolean utilisateur){
        this.utilisateur = utilisateur;
    }
    public boolean  isGroupeUtilisateurs(){
        return this.groupeUtilisateurs ;
    }
    public void  setGroupeUtilisateurs(boolean groupeUtilisateurs ){
        this.groupeUtilisateurs  = groupeUtilisateurs ;
    }
}
