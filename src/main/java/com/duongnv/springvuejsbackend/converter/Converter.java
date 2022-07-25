package com.duongnv.springvuejsbackend.converter;

public interface Converter<T, K> {
    K entityToDTO(T t);
    T dtoToEntity(K k);
    T entityToEntity(T t, K k);
}
