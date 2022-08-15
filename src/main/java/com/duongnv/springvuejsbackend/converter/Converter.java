package com.duongnv.springvuejsbackend.converter;

import org.modelmapper.ModelMapper;

public interface Converter<T, K> {
    abstract K entityToDTO(T t);
    abstract T dtoToEntity(K k);
    abstract T entityToEntity(T t, K k);
}
