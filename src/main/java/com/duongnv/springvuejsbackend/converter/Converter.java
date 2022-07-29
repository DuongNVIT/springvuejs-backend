package com.duongnv.springvuejsbackend.converter;

import org.modelmapper.ModelMapper;

public abstract class Converter<T, K> {
    protected ModelMapper modelMapper = new ModelMapper();
    abstract K entityToDTO(T t);
    abstract T dtoToEntity(K k);
    abstract T entityToEntity(T t, K k);
}
