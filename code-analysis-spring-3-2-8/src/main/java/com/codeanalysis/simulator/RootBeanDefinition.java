package com.codeanalysis.simulator;

public class RootBeanDefinition extends AbstractBeanDefinition {
    public String getParentName() {
        return null;
    }
    RootBeanDefinition(BeanDefinition original) {
        super(original);
    }
}
