package com.university.contractors.model.dto.students;

import com.university.contractors.util.BeanUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.core.GenericTypeResolver;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Base DTO class
 *
 * @ENTITY Base entity to use in modeler
 *
 * @author Eugene A. Kalosha <ekalosha@dfusiontech.com>
 * @version  0.1.1
 * @since    2018-10-17
 */
@Slf4j
public abstract class DTOBase<ENTITY> implements Serializable {

    @JsonIgnore
    @Getter
    private ENTITY entity;

    /**
     * Default DTO constructor
     */
    public DTOBase() {
    }

    /**
     * Entity DTO constructor
     */
    public DTOBase(ENTITY entity) {
        this();

        // Init from Entity
        if (entity != null) {
            this.entity = entity;
            fromEntity(entity);
        }
    }

    /**
     * Convert current model to Entity Instance
     *
     * @return
     */
    public ENTITY toEntity() {
        return toEntity(null);
    }

    /**
     * Convert current model to Entity Instance
     *
     * @return
     */
    public ENTITY toEntity(ENTITY update) {
        ENTITY result = null;

        Class<ENTITY> entityClass = (Class<ENTITY>) GenericTypeResolver.resolveTypeArgument(getClass(), DTOBase.class);
        ModelMapper modelMapper = BeanUtil.getBean(ModelMapper.class);

        if (update != null) {
            modelMapper.map(this, update);
            result = update;
        } else {
            result = modelMapper.map(this, entityClass);
        }

        return result;
    }

    /**
     * Convert current model to Entity Instance
     *
     * @return
     */
    public void fromEntity(ENTITY entity) {
        this.entity = entity;

        long startTime = System.currentTimeMillis();
        ENTITY unproxied = (ENTITY) Hibernate.unproxy(entity);
        long unproxyTime = System.currentTimeMillis();
        if (unproxyTime - startTime > 200) {
            log.warn(MessageFormat.format("# ModelMapper [{0}]. Unproxy time: {1}", this.getClass().getSimpleName(), (unproxyTime - startTime)));
        } else {
            log.debug(MessageFormat.format("# ModelMapper [{0}]. Unproxy time: {1}", this.getClass().getSimpleName(), (unproxyTime - startTime)));
        }
        ModelMapper modelMapper = BeanUtil.getBean(ModelMapper.class);
        modelMapper.map(unproxied,this);
        long mappingTime = System.currentTimeMillis() - unproxyTime;
        if (mappingTime > 200) {
            log.warn(MessageFormat.format("# ModelMapper [{0}]. Mapping time: {1}", this.getClass().getSimpleName(), mappingTime));
        } else {
            log.debug(MessageFormat.format("# ModelMapper [{0}]. Mapping time: {1}", this.getClass().getSimpleName(), mappingTime));
        }
    }

    /**
     * Get list DTO from the entity
     *
     * @param entities
     * @param dtoClass
     * @param <E>
     * @param <D>
     * @return
     */
    public static <E, D extends DTOBase> List<D> fromEntitiesList(List<E> entities, Class<D> dtoClass) {

        List<D> result = new ArrayList<>();

        // Initialize items list for ENTITY Element
        Optional.ofNullable(entities).orElse(new ArrayList<>()).forEach(entity -> {
            try {
                long startTime = System.currentTimeMillis();
                D newItem = dtoClass.getDeclaredConstructor().newInstance();
                newItem.fromEntity(entity);
                result.add(newItem);
                log.debug(MessageFormat.format("## DTO Entity Mapper [{0}]. Instantiation time: {1}", dtoClass.getSimpleName(), (System.currentTimeMillis() - startTime)));
            } catch (InstantiationException exception) {
                exception.printStackTrace();
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            } catch (NoSuchMethodException exception) {
                exception.printStackTrace();
            } catch (InvocationTargetException exception) {
                exception.printStackTrace();
            }
        });

        return result;
    }
}
