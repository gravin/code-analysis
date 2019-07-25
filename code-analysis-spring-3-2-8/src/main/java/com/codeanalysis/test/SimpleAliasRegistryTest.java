package com.codeanalysis.test;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.AliasRegistry;
import org.springframework.core.SimpleAliasRegistry;

import java.lang.reflect.Field;

public class SimpleAliasRegistryTest {
    public static void main(String[] args) throws IllegalAccessException {
        AliasRegistry simpleAliasRegistry =new SimpleAliasRegistry();
        simpleAliasRegistry.registerAlias("zhangsan","sb");
        Field aliasMap = FieldUtils.getField(SimpleAliasRegistry.class, "aliasMap",true);
        System.out.println("目前的别名列表:"+aliasMap.get(simpleAliasRegistry));
        simpleAliasRegistry.registerAlias("sb","zhangsan");
    }
}
