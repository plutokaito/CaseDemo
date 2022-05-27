package com.kaitoshy.annotation;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Set;

public class ClassPathMyComponentScanner extends ClassPathBeanDefinitionScanner {


    public ClassPathMyComponentScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        System.out.println(" ===> doScan ===== size:" +  beanDefinitionHolders.size());
        if (beanDefinitionHolders.isEmpty()) {
            System.out.println("Errror: scan is empty");
        }
        processBeanDefinitions(beanDefinitionHolders);
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        AbstractBeanDefinition definition;
        for (BeanDefinitionHolder holder: beanDefinitions) {
            System.out.println(">=====processBeanDefinitions:" + holder.getBeanName());
        }
    }

    @Override
    public void resetFilters(boolean useDefaultFilters) {
        super.resetFilters(useDefaultFilters);
    }

    public void registerFilters() {
        addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            Set<String> classNameSet = metadataReader.getAnnotationMetadata().getAnnotationTypes();
            classNameSet.forEach(seta -> System.out.println("====>annotation:" + seta + ", 是否為 true:"
                    + classNameSet.contains(MyComponent.class.getName())));
            return classNameSet.contains(MyComponent.class.getName());
        });

        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }
}
