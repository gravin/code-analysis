package com.codeanalysis.simulator;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private final Map<String, RootBeanDefinition> mergedBeanDefinitions =
            new ConcurrentHashMap<String, RootBeanDefinition>(64);

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null, null, false);
    }

    protected <T> T doGetBean(
            final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly) {
        final String beanName = name;
        Object bean;

        // Eagerly check singleton cache for manually registered singletons.
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance != null && args == null) {
            bean = sharedInstance;
        } else {

            try {
                final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
                checkMergedBeanDefinition(mbd, beanName, args);

                // Guarantee initialization of beans that the current bean depends on.
                String[] dependsOn = mbd.getDependsOn();
                if (dependsOn != null) {
                    for (String dependsOnBean : dependsOn) {
                        getBean(dependsOnBean);
                        registerDependentBean(dependsOnBean, beanName);
                    }
                }

                // Create bean instance.
                if (mbd.isSingleton()) {
                    sharedInstance = getSingleton(beanName, new ObjectFactory<Object>() {
                        public Object getObject() throws BeansException {
                            try {
                                return createBean(beanName, mbd, args);
                            } catch (BeansException ex) {
                                // Explicitly remove instance from singleton cache: It might have been put there
                                // eagerly by the creation process, to allow for circular reference resolution.
                                // Also remove any beans that received a temporary reference to the bean.
                                destroySingleton(beanName);
                                throw ex;
                            }
                        }
                    });
                    bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
                } else if (mbd.isPrototype()) {
                    // It's a prototype -> create a new instance.
                    Object prototypeInstance = null;
                    try {
                        beforePrototypeCreation(beanName);
                        prototypeInstance = createBean(beanName, mbd, args);
                    } finally {
                        afterPrototypeCreation(beanName);
                    }
                    bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
                } else {
                    String scopeName = mbd.getScope();
                    final Scope scope = this.scopes.get(scopeName);
                    if (scope == null) {
                        throw new IllegalStateException("No Scope registered for scope '" + scopeName + "'");
                    }
                    try {
                        Object scopedInstance = scope.get(beanName, new ObjectFactory<Object>() {
                            public Object getObject() throws BeansException {
                                beforePrototypeCreation(beanName);
                                try {
                                    return createBean(beanName, mbd, args);
                                } finally {
                                    afterPrototypeCreation(beanName);
                                }
                            }
                        });
                        bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
                    } catch (IllegalStateException ex) {
                        throw new BeanCreationException(beanName,
                                "Scope '" + scopeName + "' is not active for the current thread; " +
                                        "consider defining a scoped proxy for this bean if you intend to refer to it from a singleton",
                                ex);
                    }
                }
            } catch (BeansException ex) {
                cleanupAfterBeanCreationFailure(beanName);
                throw ex;
            }
        }

        // Check if required type matches the type of the actual bean instance.
        if (requiredType != null && bean != null && !requiredType.isAssignableFrom(bean.getClass())) {
            try {
                return getTypeConverter().convertIfNecessary(bean, requiredType);
            } catch (TypeMismatchException ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to convert bean '" + name + "' to required type [" +
                            ClassUtils.getQualifiedName(requiredType) + "]", ex);
                }
                throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
            }
        }
        return (T) bean;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }

    protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) {
        // Quick check on the concurrent map first, with minimal locking.
        RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
        if (mbd != null) {
            return mbd;
        }
        return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
    }

    protected RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition bd) {
        synchronized (this.mergedBeanDefinitions) {
            RootBeanDefinition mbd = null;

            if (mbd == null) {
                if (bd.getParentName() == null) {
                    mbd = new RootBeanDefinition(bd);
                }

                // Set default singleton scope, if not configured before.
                if (StringUtils.isBlank(mbd.getScope())) {
                    mbd.setScope("singleton");
                }

                // Only cache the merged bean definition if we're already about to create an
                // instance of the bean, or at least have already created an instance before.
                if (isCacheBeanMetadata() && isBeanEligibleForMetadataCaching(beanName)) {
                    this.mergedBeanDefinitions.put(beanName, mbd);
                }
            }

            return mbd;
        }
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);
}
