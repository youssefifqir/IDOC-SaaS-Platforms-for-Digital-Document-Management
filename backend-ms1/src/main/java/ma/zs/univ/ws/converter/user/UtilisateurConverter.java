package  ma.zs.univ.ws.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.abonne.AbonneConverter;



import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.user.Utilisateur;
import ma.zs.univ.ws.dto.user.UtilisateurDto;

@Component
public class UtilisateurConverter {

    @Autowired
    private AbonneConverter abonneConverter ;
    private boolean abonne;

    public  UtilisateurConverter() {
        initObject(true);
    }


    public Utilisateur toItem(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Utilisateur item = new Utilisateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired() != null && dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled() != null && dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired() != null && dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked() != null && dto.getAccountNonLocked());
            //
            //
            item.setPasswordChanged(dto.getPasswordChanged() != null && dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(this.abonne && dto.getAbonne()!=null)
                item.setAbonne(abonneConverter.toItem(dto.getAbonne())) ;



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public UtilisateurDto toDto(Utilisateur item) {
        if (item == null) {
            return null;
        } else {
            UtilisateurDto dto = new UtilisateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(this.abonne && item.getAbonne()!=null) {
                dto.setAbonne(abonneConverter.toDto(item.getAbonne())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.abonne = value;
    }
	
    public List<Utilisateur> toItem(List<UtilisateurDto> dtos) {
        List<Utilisateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (UtilisateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<UtilisateurDto> toDto(List<Utilisateur> items) {
        List<UtilisateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Utilisateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(UtilisateurDto dto, Utilisateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getAbonne() != null)
        abonneConverter.copy(dto.getAbonne(), t.getAbonne());
    }

    public List<Utilisateur> copy(List<UtilisateurDto> dtos) {
        List<Utilisateur> result = new ArrayList<>();
        if (dtos != null) {
            for (UtilisateurDto dto : dtos) {
                Utilisateur instance = new Utilisateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AbonneConverter getAbonneConverter(){
        return this.abonneConverter;
    }
    public void setAbonneConverter(AbonneConverter abonneConverter ){
        this.abonneConverter = abonneConverter;
    }
    public boolean  isAbonne(){
        return this.abonne;
    }
    public void  setAbonne(boolean abonne){
        this.abonne = abonne;
    }
}
