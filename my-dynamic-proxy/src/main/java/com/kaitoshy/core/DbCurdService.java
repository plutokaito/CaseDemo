package com.kaitoshy.core;

public interface DbCurdService {
    <R> R selectOne(Class<R> tClass);
    <T> T getService(Class<T> clazz) throws InstantiationException, IllegalAccessException;
}
