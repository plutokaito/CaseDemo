package com.kaitoshy.core;

import java.lang.reflect.Field;

public class DefaultService implements DbCurdService{
    @Override
    public <T> T selectOne(Class<T> tClass) {
        System.out.println(tClass.getName());

        try {
           T t = tClass.newInstance();
           Class<?> z = Class.forName(t.getClass().getName());
           Field[] fields = z.getDeclaredFields();
            for (Field f: fields) {
                System.out.println(">=====" + f.getName() + ":" + f.getType().getName());
                f.setAccessible(true);
                Class<?> type = f.getType();
                if (Long.class.isAssignableFrom(type)) {
                    f.set(t, 111L);
                } else if (String.class.isAssignableFrom(type)) {
                    f.set(t, "22222");
                }
            }

            return t;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T getService(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
