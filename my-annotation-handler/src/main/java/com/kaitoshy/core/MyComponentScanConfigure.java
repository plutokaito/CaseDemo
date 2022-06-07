package com.kaitoshy.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class MyComponentScanConfigure implements BeanDefinitionRegistryPostProcessor, InitializingBean {

    /**
     * 导入的包
     */
    private String packages;



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcess");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("===== BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry ====");
        ClassPathMyComponentScanner scanner = new ClassPathMyComponentScanner(registry);
        scanner.registerFilters();
        System.out.println(packages);
        scanner.scan(StringUtils.tokenizeToStringArray(packages, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" ==== InitializingBean 执行 afterPropertiesSet ===>");
        if (Objects.isNull(packages)) {
            throw new IllegalArgumentException("package 必须有");
        }
        System.out.println(">==== packages:" + packages);
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

}

