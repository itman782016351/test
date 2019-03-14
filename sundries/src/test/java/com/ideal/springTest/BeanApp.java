package com.ideal.springTest;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author zhaopei
 * @create 2019-03-12 12:43
 */
public class BeanApp {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory beanFactory = bindViaCode(beanRegistry);
        SuperMarket superMarket = beanFactory.getBean(SuperMarket.class);
        System.out.println(superMarket);
    }

    private static BeanFactory bindViaCode(BeanDefinitionRegistry beanRegistry) {
        AbstractBeanDefinition fruit = new RootBeanDefinition(Apple.class);
        AbstractBeanDefinition drink = new RootBeanDefinition(Milk.class);
        AbstractBeanDefinition superMarket = new RootBeanDefinition(SuperMarket.class);

        beanRegistry.registerBeanDefinition("fruit", fruit);
        beanRegistry.registerBeanDefinition("drink", drink);
        beanRegistry.registerBeanDefinition("superMarket", superMarket);

        // 使用构造方法对属性进行设值
        ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
        argumentValues.addIndexedArgumentValue(0, drink);
        argumentValues.addIndexedArgumentValue(1, fruit);
        superMarket.setConstructorArgumentValues(argumentValues);

        // 使用setter方法对属性进行设值
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("fruit", fruit);
        propertyValues.addPropertyValue("drink", drink);
        superMarket.setPropertyValues(propertyValues);

        return (BeanFactory) beanRegistry;
    }
}
