package com.kaitoshy.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;

import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MyComponentRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(metadata.getAnnotationAttributes(ComponentScan.class.getName()));

        if (Objects.nonNull(attributes)) {
            List<String> packages = new ArrayList<>();
            for (String basePackage : attributes.getStringArray("packages")) {
                if (StringUtils.hasText(basePackage)) {
                    packages.add(basePackage);
                }
            }

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MyComponentScanConfigure.class);
            builder.addPropertyValue("packages",  packages);
            registry.registerBeanDefinition(MyComponentRegister.class.getName(), builder.getBeanDefinition());
        }


        System.out.println("打印完毕");
    }

}
