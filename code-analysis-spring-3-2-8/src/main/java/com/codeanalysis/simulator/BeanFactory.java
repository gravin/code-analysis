package com.codeanalysis.simulator;

public interface BeanFactory {

    Object getBean(String name);

    boolean containsBean(String name);

    String[] getAliases(String name);
}
