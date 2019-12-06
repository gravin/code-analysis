package com.codeanalysis.simulator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
            this.singletonObjects.put(beanName, singletonObject);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = this.singletonObjects.get(beanName);
        return singletonObject;
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return (this.singletonObjects.containsKey(beanName));
    }
}
