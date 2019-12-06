package com.codeanalysis.simulator;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {
    
    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        return null;
    }
}
