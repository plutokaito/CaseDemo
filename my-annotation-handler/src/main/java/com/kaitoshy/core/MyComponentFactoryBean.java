package com.kaitoshy.core;

import org.springframework.beans.factory.FactoryBean;

public class MyComponentFactoryBean<T> implements FactoryBean<T> {

    private Class<T> interfaceService;

    private MyComponentTemplate componentTemplate;

    @Override
    public T getObject() throws Exception {
        return componentTemplate.getTemplate(interfaceService);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceService.getComponentType();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
