package com.codeanalysis.simulator;


public interface BeanDefinition {
    ConstructorArgumentValues getConstructorArgumentValues();
    MutablePropertyValues getPropertyValues();
    String getParentName();
    void setParentName(String parentName);
    String getScope();
    void setScope(String scope);
}
