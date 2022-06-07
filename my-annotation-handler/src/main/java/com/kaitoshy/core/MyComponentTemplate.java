package com.kaitoshy.core;

import org.springframework.beans.factory.DisposableBean;

import java.lang.reflect.Proxy;

public class MyComponentTemplate<T> implements DisposableBean {

   // private MyComponentTemplateFactory

    public T getTemplate() {
        return Proxy.newProxyInstance(MyComponentTemplate.class.getClassLoader(), );
    }


    @Override
    public void destroy() throws Exception {

    }
}
