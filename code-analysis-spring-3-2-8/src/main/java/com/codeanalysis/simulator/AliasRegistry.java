package com.codeanalysis.simulator;

public interface AliasRegistry {

    void registerAlias(String name, String alias);

    void removeAlias(String alias);

    boolean isAlias(String beanName);

    String[] getAliases(String name);

}
