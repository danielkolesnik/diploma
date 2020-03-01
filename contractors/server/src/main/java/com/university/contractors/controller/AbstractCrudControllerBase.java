package com.university.contractors.controller;

import com.university.contractors.model.jpa.entity.IdEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


public abstract class AbstractCrudControllerBase<I, E extends IdEntity<I>> {

    private final CrudRepository<E, I> crudRepository;

    public AbstractCrudControllerBase(CrudRepository<E, I> crudRepository) {
        this.crudRepository = crudRepository;
    }

    E getById(I id) {
        return crudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with ID '%s' was not found."));
    }

    Iterable<E> getAll() {
        return crudRepository.findAll();
    }

    E create(E entityToCreate) {
        return crudRepository.save(entityToCreate);
    }

    E update(I id, E entityToUpdateWith) {
        final I entityId = entityToUpdateWith.getId();

        if (Objects.isNull(entityId)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Entity to update should have ID.");
        }

        if (ObjectUtils.notEqual(id, entityId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ID of Entity and ID specified in the request path should match.");
        }

        return crudRepository.save(entityToUpdateWith);
    }

    I delete(I id) {
        boolean isEntityWithGivenIdExists = crudRepository.existsById(id);

        if (!isEntityWithGivenIdExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Entity with ID '%s' was not found.");
        }

        crudRepository.deleteById(id);

        return id;
    }
}
