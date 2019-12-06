package com.codeanalysis.simulator;


public interface BeanDefinition {
    ConstructorArgumentValues getConstructorArgumentValues();
    MutablePropertyValues getPropertyValues();
    String getParentName();
}
