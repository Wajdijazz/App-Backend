package com.followup.davidson.converter;

import java.util.Collection;

import java.util.stream.Collectors;
public interface GenericsConverter<Entity, Dto> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto dto);
    default Collection<Dto> entityListToDtoList(Collection<Entity> entityList){
        return entityList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    default Collection<Entity> dtoListToEntityList(Collection<Dto> dtoList) {
        return dtoList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
