package com.commerzbank.library.mapper;

import java.util.function.Function;

public class Mapper<T, U> {
    private  final Function<T,U> toEntity;
    private  final Function<U,T> toDto;

    public Mapper(Function<T, U> toEntity, Function<U, T> toDto) {
        this.toEntity = toEntity;
        this.toDto = toDto;
    }


    public final U mapFromDto(final T dto){
        return toEntity.apply(dto);
    }

    public final T mapFromEntity(final U entity){
        return toDto.apply(entity);
    }
}
