package  ma.zs.univ.ws.converter.abonne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.user.EntiteAdministrativeConverter;



import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.ws.dto.abonne.AbonneDto;

@Component
public class AbonneConverter {

    @Autowired
    private EntiteAdministrativeConverter entiteAdministrativeConverter ;
    private boolean entiteAdministrative;

    public  AbonneConverter() {
        initObject(true);
    }


    public Abonne toItem(AbonneDto dto) {
        if (dto == null) {
            return null;
        } else {
        Abonne item = new Abonne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setCredentialsNonExpired(dto.getAccountNonExpired() != null && dto.getAccountNonExpired());
            item.setEnabled(dto.getEnabled() != null && dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired() != null && dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked() != null && dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged() != null && dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(this.entiteAdministrative && dto.getEntiteAdministrative()!=null)
                item.setEntiteAdministrative(entiteAdministrativeConverter.toItem(dto.getEntiteAdministrative())) ;



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public AbonneDto toDto(Abonne item) {
        if (item == null) {
            return null;
        } else {
            AbonneDto dto = new AbonneDto();
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
            if(this.entiteAdministrative && item.getEntiteAdministrative()!=null) {
                entiteAdministrativeConverter.setAbonne(false);
                dto.setEntiteAdministrative(entiteAdministrativeConverter.toDto(item.getEntiteAdministrative())) ;
                entiteAdministrativeConverter.setAbonne(true);

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.entiteAdministrative = value;
    }
	
    public List<Abonne> toItem(List<AbonneDto> dtos) {
        List<Abonne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AbonneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AbonneDto> toDto(List<Abonne> items) {
        List<AbonneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Abonne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AbonneDto dto, Abonne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEntiteAdministrative() != null)
        entiteAdministrativeConverter.copy(dto.getEntiteAdministrative(), t.getEntiteAdministrative());
    }

    public List<Abonne> copy(List<AbonneDto> dtos) {
        List<Abonne> result = new ArrayList<>();
        if (dtos != null) {
            for (AbonneDto dto : dtos) {
                Abonne instance = new Abonne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EntiteAdministrativeConverter getEntiteAdministrativeConverter(){
        return this.entiteAdministrativeConverter;
    }
    public void setEntiteAdministrativeConverter(EntiteAdministrativeConverter entiteAdministrativeConverter ){
        this.entiteAdministrativeConverter = entiteAdministrativeConverter;
    }
    public boolean  isEntiteAdministrative(){
        return this.entiteAdministrative;
    }
    public void  setEntiteAdministrative(boolean entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }
}
