package com.codeanalysis.simulator;

public abstract class AbstractBeanFactory implements BeanFactory {
    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }
}
