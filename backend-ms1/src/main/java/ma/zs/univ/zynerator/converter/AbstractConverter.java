package ma.zs.univ.zynerator.converter;

import ma.zs.univ.zynerator.bean.BaseEntity;
import ma.zs.univ.zynerator.dto.BaseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class AbstractConverter<T extends BaseEntity, DTO extends BaseDto> {
    protected Class<T> itemType;
    protected Class<DTO> dtoType;

    protected AbstractConverter(Class<T> itemType, Class<DTO> dtoType) {
        this.itemType = itemType;
        this.dtoType = dtoType;
        this.init(true);
    }

    public abstract T toItem(DTO dto);

    public abstract DTO toDto(T item);

    public void copy(DTO dto, T t) {
        if (dto != null && t != null) {
            copyNonNullProperties(dto, t);
        }
    }

    public List<T> copy(List<DTO> dtos) {
        List<T> result = new ArrayList<>();
        if (dtos != null) {
            for (DTO dto : dtos) {
                T instance = null;
                try {
                    instance = itemType.getDeclaredConstructor().newInstance();
                    copy(dto, instance);
                } catch (Exception e) {

                }
                if (instance != null) {
                    result.add(instance);
                }
            }
        }
        return result.isEmpty() ? null : result;
    }


    public List<T> toItem(List<DTO> dtos) {
        List<T> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DTO DTO : dtos) {
                items.add(toItem(DTO));
            }
        }
        return items;
    }


    public List<DTO> toDto(List<T> items) {
        List<DTO> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (T item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }

    public void initObject(boolean initialisationObject) {

    }

    public void initList(boolean initialisationList) {

    }

    public void init(boolean initialisation) {
        initObject(initialisation);
        initList(initialisation);
    }


    private void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, AbstractConverterHelper.getNullPropertyNames(src));
    }


}
