package  ma.zs.univ.ws.converter.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.user.GroupeConverter;
import ma.zs.univ.ws.converter.user.UtilisateurConverter;

import ma.zs.univ.bean.core.user.Groupe;


import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.purchase.GroupeUtilisateur;
import ma.zs.univ.ws.dto.purchase.GroupeUtilisateurDto;

@Component
public class GroupeUtilisateurConverter {

    @Autowired
    private GroupeConverter groupeConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    private boolean groupe;
    private boolean utilisateur;

    public  GroupeUtilisateurConverter() {
        initObject(true);
    }


    public GroupeUtilisateur toItem(GroupeUtilisateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        GroupeUtilisateur item = new GroupeUtilisateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getGroupe() != null && dto.getGroupe().getId() != null){
                item.setGroupe(new Groupe());
                item.getGroupe().setId(dto.getGroupe().getId());
                item.getGroupe().setLibelle(dto.getGroupe().getLibelle());
            }

            if(this.utilisateur && dto.getUtilisateur()!=null)
                item.setUtilisateur(utilisateurConverter.toItem(dto.getUtilisateur())) ;




        return item;
        }
    }


    public GroupeUtilisateurDto toDto(GroupeUtilisateur item) {
        if (item == null) {
            return null;
        } else {
            GroupeUtilisateurDto dto = new GroupeUtilisateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.groupe && item.getGroupe()!=null) {
                dto.setGroupe(groupeConverter.toDto(item.getGroupe())) ;

            }
            if(this.utilisateur && item.getUtilisateur()!=null) {
                dto.setUtilisateur(utilisateurConverter.toDto(item.getUtilisateur())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.groupe = value;
        this.utilisateur = value;
    }
	
    public List<GroupeUtilisateur> toItem(List<GroupeUtilisateurDto> dtos) {
        List<GroupeUtilisateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GroupeUtilisateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GroupeUtilisateurDto> toDto(List<GroupeUtilisateur> items) {
        List<GroupeUtilisateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (GroupeUtilisateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GroupeUtilisateurDto dto, GroupeUtilisateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getGroupe() != null)
        groupeConverter.copy(dto.getGroupe(), t.getGroupe());
        if (dto.getUtilisateur() != null)
        utilisateurConverter.copy(dto.getUtilisateur(), t.getUtilisateur());
    }

    public List<GroupeUtilisateur> copy(List<GroupeUtilisateurDto> dtos) {
        List<GroupeUtilisateur> result = new ArrayList<>();
        if (dtos != null) {
            for (GroupeUtilisateurDto dto : dtos) {
                GroupeUtilisateur instance = new GroupeUtilisateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public GroupeConverter getGroupeConverter(){
        return this.groupeConverter;
    }
    public void setGroupeConverter(GroupeConverter groupeConverter ){
        this.groupeConverter = groupeConverter;
    }
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public boolean  isGroupe(){
        return this.groupe;
    }
    public void  setGroupe(boolean groupe){
        this.groupe = groupe;
    }
    public boolean  isUtilisateur(){
        return this.utilisateur;
    }
    public void  setUtilisateur(boolean utilisateur){
        this.utilisateur = utilisateur;
    }
}
