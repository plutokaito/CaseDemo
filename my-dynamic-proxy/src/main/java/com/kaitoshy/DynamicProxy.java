package com.kaitoshy;

import com.kaitoshy.core.DbCurd;
import com.kaitoshy.core.DefaultService;
import com.kaitoshy.service.UserService;
import com.kaitoshy.service.VideoService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;

public class DynamicProxy {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        VideoService defaultService = getServiceProxy(VideoService.class);
        System.out.println("video:" + defaultService.getOne());

        UserService userService = getServiceProxy(UserService.class);
        System.out.println("user:" + userService.getOne());
        /*
        VideoService service2 = defaultService.getService(VideoService.class);
        Video video = service2.getOne();
        System.out.println(video);*/
    }

    @SuppressWarnings("unchecked")
    private static <T> T getServiceProxy(Class<T> clazz) {
       return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args1) -> {
                    Annotation[] annotations = method.getDeclaredAnnotations();
                    Annotation dbCurdAnnotation = Arrays.stream(annotations)
                            .filter(annotation -> annotation instanceof DbCurd)
                            .findFirst().orElse(null);
                    if (Objects.isNull(dbCurdAnnotation)) {
                        throw new RuntimeException(" annotation DbCurd must exist");
                    }

                    DbCurd curd = method.getDeclaredAnnotation(DbCurd.class);
                    switch (curd.method()) {
                        case SELECT_ONE:
                           DefaultService service =  new DefaultService();
                         //  System.out.println(method.getReturnType().getTypeName());
                           return service.selectOne(method.getReturnType());
                    }

                    return null;
                });
    }
}
