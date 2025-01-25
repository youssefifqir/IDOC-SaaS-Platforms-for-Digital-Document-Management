package  ma.zs.univ.ws.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.univ.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.ws.converter.abonne.AbonneConverter;
import ma.zs.univ.ws.converter.doc.EntiteAdministrativeTypeConverter;
import ma.zs.univ.ws.converter.user.UtilisateurConverter;



import ma.zs.univ.zynerator.util.StringUtil;
import ma.zs.univ.zynerator.converter.AbstractConverter;
import ma.zs.univ.zynerator.util.DateUtil;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.ws.dto.user.EntiteAdministrativeDto;

@Component
public class EntiteAdministrativeConverter {

    @Autowired
    private AbonneConverter abonneConverter;
    @Autowired
    private EntiteAdministrativeTypeConverter entiteAdministrativeTypeConverter;
    @Autowired
    private UtilisateurConverter utilisateurConverter;
    private boolean chef;
    private boolean entiteAdministrativeType;
    private boolean abonne;

    public EntiteAdministrativeConverter() {
        initObject(true);
    }

    public EntiteAdministrative toItem(EntiteAdministrativeDto dto) {
        if (dto == null) {
            return null;
        } else {
            EntiteAdministrative item = new EntiteAdministrative();
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if (StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if (StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if (StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if (StringUtil.isNotEmpty(dto.getCodeEntiteAdminParent()))
                item.setCodeEntiteAdminParent(dto.getCodeEntiteAdminParent());
            if (this.chef && dto.getChef() != null)
                item.setChef(utilisateurConverter.toItem(dto.getChef()));
            if (this.entiteAdministrativeType && dto.getEntiteAdministrativeType() != null)
                item.setEntiteAdministrativeType(entiteAdministrativeTypeConverter.toItem(dto.getEntiteAdministrativeType()));
            if (this.abonne && dto.getAbonne() != null)
                item.setAbonne(abonneConverter.toItem(dto.getAbonne()));
            return item;
        }
    }

    public EntiteAdministrativeDto toDto(EntiteAdministrative item) {
        if (item == null) {
            return null;
        } else {
            EntiteAdministrativeDto dto = new EntiteAdministrativeDto();
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if (StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if (StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if (StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if (StringUtil.isNotEmpty(item.getCodeEntiteAdminParent()))
                dto.setCodeEntiteAdminParent(item.getCodeEntiteAdminParent());
            if (this.chef && item.getChef() != null)
                dto.setChef(utilisateurConverter.toDto(item.getChef()));
            if (this.entiteAdministrativeType && item.getEntiteAdministrativeType() != null)
                dto.setEntiteAdministrativeType(entiteAdministrativeTypeConverter.toDto(item.getEntiteAdministrativeType()));
            if (this.abonne && item.getAbonne() != null) {
                abonneConverter.setEntiteAdministrative(false);
                dto.setAbonne(abonneConverter.toDto(item.getAbonne()));
                abonneConverter.setEntiteAdministrative(true);
            }
            return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.chef = value;
        this.entiteAdministrativeType = value;
        this.abonne = value;
    }

    public List<EntiteAdministrative> toItem(List<EntiteAdministrativeDto> dtos) {
        List<EntiteAdministrative> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntiteAdministrativeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }

    public List<EntiteAdministrativeDto> toDto(List<EntiteAdministrative> items) {
        List<EntiteAdministrativeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntiteAdministrative item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }

    public void copy(EntiteAdministrativeDto dto, EntiteAdministrative t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getChef() != null)
            utilisateurConverter.copy(dto.getChef(), t.getChef());
        if (dto.getEntiteAdministrativeType() != null)
            entiteAdministrativeTypeConverter.copy(dto.getEntiteAdministrativeType(), t.getEntiteAdministrativeType());
        if (dto.getAbonne() != null)
            abonneConverter.copy(dto.getAbonne(), t.getAbonne());
    }

    public List<EntiteAdministrative> copy(List<EntiteAdministrativeDto> dtos) {
        List<EntiteAdministrative> result = new ArrayList<>();
        if (dtos != null) {
            for (EntiteAdministrativeDto dto : dtos) {
                EntiteAdministrative instance = new EntiteAdministrative();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }

    public AbonneConverter getAbonneConverter() {
        return this.abonneConverter;
    }

    public void setAbonneConverter(AbonneConverter abonneConverter) {
        this.abonneConverter = abonneConverter;
    }

    public EntiteAdministrativeTypeConverter getEntiteAdministrativeTypeConverter() {
        return this.entiteAdministrativeTypeConverter;
    }

    public void setEntiteAdministrativeTypeConverter(EntiteAdministrativeTypeConverter entiteAdministrativeTypeConverter) {
        this.entiteAdministrativeTypeConverter = entiteAdministrativeTypeConverter;
    }

    public UtilisateurConverter getUtilisateurConverter() {
        return this.utilisateurConverter;
    }

    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter) {
        this.utilisateurConverter = utilisateurConverter;
    }

    public boolean isChef() {
        return this.chef;
    }

    public void setChef(boolean chef) {
        this.chef = chef;
    }

    public boolean isEntiteAdministrativeType() {
        return this.entiteAdministrativeType;
    }

    public void setEntiteAdministrativeType(boolean entiteAdministrativeType) {
        this.entiteAdministrativeType = entiteAdministrativeType;
    }

    public boolean isAbonne() {
        return this.abonne;
    }

    public void setAbonne(boolean abonne) {
        this.abonne = abonne;
    }
}
